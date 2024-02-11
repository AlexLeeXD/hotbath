package com.crabmod.hotbath.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class ResistanceBoostHandler {
  public static void applyResistanceBoost(int secondPerBoost, ServerPlayer player) {
    int duration = 20 * secondPerBoost; // 例如，10秒 (20 ticks/second * 10秒)
    int amplifier = 0; // 等级1抗性 (放大器0表示等级1)
    boolean ambient = false;
    boolean showParticles = true;

    // 创建抗性效果实例，使用可能的1.18版本中的类名和方法
    MobEffectInstance resistanceEffect =
        new MobEffectInstance(
            MobEffects.DAMAGE_RESISTANCE, duration, amplifier, ambient, showParticles);
    // 应用效果到玩家
    player.addEffect(resistanceEffect);
  }
}
