package net.hiveteam.hotbath.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

import static net.hiveteam.hotbath.util.FluidsParticles.animateDefaultSteam;

/**
 * 热水方块
 */
public class HotWaterBlock extends FlowingFluidBlock {
    public HotWaterBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
        animateDefaultSteam(worldIn, pos, rand);
    }
}
