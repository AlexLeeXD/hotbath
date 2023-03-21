package net.hiveteam.hotbath.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class EffectRemovalHandler {
  public static void removeNegativeEffectsExceptUnluckAndBadOmen(ServerPlayerEntity player) {
    // 创建一个新的效果实例集合，以避免在迭代过程中修改集合
    List<EffectInstance> activeEffects = new ArrayList<>(player.getActivePotionEffects());

    for (EffectInstance effectInstance : activeEffects) {
      Effect effect = effectInstance.getPotion();

      // 检查效果是否为负面效果，且不是霉运或不祥之兆
      if (effect.getEffectType() == EffectType.HARMFUL
          && effect != Effects.UNLUCK
          && effect != Effects.BAD_OMEN) {
        // 移除效果
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
}
