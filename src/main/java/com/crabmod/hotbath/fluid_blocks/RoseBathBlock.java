package com.crabmod.hotbath.fluid_blocks;

import java.util.function.Supplier;
import net.minecraft.world.level.material.FlowingFluid;

/** Rose Bath Block */
public class RoseBathBlock extends AbstractHotbathBlock {
  public RoseBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }
}
