package net.hiveteam.hotbath.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class HungerRegenHandler {
  public static void regenHunger(int secondsPerRegen, ServerPlayerEntity player) {
    CompoundNBT playerData = player.getPersistentData();
    String hungerRegenTimerKey = "hungerRegenTimer";

    int hungerRegenTimer = playerData.getInt(hungerRegenTimerKey) + 1;
    playerData.putInt(hungerRegenTimerKey, hungerRegenTimer);

    if (hungerRegenTimer >= 20) { // 1 second (20 ticks/second)
      int currentFoodLevel = player.getFoodStats().getFoodLevel();
      int maxFoodLevel = 20;

      if (currentFoodLevel < maxFoodLevel) {
        player.getFoodStats().addStats(2, 0.0f); // Adding 2 food points
      }
      playerData.putInt(hungerRegenTimerKey, 0);
    }
  }
}
