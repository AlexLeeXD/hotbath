package com.crabmod.hotbath.events.enter_fluid_events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.advancements.AdvancementTrigger;
import com.crabmod.hotbath.util.CustomFluidHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class HotWaterEvents {
  private static final int TICK_NUMBER = 20;
  static final String HOT_WATER_ENTERED_NUMBER = "HotWaterEnteredNumber";
  static final String HOT_WATER_STAYED_TIME = "HotWaterStayedTime";
  static final String HAS_ENTERED_HOT_WATER = "HasEnteredHotWater";
  private static final int HOT_WATER_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int HOT_WATER_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;

  private static final AdvancementTrigger HOT_WATER_TRIGGER =
      new AdvancementTrigger("hotbath", "hot_water");

  // enter hot water block event
  @SubscribeEvent
  public static void enterHotWaterEvents(LivingEvent.LivingTickEvent event) {
    enterFluidEvents(
        event,
        HOT_WATER_ENTERED_COUNT_TRIGGER_NUMBER,
        HOT_WATER_STAYED_EFFECT_TRIGGER_TIME_SECONDS,
        HOT_WATER_ENTERED_NUMBER,
        HOT_WATER_STAYED_TIME,
        HAS_ENTERED_HOT_WATER);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingTickEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String hotWaterEnteredNumber,
      String hotWaterStayedTime,
      String hasEnteredHotWater) {
    if (event.getEntity() instanceof ServerPlayer player) {
      CompoundTag playerData = player.getPersistentData();
      boolean isInHotWater = CustomFluidHandler.isPlayerInHotWaterBlock(player);

      if (isInHotWater) {
        if (!playerData.getBoolean(hasEnteredHotWater)) {
          int enteredCount = playerData.getInt(hotWaterEnteredNumber) + 1;
          playerData.putInt(hotWaterEnteredNumber, enteredCount);
          playerData.putBoolean(hasEnteredHotWater, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            HOT_WATER_TRIGGER.trigger(player); // Pass the player instance here

            playerData.putInt(hotWaterEnteredNumber, 0);
          }
        }

        int hotBathTime = playerData.getInt(hotWaterStayedTime) + 1;
        playerData.putInt(hotWaterStayedTime, hotBathTime);

        if (playerData.getInt(hotWaterStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          player.addEffect(
              new MobEffectInstance(
                  MobEffects.MOVEMENT_SPEED, TICK_NUMBER * 20, 0, false, false, true));
        }
      } else {
        playerData.putInt(hotWaterStayedTime, 0);
        playerData.putBoolean(hasEnteredHotWater, false);
      }
    }
  }
}
