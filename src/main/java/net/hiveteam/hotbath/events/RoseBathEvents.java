package net.hiveteam.hotbath.events;

import static net.hiveteam.hotbath.util.EffectRemovalHandler.*;
import static net.hiveteam.hotbath.util.HealthRegenHandler.regenHealth;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.util.CustomFluidHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class RoseBathEvents {
  private static final int TICK_NUMBER = 20;
  static final String ROSE_BATH_ENTERED_NUMBER = "RoseBathEnteredNumber";
  static final String ROSE_BATH_STAYED_TIME = "RoseBathStayedTime";
  static final String HAS_ENTERED_ROSE_BATH = "HasEnteredRoseBath";
  static final String ROSE_BATH_ADVANCEMENT_ID = "hotbath:rose_body_fragrance";
  private static final int ROSE_BATH_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int ROSE_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;

  @SubscribeEvent
  public static void enterRoseBathEvents(LivingEvent.LivingUpdateEvent event) {
    enterFluidEvents(
        event,
        ROSE_BATH_ENTERED_COUNT_TRIGGER_NUMBER,
        ROSE_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS,
        ROSE_BATH_ENTERED_NUMBER,
        ROSE_BATH_STAYED_TIME,
        HAS_ENTERED_ROSE_BATH,
        ROSE_BATH_ADVANCEMENT_ID);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingUpdateEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String enteredNumberInRoseBath,
      String roseBathStayedTime,
      String hasEnteredRoseBath,
      String roseBathAdvancementId) {
    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();
      boolean isInRoseBath = CustomFluidHandler.isPlayerInRoseBathBlock(player);

      if (isInRoseBath) {
        if (!playerData.getBoolean(hasEnteredRoseBath)) {
          int enteredCount = playerData.getInt(enteredNumberInRoseBath) + 1;
          playerData.putInt(enteredNumberInRoseBath, enteredCount);
          playerData.putBoolean(hasEnteredRoseBath, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            Advancement advancement =
                player
                    .getServer()
                    .getAdvancementManager()
                    .getAdvancement(ResourceLocation.tryCreate(roseBathAdvancementId));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.putInt(enteredNumberInRoseBath, 0);
            }
          }
        }
        int roseBathTime = playerData.getInt(roseBathStayedTime) + 1;
        playerData.putInt(roseBathStayedTime, roseBathTime);
        regenHealth(0.25F, 1, player);
        if (playerData.getInt(roseBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          removeNegativeEffects(player);
          removeBadOmen(player);
          player.addPotionEffect(
              new EffectInstance(Effects.STRENGTH, 20 * TICK_NUMBER, 0, false, false, true));
        }

      } else {
        playerData.putInt(roseBathStayedTime, 0);
        playerData.putBoolean(hasEnteredRoseBath, false);
      }
    }
  }
}
