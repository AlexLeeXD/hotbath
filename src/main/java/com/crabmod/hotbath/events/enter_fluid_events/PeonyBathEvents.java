package com.crabmod.hotbath.events.enter_fluid_events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.util.CustomFluidHandler;
import com.crabmod.hotbath.util.EffectRemovalHandler;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static com.crabmod.hotbath.util.HealthRegenHandler.regenHealth;

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
    if (event.getEntityLiving() instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
      NBTTagCompound playerData = player.getEntityData();
      boolean isInPeonyBath = CustomFluidHandler.isPlayerInPeonyBathBlock(player);

      if (isInPeonyBath) {
        if (!playerData.getBoolean(hasEnteredPeonyBath)) {
          int enteredCount = playerData.getInt(enteredNumberInPeonyBath) + 1;
          playerData.setInt(enteredNumberInPeonyBath, enteredCount);
          playerData.setBoolean(hasEnteredPeonyBath, true);
        }

        int hotBathTime = playerData.getInt(peonyBathStayedTime) + 1;
        playerData.setInt(peonyBathStayedTime, hotBathTime);

        regenHealth(0.25F, 2, player);

        if (playerData.getInt(peonyBathStayedTime) >= 15 * TICK_NUMBER) {
          applyAttributeModifier(
              player,
              SharedMonsterAttributes.KNOCKBACK_RESISTANCE,
              KNOCKBACK_RESISTANCE_UUID,
              0.05,
              0); // AttributeModifier.Operation.ADDITION (0 = ADDITION in 1.13.2)
          applyAttributeModifier(
              player,
              SharedMonsterAttributes.ATTACK_SPEED,
              ATTACK_SPEED_MODIFIER_UUID,
              0.10,
              1); // AttributeModifier.Operation.MULTIPLY_TOTAL (1 = MULTIPLY_TOTAL in 1.13.2)
          EffectRemovalHandler.removeNegativeEffects(player);
          EffectRemovalHandler.removeBadOmen(player);
        }

        if (playerData.getInt(enteredNumberInPeonyBath) >= LUCK_THRESHOLD) {
          player.addPotionEffect(
              new PotionEffect(MobEffects.LUCK, LUCK_DURATION, 45, false, false));
        }

        playerData.setInt(PEONY_BATH_EXITED_TIME, 0);
      } else {
        if (playerData.getBoolean(hasEnteredPeonyBath)) {
          playerData.setBoolean(hasEnteredPeonyBath, false);
        }

        playerData.setInt(PEONY_BATH_EXITED_TIME, playerData.getInt(PEONY_BATH_EXITED_TIME) + 1);

        if (playerData.getInt(PEONY_BATH_EXITED_TIME) >= 15 * TICK_NUMBER) {
          removeAttributeModifier(
              player, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER_UUID);
        }

        if (playerData.getInt(PEONY_BATH_EXITED_TIME) >= 30 * TICK_NUMBER) {
          removeAttributeModifier(
              player, SharedMonsterAttributes.KNOCKBACK_RESISTANCE, KNOCKBACK_RESISTANCE_UUID);
        }

        playerData.setInt(peonyBathStayedTime, 0);
      }
    }
  }

  private static void applyAttributeModifier(
      EntityPlayerMP player, IAttribute attribute, UUID uuid, double value, int operation) {
    IAttributeInstance attributeInstance = player.getAttribute(attribute);

    AttributeModifier modifier = new AttributeModifier(uuid, attribute.getName(), value, operation);

    if (attributeInstance.getModifier(modifier.getID()) != null) {
      attributeInstance.removeModifier(modifier.getID());
    }

    attributeInstance.applyModifier(modifier);
  }

  private static void removeAttributeModifier(
      EntityPlayerMP player, IAttribute attribute, UUID uuid) {
    IAttributeInstance attributeInstance = player.getAttribute(attribute);

    if (attributeInstance.getModifier(uuid) != null) {
      attributeInstance.removeModifier(uuid);
    }
  }
}
