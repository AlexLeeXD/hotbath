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

    if (healthRegenTimer >= 20 * perSecondsNumber) { // 4 seconds (20 ticks/second * 4 seconds)
      float currentHealth = player.getHealth();
      float maxHealth = player.getMaxHealth();

      if (currentHealth < maxHealth) {
        player.setHealth(Math.min(currentHealth + regenHealthNumber, maxHealth));
      }
      playerData.putInt(healthRegenTimerKey, 0);
    }
  }
}
