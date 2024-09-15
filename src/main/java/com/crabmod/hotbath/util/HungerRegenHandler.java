package com.crabmod.hotbath.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;

public class HungerRegenHandler {
  public static void regenHunger(
          int regenHungerNumber, float perSecondsNumber, EntityPlayer player) {
    NBTTagCompound playerData = player.getEntityData();
    String hungerRegenTimerKey = "hungerRegenTimer";

    int hungerRegenTimer = playerData.getInt(hungerRegenTimerKey) + 1;
    playerData.setInt(hungerRegenTimerKey, hungerRegenTimer);

    int ticksPerRegen =
            (int) (perSecondsNumber * 20); // Calculate the number of ticks required to recover

    if (hungerRegenTimer >= ticksPerRegen) { // Regenerate based on the given number of seconds
      FoodStats foodStats = player.getFoodStats();
      int currentFoodLevel = foodStats.getFoodLevel();
      int maxFoodLevel = 20;

      if (currentFoodLevel < maxFoodLevel) {
        foodStats.addStats(regenHungerNumber, 0);
      }
      playerData.setInt(hungerRegenTimerKey, 0);
    }
  }
}
