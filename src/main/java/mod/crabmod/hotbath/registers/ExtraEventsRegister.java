package mod.crabmod.hotbath.registers;

import mod.crabmod.hotbath.HotBath;
import mod.crabmod.hotbath.advancements.AdvancementTrigger;
import mod.crabmod.hotbath.particles.SteamParticle;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(
    modid = HotBath.MOD_ID,
    bus = Mod.EventBusSubscriber.Bus.MOD,
    value = Dist.CLIENT)
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
              "hotbath:foot_health", new AdvancementTrigger("hotbath", "foot_health"));
          CriteriaTriggers.register(
              "hotbath:milk_skin", new AdvancementTrigger("hotbath", "milk_skin"));
          CriteriaTriggers.register(
              "hotbath:chronic_invalid", new AdvancementTrigger("hotbath", "chronic_invalid"));
          CriteriaTriggers.register(
              "hotbath:rose_body_fragrance",
              new AdvancementTrigger("hotbath", "rose_body_fragrance"));
        });
  }
}
