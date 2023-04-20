package hivestandsteam.hotbath.events.enter_fluid_events;

import static hivestandsteam.hotbath.util.HealthRegenHandler.regenHealth;

import hivestandsteam.hotbath.HotBath;
import hivestandsteam.hotbath.util.CustomFluidHandler;
import hivestandsteam.hotbath.util.EffectRemovalHandler;
import hivestandsteam.hotbath.util.HungerRegenHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class HoneyBathEvents {
  private static final int TICK_NUMBER = 20;
  static final String HONEY_BATH_STAYED_TIME = "HoneyBathStayedTime";
  private static final int HONEY_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;

  @SubscribeEvent
  public static void enterHoneyBathEvents(LivingEvent.LivingUpdateEvent event) {
    enterFluidEvents(event, HONEY_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS, HONEY_BATH_STAYED_TIME);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingUpdateEvent event,
      int stayedEffectTriggerTime,
      String honeyBathStayedTime) {
    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();
      boolean isInHoneyBath = CustomFluidHandler.isPlayerInHoneyBathBlock(player);

      if (isInHoneyBath) {
        int honeyBathTime = playerData.getInt(honeyBathStayedTime) + 1;
        playerData.putInt(honeyBathStayedTime, honeyBathTime);

        regenHealth(0.25F, 1, player);
        HungerRegenHandler.regenHunger(1, 4, player);

        player.addPotionEffect(
            new EffectInstance(Effects.SLOWNESS, 10 * TICK_NUMBER, 0, false, false, true));

        if (playerData.getInt(honeyBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          EffectRemovalHandler.removeNegativeEffectsExceptSlowAndUnluck(player);
          player.addPotionEffect(
              new EffectInstance(Effects.ABSORPTION, 20 * TICK_NUMBER, 1, false, false, true));
        }
      } else {
        playerData.putInt(honeyBathStayedTime, 0);
      }
    }
  }
}
