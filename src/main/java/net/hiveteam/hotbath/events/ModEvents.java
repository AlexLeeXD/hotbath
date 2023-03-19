package net.hiveteam.hotbath.events;

import static net.hiveteam.hotbath.register.ParticleRegister.STEAM_PARTICLE;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.particles.SteamParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
  @SubscribeEvent
  public static void registerParticlesFactories(final ParticleFactoryRegisterEvent event) {
    Minecraft.getInstance()
        .particles
        .registerFactory(STEAM_PARTICLE.get(), SteamParticle.CozySmokeFactory::new);
  }
}
