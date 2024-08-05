package com.crabmod.hotbath.registers;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.advancements.AdvancementTrigger;
import com.crabmod.hotbath.particles.SteamParticle;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtraEventsRegister {
  @SubscribeEvent
  public static void registerParticlesFactories(final RegisterParticleProvidersEvent event) {
    Minecraft.getInstance()
        .particleEngine
        .register(ParticleRegister.STEAM_PARTICLE.get(), SteamParticle.CozySmokeFactory::new);
  }

  @SubscribeEvent
  public static void registerAdvancementTrigger(FMLCommonSetupEvent event) {
    event.enqueueWork(
        () -> {
          CriteriaTriggers.register(
              "foot_health", new AdvancementTrigger("hotbath", "foot_health"));
          CriteriaTriggers.register("milk_skin", new AdvancementTrigger("hotbath", "milk_skin"));
          CriteriaTriggers.register(
              "chronic_invalid", new AdvancementTrigger("hotbath", "chronic_invalid"));
          CriteriaTriggers.register(
              "rose_body_fragrance", new AdvancementTrigger("hotbath", "rose_body_fragrance"));
        });
  }
}
