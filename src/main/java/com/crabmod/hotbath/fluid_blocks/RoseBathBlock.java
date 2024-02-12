package com.crabmod.hotbath.fluid_blocks;

import com.crabmod.hotbath.registers.FluidsRegister;
import com.crabmod.hotbath.util.ParticleGenerator;
import java.util.function.Supplier;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/** Rose Bath Block */
public class RoseBathBlock extends LiquidBlock implements IHotbathBlock {
  public RoseBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }

  @Override
  public FluidState getHotBathFluidState() {
    return FluidsRegister.ROSE_BATH_FLUID.get().defaultFluidState();
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, java.util.Random rand) {
    ParticleGenerator.renderDefaultSteam((ClientLevel) worldIn, pos, rand);
  }
}
