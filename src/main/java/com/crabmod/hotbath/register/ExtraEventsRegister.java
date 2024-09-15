package com.crabmod.hotbath.register;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.advancements.AdvancementTrigger;
import com.crabmod.hotbath.particles.SteamParticle;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtraEventsRegister {
  @SubscribeEvent
  public static void registerParticlesFactories(final ParticleFactoryRegisterEvent event) {
    assert ParticleRegister.STEAM_PARTICLE.get() != null;
    Minecraft.getInstance()
        .particles
        .registerFactory(
            ParticleRegister.STEAM_PARTICLE.get(), SteamParticle.CozySmokeFactory::new);
  }
}
