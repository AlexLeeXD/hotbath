package hivestandsteam.hotbath.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ResistanceBoostHandler {
  public static void applyResistanceBoost(int secondPerBoost, ServerPlayerEntity player) {
    int duration = 20 * secondPerBoost; // 10 seconds (20 ticks/second * 10 seconds)
    int amplifier = 0; // Level 1 resistance (amplifier 0 means level 1)
    boolean ambient = false;
    boolean showParticles = true;

    EffectInstance resistanceEffect =
        new EffectInstance(Effects.RESISTANCE, duration, amplifier, ambient, showParticles);
    player.addPotionEffect(resistanceEffect);
  }
}
