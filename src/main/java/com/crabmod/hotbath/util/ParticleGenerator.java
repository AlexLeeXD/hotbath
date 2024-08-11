package com.crabmod.hotbath.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.crabmod.hotbath.register.ParticleRegister.STEAM_PARTICLE;

/** Fluids Particles */
public class ParticleGenerator {

  /**
   * Render default steam
   *
   * @param worldIn
   * @param pos
   * @param rand
   */
  public static void renderDefaultSteam(World worldIn, BlockPos pos, java.util.Random rand) {
    if (rand.nextInt(80) == 0) {
      worldIn.addParticle(
          STEAM_PARTICLE.get(),
          (double) pos.getX() + rand.nextDouble(),
          (double) pos.getY() + rand.nextDouble(),
          (double) pos.getZ() + rand.nextDouble(),
          0.0D,
          0.02D,
          0.0D);
    }
  }
}
