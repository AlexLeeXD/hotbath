package com.crabmod.hotbath.fluid_blocks;

import java.util.function.Supplier;

import com.crabmod.hotbath.register.FluidsRegister;
import com.crabmod.hotbath.util.ParticleGenerator;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/** Honey Bath Block */
public class HoneyBathBlock extends FlowingFluidBlock implements IHotbathBlock {
  public HoneyBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }

  @Override
  public IFluidState getHotBathFluidState() {
    assert FluidsRegister.HONEY_BATH_FLUID.get() != null;
    return FluidsRegister.HONEY_BATH_FLUID.get().getDefaultState();
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
    ParticleGenerator.renderDefaultSteam(worldIn, pos, rand);
  }
}
