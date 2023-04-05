package net.hiveteam.hotbath.util;

import static net.hiveteam.hotbath.register.ParticleRegister.STEAM_PARTICLE;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    if (rand.nextInt(100) == 0) {
      worldIn.addParticle(
          STEAM_PARTICLE.get(),
          (double) pos.getX() + rand.nextDouble(),
          (double) pos.getY() + rand.nextDouble(),
          (double) pos.getZ() + rand.nextDouble(),
          0.0D,
          0.05D,
          0.0D);
    }
  }

  //  public static void renderCustomParticles(World worldIn, BlockPos pos, FluidState state,
  // java.util.Random rand) {
  //    if (!state.isSource() && !state.get(FlowingFluid.FALLING)) {
  //      BlockPos blockpos = pos.down();
  //      BlockState blockStateBelow = worldIn.getBlockState(blockpos);
  //
  //      if (rand.nextInt(64) == 0) {
  //        // 生成自定义的进入液体粒子效果
  //        worldIn.addParticle(
  //                CUSTOM_ENTERING_PARTICLE,
  //                pos.getX() + 0.5,
  //                pos.getY() + 0.5,
  //                pos.getZ() + 0.5,
  //                0.0D,
  //                0.0D,
  //                0.0D);
  //      }
  //
  //      if (rand.nextInt(10) == 0 && blockStateBelow.isSolidSide(worldIn, blockpos, Direction.UP))
  // {
  //        // 生成自定义的气泡效果
  //        worldIn.addParticle(
  //                CUSTOM_BUBBLE_PARTICLE,
  //                pos.getX() + rand.nextDouble(),
  //                pos.getY() + 1.1D,
  //                pos.getZ() + rand.nextDouble(),
  //                0.0D,
  //                0.0D,
  //                0.0D);
  //      }
  //    }
  //  }

}
