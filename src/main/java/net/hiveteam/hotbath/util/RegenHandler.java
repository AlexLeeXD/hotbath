package net.hiveteam.hotbath.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class RegenHandler {
    private static final int TICK_NUMBER = 20;
  public static boolean juedgeTime(int stayTime, int perSecondsNumber){
      return stayTime%(perSecondsNumber*TICK_NUMBER)==0;
  }
  public static void regenHealth(
      float regenHealthNumber, ServerPlayerEntity player) {
      float currentHealth = player.getHealth();
      float maxHealth = player.getMaxHealth();

      if (currentHealth < maxHealth) {
        player.setHealth(Math.min(currentHealth + regenHealthNumber, maxHealth));
      }
    }

    public static void regenHunger(
            int regenHungerNumber, ServerPlayerEntity player) {
        int currentFoodLevel = player.getFoodStats().getFoodLevel();
        int maxFoodLevel = 20;
        if (currentFoodLevel < maxFoodLevel) {
            player.getFoodStats().addStats(regenHungerNumber, 0);
        }
    }
}
