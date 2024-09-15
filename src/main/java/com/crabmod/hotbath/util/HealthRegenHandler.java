package com.crabmod.hotbath.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class HealthRegenHandler {
  public static void regenHealth(
      float regenHealthNumber, double perSecondsNumber, EntityPlayer player) {

    NBTTagCompound playerData = player.getEntityData();
    String healthRegenTimerKey = "healthRegenTimer";

    int healthRegenTimer = playerData.getInt(healthRegenTimerKey) + 1;
    playerData.setInt(healthRegenTimerKey, healthRegenTimer);

    if (healthRegenTimer >= 20 * perSecondsNumber) {
      float currentHealth = player.getHealth();
      float maxHealth = player.getMaxHealth();

      if (currentHealth < maxHealth) {
        player.setHealth(Math.min(currentHealth + regenHealthNumber, maxHealth));
      }
      playerData.setInt(healthRegenTimerKey, 0);
    }
  }
}
