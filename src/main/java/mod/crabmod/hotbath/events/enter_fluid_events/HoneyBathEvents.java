package mod.crabmod.hotbath.events.enter_fluid_events;

import static mod.crabmod.hotbath.util.HealthRegenHandler.regenHealth;

import mod.crabmod.hotbath.HotBath;
import mod.crabmod.hotbath.util.CustomFluidHandler;
import mod.crabmod.hotbath.util.EffectRemovalHandler;
import mod.crabmod.hotbath.util.HungerRegenHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class HoneyBathEvents {
  private static final int TICK_NUMBER = 20;
  static final String HONEY_BATH_STAYED_TIME = "HoneyBathStayedTime";
  private static final int HONEY_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;

  @SubscribeEvent
  public static void enterHoneyBathEvents(LivingEvent.LivingTickEvent event) {
    enterFluidEvents(event, HONEY_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS, HONEY_BATH_STAYED_TIME);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingTickEvent event, int stayedEffectTriggerTime, String honeyBathStayedTime) {
    if (event.getEntity() instanceof ServerPlayer player) {
      CompoundTag playerData = player.getPersistentData();
      boolean isInHoneyBath = CustomFluidHandler.isPlayerInHoneyBathBlock(player);

      if (isInHoneyBath && player.isAlive()) {
        int honeyBathTime = playerData.getInt(honeyBathStayedTime) + 1;
        playerData.putInt(honeyBathStayedTime, honeyBathTime);

        regenHealth(0.25F, 1, player);
        HungerRegenHandler.regenHunger(1, 4, player);

        player.addEffect(
            new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN, 10 * TICK_NUMBER, 0, false, false, true));

        if (playerData.getInt(honeyBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          EffectRemovalHandler.removeNegativeEffectsExceptSlowAndUnluck(player);
          player.addEffect(
              new MobEffectInstance(
                  MobEffects.ABSORPTION, 20 * TICK_NUMBER, 1, false, false, true));
        }
      } else {
        playerData.putInt(honeyBathStayedTime, 0);
      }
    }
  }
}
