package com.crabmod.hotbath.fluid_blocks;

import com.crabmod.hotbath.register.FluidsRegister;
import com.crabmod.hotbath.util.ParticleGenerator;
import net.minecraft.block.BlockFlowingFluid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/** Hot Water Block */
public class HotWaterBlock extends BlockFlowingFluid implements IHotbathBlock {

  public HotWaterBlock(FlowingFluid flowingFluid, Properties properties) {
    super(flowingFluid, properties);
  }

  @Override
  public IFluidState getHotBathFluidState() {
    return FluidsRegister.HOT_WATER_FLUID.getDefaultState();
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
    ParticleGenerator.renderDefaultSteam(worldIn, pos, rand);
  }
}
