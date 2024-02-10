package com.crabmod.hotbath.fluid_blocks;

import com.crabmod.hotbath.fluid_blocks.IHotbathBlock;

import com.crabmod.hotbath.ModItems.FluidsRegister;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.function.Supplier;

/** Herbal Bath Block */
public class HerbalBathBlock extends LiquidBlock implements IHotbathBlock {
    public HerbalBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

  @Override
  public FluidState getHotBathFluidState() {
    return FluidsRegister.HERBAL_BATH_FLUID.get().defaultFluidState();
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(BlockState stateIn, ClientLevel worldIn, BlockPos pos, java.util.Random rand) {
//    hivestandsteam.hotbath.util.ParticleGenerator.renderDefaultSteam(worldIn, pos, rand);
      return;
  }
}
