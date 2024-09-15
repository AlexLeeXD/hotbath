package com.crabmod.hotbath.events.enter_fluid_events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.util.CustomFluidHandler;
import com.crabmod.hotbath.util.EffectRemovalHandler;
import com.crabmod.hotbath.util.HungerRegenHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.crabmod.hotbath.util.HealthRegenHandler.regenHealth;
import static com.crabmod.hotbath.util.HungerRegenHandler.regenHunger;

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

    if (event.getEntityLiving() instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
      NBTTagCompound playerData = player.getEntityData();
      boolean isInHoneyBath = CustomFluidHandler.isPlayerInHoneyBathBlock((EntityPlayer) player);

      if (isInHoneyBath) {
        int honeyBathTime = playerData.getInt(honeyBathStayedTime) + 1;
        playerData.setInt(honeyBathStayedTime, honeyBathTime);

        regenHealth(0.25F, 1, player);
        regenHunger(1, 4, player);

        player.addPotionEffect(
            new PotionEffect(MobEffects.SLOWNESS, 10 * TICK_NUMBER, 0, false, false));

        if (playerData.getInt(honeyBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          EffectRemovalHandler.removeNegativeEffectsExceptSlowAndUnluck(player);
          player.addPotionEffect(
              new PotionEffect(MobEffects.ABSORPTION, 20 * TICK_NUMBER, 1, false, false));
        }
      } else {
        playerData.setInt(honeyBathStayedTime, 0);
      }
    }
  }
}
