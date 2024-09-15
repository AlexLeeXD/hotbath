package com.crabmod.hotbath.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;

public class EffectRemovalHandler {
  public static void removeNegativeEffectsExceptUnluck(EntityPlayerMP player) {
    // Create a new effect instance collection to avoid modifying the collection during iteration
    List<PotionEffect> activeEffects = new ArrayList<>(player.getActivePotionEffects());

    for (PotionEffect effectInstance : activeEffects) {
      Potion effect = effectInstance.getPotion();

      // Check if the effect is a negative effect, and not unluck
      if (effect.isBadEffect() && effect != MobEffects.UNLUCK) {
        // Remove the effect
        player.removeActivePotionEffect(effect);
      }
    }
  }

  public static void removeNegativeEffectsExceptSlowAndUnluck(EntityPlayerMP player) {
    List<PotionEffect> activeEffects = new ArrayList<>(player.getActivePotionEffects());

    for (PotionEffect effectInstance : activeEffects) {
      Potion effect = effectInstance.getPotion();

      if (effect.isBadEffect()
              && effect != MobEffects.UNLUCK
              && effect != MobEffects.SLOWNESS) {
        player.removeActivePotionEffect(effect);
      }
    }
  }

  public static void removeNegativeEffects(EntityPlayerMP player) {
    List<PotionEffect> activeEffects = new ArrayList<>(player.getActivePotionEffects());

    for (PotionEffect effectInstance : activeEffects) {
      Potion effect = effectInstance.getPotion();

      if (effect.isBadEffect()) {
        player.removeActivePotionEffect(effect);
      }
    }
  }
}
