package net.hiveteam.hotbath.fluid_blocks;

import static net.hiveteam.hotbath.util.FluidsParticles.renderDefaultSteam;

import java.util.function.Supplier;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** 蜂蜜浴汤方块 */
public class HoneyBathBlock extends FlowingFluidBlock {
  public HoneyBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }

  @Override
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
    renderDefaultSteam(worldIn, pos, rand);
  }
}
