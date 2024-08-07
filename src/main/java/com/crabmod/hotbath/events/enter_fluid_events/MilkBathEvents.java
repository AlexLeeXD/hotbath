package com.crabmod.hotbath.events.enter_fluid_events;

import static com.crabmod.hotbath.util.HealthRegenHandler.regenHealth;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.advancements.AdvancementTrigger;
import com.crabmod.hotbath.util.CustomFluidHandler;
import com.crabmod.hotbath.util.EffectRemovalHandler;
import com.crabmod.hotbath.util.HungerRegenHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class MilkBathEvents {
  private static final int TICK_NUMBER = 20;
  static final String MILK_BATH_ENTERED_NUMBER = "MilkBathEnteredNumber";
  static final String MILK_BATH_STAYED_TIME = "MilkBathStayedTime";
  static final String HAS_ENTERED_MILK_BATH = "HasEnteredMilkBath";
  private static final int MILK_BATH_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int MILK_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;

  private static final AdvancementTrigger MILK_BATH_TRIGGER =
      new AdvancementTrigger("hotbath", "milk_bath");

  // enter hot water block event
  @SubscribeEvent
  public static void enterMilkBathEvents(LivingEvent.LivingTickEvent event) {
    enterFluidEvents(
        event,
        MILK_BATH_ENTERED_COUNT_TRIGGER_NUMBER,
        MILK_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS,
        MILK_BATH_ENTERED_NUMBER,
        MILK_BATH_STAYED_TIME,
        HAS_ENTERED_MILK_BATH);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingTickEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String enteredNumberInMilkBath,
      String milkBathStayedTime,
      String hasEnteredMilkBath) {
    if (event.getEntity() instanceof ServerPlayer player) {
      CompoundTag playerData = player.getPersistentData();
      boolean isInMilkBath = CustomFluidHandler.isPlayerInMilkBathBlock(player);

      if (isInMilkBath) {
        handleAdvancement(
            enteredCountTriggerNumber,
            enteredNumberInMilkBath,
            milkBathStayedTime,
            hasEnteredMilkBath,
            player,
            playerData);
        HungerRegenHandler.regenHunger(1, 15, player);
        regenHealth(0.25F, 2, player);
        if (playerData.getInt(milkBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          EffectRemovalHandler.removeNegativeEffectsExceptUnluck(player);
        }
      } else {
        playerData.putInt(milkBathStayedTime, 0);
        playerData.putBoolean(hasEnteredMilkBath, false);
      }
    }
  }

  static void handleAdvancement(
      int enteredCountTriggerNumber,
      String enteredNumberInMilkBath,
      String milkBathStayedTime,
      String hasEnteredMilkBath,
      ServerPlayer player,
      CompoundTag playerData) {
    if (!playerData.getBoolean(hasEnteredMilkBath)) {
      int enteredCount = playerData.getInt(enteredNumberInMilkBath) + 1;
      playerData.putInt(enteredNumberInMilkBath, enteredCount);
      playerData.putBoolean(hasEnteredMilkBath, true);

      if (enteredCount >= enteredCountTriggerNumber) {
        MILK_BATH_TRIGGER.trigger(player); // Pass the player instance here

        playerData.putInt(enteredNumberInMilkBath, 0);
      }
    }

    int hotBathTime = playerData.getInt(milkBathStayedTime) + 1;
    playerData.putInt(milkBathStayedTime, hotBathTime);
  }
}
