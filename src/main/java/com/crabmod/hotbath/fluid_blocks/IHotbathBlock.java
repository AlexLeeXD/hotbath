package com.crabmod.hotbath.fluid_blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.multiplayer.ClientLevel;

public interface IHotbathBlock {
  FluidState getHotBathFluidState();

  @OnlyIn(Dist.CLIENT)
  void animateTick(BlockState stateIn, ClientLevel worldIn, BlockPos pos, java.util.Random rand);
}
