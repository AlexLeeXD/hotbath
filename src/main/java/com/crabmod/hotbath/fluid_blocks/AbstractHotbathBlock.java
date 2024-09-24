package com.crabmod.hotbath.fluid_blocks;

import com.crabmod.hotbath.util.ParticleGenerator;
import java.util.Random;
import java.util.function.Supplier;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

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
      @NotNull Random rand) {

    // Generate steam particles at random adjacent air blocks
    generateSteamParticles(worldIn, pos, rand);
  }

  // Generate steam particles around the block if adjacent blocks are air
  private void generateSteamParticles(Level worldIn, BlockPos pos, Random rand) {
    BlockPos[] adjacentPositions =
        new BlockPos[] {pos.above(), pos.below(), pos.north(), pos.south(), pos.east(), pos.west()};

    int airBlockCount = 0;
    BlockPos[] airBlocks = new BlockPos[adjacentPositions.length];

    // Check for air blocks adjacent to the current position
    for (BlockPos adjacentPos : adjacentPositions) {
      if (worldIn.getBlockState(adjacentPos).isAir()) {
        airBlocks[airBlockCount++] = adjacentPos;
      }
    }

    // If there are air blocks, randomly select one and generate steam particles
    if (airBlockCount > 0) {
      BlockPos selectedPos = airBlocks[rand.nextInt(airBlockCount)];
      ParticleGenerator.renderDefaultSteam((ClientLevel) worldIn, selectedPos, rand);
    }
  }
}
