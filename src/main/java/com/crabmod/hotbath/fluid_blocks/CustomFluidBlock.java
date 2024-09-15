package com.crabmod.hotbath.fluid_blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReaderBase;

public class CustomFluidBlock extends Block {

    private final Fluid fluid;

    public CustomFluidBlock(Fluid fluid, Block.Properties properties) {
        super(properties);
        this.fluid = fluid;
    }

    @Override
    public int tickRate(IWorldReaderBase worldIn) {
        return 5;
    }
}
