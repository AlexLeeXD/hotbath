package com.crabmod.hotbath.util;

import static com.crabmod.hotbath.registers.ParticleRegister.STEAM_PARTICLE;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.Logger;

@OnlyIn(Dist.CLIENT)
/** Fluids Particles */
public class ParticleGenerator {

  /**
   * Render default steam
   *
   * @param worldIn
   * @param pos
   * @param rand
   */
  public static void renderDefaultSteam(ClientLevel worldIn, BlockPos pos, RandomSource rand) {
    if (rand.nextInt(60) == 0) {
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
