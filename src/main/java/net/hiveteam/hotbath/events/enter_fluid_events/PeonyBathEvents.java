package net.hiveteam.hotbath.events.enter_fluid_events;

import static net.hiveteam.hotbath.util.EffectRemovalHandler.*;
import static net.hiveteam.hotbath.util.HealthRegenHandler.regenHealth;

import java.util.UUID;
import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.util.CustomFluidHandler;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class PeonyBathEvents {
  static final String PEONY_BATH_ENTERED_NUMBER = "PeonyBathEnteredNumber";
  static final String PEONY_BATH_STAYED_TIME = "PeonyBathStayedTime";
  static final String HAS_ENTERED_PEONY_BATH = "HasEnteredPeonyBath";
  static final String PEONY_BATH_EXITED_TIME = "PeonyBathExitedTime";
  private static final int TICK_NUMBER = 20;
  private static final int PEONY_BATH_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int PEONY_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 5;
  private static final int KNOCKBACK_RESISTANCE_DURATION = 30 * TICK_NUMBER;
  private static final int ATTACK_SPEED_DURATION = 15 * TICK_NUMBER;
  private static final int LUCK_DURATION = 45 * TICK_NUMBER;
  private static final int LUCK_THRESHOLD = 50;

  private static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.randomUUID();
  private static final UUID KNOCKBACK_RESISTANCE_UUID = UUID.randomUUID();
  private static final int ATTRIBUTE_REMOVE_DELAY_TICKS = 15 * TICK_NUMBER;
  // enter hot water block event
  @SubscribeEvent
  public static void enterPeonyBathEvents(LivingEvent.LivingUpdateEvent event) {
    enterFluidEvents(
        event,
        PEONY_BATH_ENTERED_COUNT_TRIGGER_NUMBER,
        PEONY_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS,
        PEONY_BATH_ENTERED_NUMBER,
        PEONY_BATH_STAYED_TIME,
        HAS_ENTERED_PEONY_BATH);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingUpdateEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String enteredNumberInPeonyBath,
      String peonyBathStayedTime,
      String hasEnteredPeonyBath) {
    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();
      boolean isInPeonyBath = CustomFluidHandler.isPlayerInPeonyBathBlock(player);

      if (isInPeonyBath) {
        if (!playerData.getBoolean(hasEnteredPeonyBath)) {
          int enteredCount = playerData.getInt(enteredNumberInPeonyBath) + 1;
          playerData.putInt(enteredNumberInPeonyBath, enteredCount);
          playerData.putBoolean(hasEnteredPeonyBath, true);
        }

        int hotBathTime = playerData.getInt(peonyBathStayedTime) + 1;
        playerData.putInt(peonyBathStayedTime, hotBathTime);

        regenHealth(0.25F, 2, player);

        if (playerData.getInt(peonyBathStayedTime) >= 15 * TICK_NUMBER) {
          applyAttributeModifier(
              player,
              Attributes.KNOCKBACK_RESISTANCE,
              KNOCKBACK_RESISTANCE_UUID,
              0.05,
              AttributeModifier.Operation.ADDITION);
          applyAttributeModifier(
              player,
              Attributes.ATTACK_SPEED,
              ATTACK_SPEED_MODIFIER_UUID,
              0.10,
              AttributeModifier.Operation.MULTIPLY_TOTAL);
          removeNegativeEffects(player);
          removeBadOmen(player);
        }

        if (playerData.getInt(enteredNumberInPeonyBath) >= LUCK_THRESHOLD) {
          player.addPotionEffect(
              new EffectInstance(Effects.LUCK, LUCK_DURATION, 45, false, false, true));
        }

        playerData.putInt(PEONY_BATH_EXITED_TIME, 0);
      } else {
        if (playerData.getBoolean(hasEnteredPeonyBath)) {
          playerData.putBoolean(hasEnteredPeonyBath, false);
        }

        playerData.putInt(PEONY_BATH_EXITED_TIME, playerData.getInt(PEONY_BATH_EXITED_TIME) + 1);

        if (playerData.getInt(PEONY_BATH_EXITED_TIME) >= 15 * TICK_NUMBER) {
          // Remove attribute modifier
          removeAttributeModifier(player, Attributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER_UUID);
        }

        if (playerData.getInt(PEONY_BATH_EXITED_TIME) >= 30 * TICK_NUMBER) {
          removeAttributeModifier(
              player, Attributes.KNOCKBACK_RESISTANCE, KNOCKBACK_RESISTANCE_UUID);
        }

        playerData.putInt(peonyBathStayedTime, 0);
      }
    }
  }

  private static void applyAttributeModifier(
      ServerPlayerEntity player,
      Attribute attribute,
      UUID uuid,
      double value,
      AttributeModifier.Operation operation) {
    ModifiableAttributeInstance attributeInstance = player.getAttribute(attribute);

    if (attributeInstance != null) {
      AttributeModifier modifier =
          new AttributeModifier(uuid, attribute.getAttributeName(), value, operation);

      if (attributeInstance.getModifier(modifier.getID()) != null) {
        attributeInstance.removeModifier(modifier.getID());
      }

      attributeInstance.applyPersistentModifier(modifier);
    }
  }

  private static void removeAttributeModifier(
      ServerPlayerEntity player, Attribute attribute, UUID uuid) {
    ModifiableAttributeInstance attributeInstance = player.getAttribute(attribute);

    if (attributeInstance != null && attributeInstance.getModifier(uuid) != null) {
      attributeInstance.removeModifier(uuid);
    }
  }
}
