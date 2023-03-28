package net.hiveteam.hotbath.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class HungerRegenHandler {


  public static void regenHunger(
      int regenHungerNumber, float perSecondsNumber, ServerPlayerEntity player) {
    CompoundNBT playerData = player.getPersistentData();
    String hungerRegenTimerKey = "hungerRegenTimer";

    int hungerRegenTimer = playerData.getInt(hungerRegenTimerKey) + 1;
    playerData.putInt(hungerRegenTimerKey, hungerRegenTimer);

    int ticksPerRegen = (int) (perSecondsNumber * 20);

    if (hungerRegenTimer >= ticksPerRegen) {
      int currentFoodLevel = player.getFoodStats().getFoodLevel();
      int maxFoodLevel = 20;

      if (currentFoodLevel < maxFoodLevel) {
        player.getFoodStats().addStats(regenHungerNumber, 0);
      }
      playerData.putInt(hungerRegenTimerKey, 0);
    }
  }
}
