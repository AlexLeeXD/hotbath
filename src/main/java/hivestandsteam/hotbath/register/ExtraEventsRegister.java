package hivestandsteam.hotbath.register;

import hivestandsteam.hotbath.HotBath;
import hivestandsteam.hotbath.advancements.AdvancementTrigger;
import hivestandsteam.hotbath.particles.SteamParticle;
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
    Minecraft.getInstance()
        .particles
        .registerFactory(
            ParticleRegister.STEAM_PARTICLE.get(), SteamParticle.CozySmokeFactory::new);
  }

  @SubscribeEvent
  public static void registerAdvancementTrigger(FMLCommonSetupEvent event) {
    event.enqueueWork(
        () -> {
          CriteriaTriggers.register(new AdvancementTrigger("hotbath", "foot_health"));
          CriteriaTriggers.register(new AdvancementTrigger("hotbath", "milk_skin"));
          CriteriaTriggers.register(new AdvancementTrigger("hotbath", "chronic_invalid"));
          CriteriaTriggers.register(new AdvancementTrigger("hotbath", "rose_body_fragrance"));
        });
  }
}
