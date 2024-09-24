package com.crabmod.hotbath.fluid_blocks;

import java.util.function.Supplier;
import net.minecraft.world.level.material.FlowingFluid;

/** Milk Bath Block */
public class MilkBathBlock extends AbstractHotbathBlock {
  public MilkBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }
}
