package com.crabmod.hotbath.fluid_blocks;

import com.crabmod.hotbath.util.ParticleGenerator;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/** Abstract base class for Hotbath blocks */
public abstract class AbstractHotbathBlock extends LiquidBlock {

  protected AbstractHotbathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(
      @NotNull BlockState stateIn,
      @NotNull Level worldIn,
      @NotNull BlockPos pos,
      @NotNull RandomSource rand) {

    // Array to store potential air block positions around the current block
    BlockPos[] adjacentPositions =
        new BlockPos[] {
          pos.above(), // Above
          pos.below(), // Below
          pos.north(), // North
          pos.south(), // South
          pos.east(), // East
          pos.west() // West
        };

    // Counter for the number of valid air blocks found
    int airBlockCount = 0;
    BlockPos[] airBlocks = new BlockPos[adjacentPositions.length];

    // Check each adjacent position for air blocks
    for (BlockPos adjacentPos : adjacentPositions) {
      if (worldIn.getBlockState(adjacentPos).isAir()) {
        airBlocks[airBlockCount++] = adjacentPos;
      }
    }

    // If air blocks were found, randomly select one to render steam particles
    if (airBlockCount > 0) {
      BlockPos selectedPos = airBlocks[rand.nextInt(airBlockCount)];
      ParticleGenerator.renderDefaultSteam((ClientLevel) worldIn, selectedPos, rand);
    }
  }
}
