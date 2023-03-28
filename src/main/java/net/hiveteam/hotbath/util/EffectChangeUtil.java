package net.hiveteam.hotbath.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class EffectChangeUtil {
  public static void removeNegativeEffectsExceptUnluck(ServerPlayerEntity player) {

    List<EffectInstance> activeEffects = new ArrayList<>(player.getActivePotionEffects());

    for (EffectInstance effectInstance : activeEffects) {
      Effect effect = effectInstance.getPotion();

      if (effect.getEffectType() == EffectType.HARMFUL && effect != Effects.UNLUCK) {
        // 移除效果
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

  public static void applyAttributeModifier(
          ServerPlayerEntity player,
          Attribute attribute,
          AttributeModifier modifier) {
    ModifiableAttributeInstance attributeInstance = player.getAttribute(attribute);
    if (attributeInstance != null) {
      if (attributeInstance.getModifier(modifier.getID()) != null) {
        attributeInstance.removeModifier(modifier.getID());
      }

      attributeInstance.applyPersistentModifier(modifier);
    }
  }

  public static void removeAttributeModifier(
          ServerPlayerEntity player, Attribute attribute, UUID uuid) {
    ModifiableAttributeInstance attributeInstance = player.getAttribute(attribute);

    if (attributeInstance != null && attributeInstance.getModifier(uuid) != null) {
      attributeInstance.removeModifier(uuid);
    }
  }
}
