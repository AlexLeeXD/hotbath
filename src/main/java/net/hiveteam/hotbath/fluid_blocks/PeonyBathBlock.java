package net.hiveteam.hotbath.fluid_blocks;

import static net.hiveteam.hotbath.util.ParticleGenerator.renderDefaultSteam;

import java.util.function.Supplier;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Peony Bath Block */
public class PeonyBathBlock extends FlowingFluidBlock {
  public PeonyBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
  }

  @Override
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
    renderDefaultSteam(worldIn, pos, rand);
  }

  @Override
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
    if (entityIn instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) entityIn;
      LOGGER.info(player.getUniqueID() + " is in peony bath!");
    }
  }
}
