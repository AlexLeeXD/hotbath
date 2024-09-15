package com.crabmod.hotbath.register;

import com.crabmod.hotbath.HotBath;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ParticleRegister {

  public static BasicParticleType STEAM_PARTICLE;

  public static void registerParticles() {
    STEAM_PARTICLE = new BasicParticleType(true);
    registerParticle("steam_particle", STEAM_PARTICLE);
  }

  private static void registerParticle(String name, BasicParticleType particleType) {
    particleType.setRegistryName(new ResourceLocation(HotBath.MOD_ID, name));
    ForgeRegistries.PARTICLE_TYPES.register(particleType);
  }
}
