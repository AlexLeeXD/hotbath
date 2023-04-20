package hivestandsteam.hotbath.fluid_blocks;

import hivestandsteam.hotbath.register.FluidsRegister;
import hivestandsteam.hotbath.util.ParticleGenerator;
import java.util.function.Supplier;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/** Hot Water Block */
public class HotWaterBlock extends FlowingFluidBlock implements IHotbathBlock {

  public HotWaterBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }

  @Override
  public FluidState getHotBathFluidState() {
    return FluidsRegister.HOT_WATER_FLUID.get().getDefaultState();
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
    ParticleGenerator.renderDefaultSteam(worldIn, pos, rand);
  }
}
