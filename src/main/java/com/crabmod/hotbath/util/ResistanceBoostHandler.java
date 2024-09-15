package com.crabmod.hotbath.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;

public class ResistanceBoostHandler {
  public static void applyResistanceBoost(int secondPerBoost, EntityPlayer player) {
    int duration = 20 * secondPerBoost; // duration in ticks (20 ticks/second)
    int amplifier = 0; // Level 1 resistance (amplifier 0 means level 1)
    boolean ambient = false;
    boolean showParticles = true;

    PotionEffect resistanceEffect =
        new PotionEffect(MobEffects.RESISTANCE, duration, amplifier, ambient, showParticles);
    player.addPotionEffect(resistanceEffect);
  }
}
