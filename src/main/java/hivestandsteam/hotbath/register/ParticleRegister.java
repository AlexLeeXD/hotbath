package hivestandsteam.hotbath.register;

import hivestandsteam.hotbath.HotBath;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegister {
  public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
      DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HotBath.MOD_ID);

  public static final RegistryObject<BasicParticleType> STEAM_PARTICLE =
      PARTICLE_TYPES.register("steam_particle", () -> new BasicParticleType(true));

  public static void register(IEventBus eventBus) {
    PARTICLE_TYPES.register(eventBus);
  }
}
