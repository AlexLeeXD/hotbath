package com.crabmod.hotbath.fluid_blocks;

import java.util.function.Supplier;
import net.minecraft.world.level.material.FlowingFluid;

// ** Honey Bath Block */
public class HoneyBathBlock extends AbstractHotbathBlock {
  public HoneyBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }
}
