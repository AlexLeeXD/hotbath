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
public class HotWaterEvents {
  private static final int TICK_NUMBER = 20;
  static final String HOT_WATER_ENTERED_NUMBER = "HotWaterEnteredNumber";
  static final String HOT_WATER_STAYED_TIME = "HotWaterStayedTime";
  static final String HAS_ENTERED_HOT_WATER = "HasEnteredHotWater";
  static final String HOT_WATER_ADVANCEMENT_ID = "hotbath:foot_health";
  private static final int HOT_WATER_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int HOT_WATER_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;

  // enter hot water block event
  @SubscribeEvent
  public static void enterHotWaterEvents(LivingEvent.LivingUpdateEvent event) {
    enterFluidEvents(
        event,
        HOT_WATER_ENTERED_COUNT_TRIGGER_NUMBER,
        HOT_WATER_STAYED_EFFECT_TRIGGER_TIME_SECONDS, // <-- 修改这里
        HOT_WATER_ENTERED_NUMBER,
        HOT_WATER_STAYED_TIME,
        HAS_ENTERED_HOT_WATER,
        HOT_WATER_ADVANCEMENT_ID);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingUpdateEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String hotWaterEnteredNumber,
      String hotWaterStayedTime,
      String hasEnteredHotWater,
      String hotWaterAdvancementId) {
    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();
      boolean isInHotWater = CustomFluidHandler.isPlayerInHotWaterBlock(player);

      if (isInHotWater) {
        if (!playerData.getBoolean(hasEnteredHotWater)) {
          int enteredCount = playerData.getInt(hotWaterEnteredNumber) + 1;
          playerData.putInt(hotWaterEnteredNumber, enteredCount);
          playerData.putBoolean(hasEnteredHotWater, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            Advancement advancement =
                player
                    .getServer()
                    .getAdvancementManager()
                    .getAdvancement(ResourceLocation.tryCreate(hotWaterAdvancementId));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.putInt(hotWaterEnteredNumber, 0);
            }
          }
        }

        int hotBathTime = playerData.getInt(hotWaterStayedTime) + 1;
        playerData.putInt(hotWaterStayedTime, hotBathTime);

        if (playerData.getInt(hotWaterStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          player.addPotionEffect(
              new EffectInstance(Effects.SPEED, TICK_NUMBER * 20, 0, false, false, true));
        }
      } else {
        playerData.putInt(hotWaterStayedTime, 0);
        playerData.putBoolean(hasEnteredHotWater, false);
      }
    }
  }
}
