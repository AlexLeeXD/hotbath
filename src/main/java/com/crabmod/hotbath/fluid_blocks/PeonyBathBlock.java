package com.crabmod.hotbath.fluid_blocks;

import java.util.function.Supplier;
import net.minecraft.world.level.material.FlowingFluid;

/** Peony Bath Block */
public class PeonyBathBlock extends AbstractHotbathBlock {
  public PeonyBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }
}
