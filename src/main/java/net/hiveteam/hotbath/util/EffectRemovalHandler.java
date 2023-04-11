package net.hiveteam.hotbath.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class EffectRemovalHandler {
  public static void removeNegativeEffectsExceptUnluck(ServerPlayerEntity player) {
    // Create a new effect instance collection to avoid modifying the collection during iteration
    List<EffectInstance> activeEffects = new ArrayList<>(player.getActivePotionEffects());

    for (EffectInstance effectInstance : activeEffects) {
      Effect effect = effectInstance.getPotion();

      // Check if the effect is a negative effect, and not unluck or bad omen
      if (effect.getEffectType() == EffectType.HARMFUL && effect != Effects.UNLUCK) {
        // Remove the effect
        player.removePotionEffect(effect);
      }
    }
  }

  public static void removeNegativeEffectsExceptSlowAndUnluck(ServerPlayerEntity player) {
    List<EffectInstance> activeEffects = new ArrayList<>(player.getActivePotionEffects());

    for (EffectInstance effectInstance : activeEffects) {
      Effect effect = effectInstance.getPotion();

      if (effect.getEffectType() == EffectType.HARMFUL
          && effect != Effects.UNLUCK
          && effect != Effects.SLOWNESS) {
        player.removePotionEffect(effect);
      }
    }
  }

  public static void removeNegativeEffects(ServerPlayerEntity player) {
    List<EffectInstance> activeEffects = new ArrayList<>(player.getActivePotionEffects());

    for (EffectInstance effectInstance : activeEffects) {
      Effect effect = effectInstance.getPotion();

      if (effect.getEffectType() == EffectType.HARMFUL) {
        player.removePotionEffect(effect);
      }
    }
  }

  public static void removeBadOmen(ServerPlayerEntity player) {
    player.removePotionEffect(Effects.BAD_OMEN);
  }
}
