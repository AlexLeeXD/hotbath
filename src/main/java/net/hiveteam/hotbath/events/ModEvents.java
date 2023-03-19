package net.hiveteam.hotbath.events;

import static net.hiveteam.hotbath.register.ParticleRegister.STEAM_PARTICLE;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.particles.SteamParticle;
import net.hiveteam.hotbath.util.CustomFluidHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class ModEvents {
  @SubscribeEvent
  public static void registerParticlesFactories(ParticleFactoryRegisterEvent event) {
    Minecraft.getInstance()
        .particles
        .registerFactory(STEAM_PARTICLE.get(), SteamParticle.CozySmokeFactory::new);
  }

  @SubscribeEvent
  public static void logPlayerWhenTheyAreInHotWater(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) event.getEntityLiving();

      if (CustomFluidHandler.isPlayerInHotWaterBlock(player)) {
        HotBath.LOGGER.info("Player is in hot water!");
      }
    }
  }
}
