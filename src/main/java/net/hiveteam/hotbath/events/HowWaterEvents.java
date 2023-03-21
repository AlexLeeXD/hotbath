package net.hiveteam.hotbath.events;

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
public class HowWaterEvents {
  private static final int TICK_NUMBER = 20;
  static final String HOT_WATER_ENTERED_NUMBER = "HotWaterEnteredNumber";
  static final String HOT_WATER_STAYED_TIME = "HotWaterStayedTime";
  static final String HAS_ENTERED_HOT_WATER = "HasEnteredHotWater";
  static final String HOT_WATER_ADVANCEMENT_ID = "hotbath:foot_health";
  private static final int HOT_WATER_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int HOT_WATER_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;
  private static final EffectInstance HOT_WATER_STAY_EFFECT =
      new EffectInstance(Effects.SPEED, TICK_NUMBER * 15, 1, false, false, true);

  // enter hot water block event
  @SubscribeEvent
  public static void enterHotWaterEvents(LivingEvent.LivingUpdateEvent event) {
    enterFluidEvents(
        event,
        HOT_WATER_ENTERED_COUNT_TRIGGER_NUMBER,
        TICK_NUMBER * HOT_WATER_STAYED_EFFECT_TRIGGER_TIME_SECONDS,
        HOT_WATER_ENTERED_NUMBER,
        HOT_WATER_STAYED_TIME,
        HAS_ENTERED_HOT_WATER,
        HOT_WATER_ADVANCEMENT_ID,
        HOT_WATER_STAY_EFFECT);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingUpdateEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String enteredNumber,
      String stayedTime,
      String hasEntered,
      String advancementId,
      EffectInstance effectInstance) {
    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();
      boolean isInHotWater = CustomFluidHandler.isPlayerInHotWaterBlock(player);

      if (isInHotWater) {
        if (!playerData.getBoolean(hasEntered)) {
          int enteredCount = playerData.getInt(enteredNumber) + 1;
          playerData.putInt(enteredNumber, enteredCount);
          playerData.putBoolean(hasEntered, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            Advancement advancement =
                player
                    .getServer()
                    .getAdvancementManager()
                    .getAdvancement(ResourceLocation.tryCreate(advancementId));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.putInt(enteredNumber, 0);
            }
          }
        }

        int hotBathTime = playerData.getInt(stayedTime) + 1;
        playerData.putInt(stayedTime, hotBathTime);

        if (playerData.getInt(stayedTime) >= stayedEffectTriggerTime) {
          player.addPotionEffect(effectInstance);
        }
      } else {
        playerData.putInt(stayedTime, 0);
        playerData.putBoolean(hasEntered, false);
      }
    }
  }
}
