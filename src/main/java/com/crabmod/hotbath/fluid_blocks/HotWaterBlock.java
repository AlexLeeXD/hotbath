package com.crabmod.hotbath.fluid_blocks;

import com.crabmod.hotbath.registers.FluidsRegister;
import com.crabmod.hotbath.util.ParticleGenerator;
import java.util.function.Supplier;
import java.util.logging.Logger;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

/** Hot Water Block */
public class HotWaterBlock extends LiquidBlock implements IHotbathBlock {
  public HotWaterBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }

  @Override
  public FluidState getHotBathFluidState() {
    return FluidsRegister.HOT_WATER_FLUID.get().defaultFluidState();
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(
      @NotNull BlockState stateIn,
      @NotNull Level worldIn,
      @NotNull BlockPos pos,
      @NotNull RandomSource rand) {
    ParticleGenerator.renderDefaultSteam((ClientLevel) worldIn, pos, rand);
  }
}
