package com.crabmod.hotbath.fluid_blocks;

import java.util.function.Supplier;
import net.minecraft.world.level.material.FlowingFluid;

/** Herbal Bath Block */
public class HerbalBathBlock extends AbstractHotbathBlock {

  public HerbalBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }
}
