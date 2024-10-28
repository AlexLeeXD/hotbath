package mod.crabmod.hotbath.fluid_blocks;

import java.util.function.Supplier;
import net.minecraft.world.level.material.FlowingFluid;

/** Hot Water Block */
public class HotWaterBlock extends AbstractHotbathBlock {
  public HotWaterBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }
}
