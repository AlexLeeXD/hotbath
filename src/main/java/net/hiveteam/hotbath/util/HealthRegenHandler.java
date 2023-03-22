package net.hiveteam.hotbath.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class HealthRegenHandler {
  public static void regenHealth(
      float regenHealthNumber, double perSecondsNumber, ServerPlayerEntity player) {

    CompoundNBT playerData = player.getPersistentData();
    String healthRegenTimerKey = "healthRegenTimer";

    int healthRegenTimer = playerData.getInt(healthRegenTimerKey) + 1;
    playerData.putInt(healthRegenTimerKey, healthRegenTimer);

    if (healthRegenTimer >= 20 * perSecondsNumber) {
      float currentHealth = player.getHealth();
      float maxHealth = player.getMaxHealth();

      if (currentHealth < maxHealth) {
        player.setHealth(Math.min(currentHealth + regenHealthNumber, maxHealth));
        //        Logger.getGlobal().info("Health Regen: " + regenHealthNumber);
        //        Logger.getGlobal().info("Current Health: " + currentHealth);
        //        Logger.getGlobal().info("Time: " + healthRegenTimer);
      }
      playerData.putInt(healthRegenTimerKey, 0);
    }
  }
}
