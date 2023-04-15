package net.hiveteam.hotbath.events;

import net.hiveteam.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.block.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockPlacementHandler {

  @SubscribeEvent
  public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
    Block placedBlock = event.getPlacedBlock().getBlock();
    Block blockAbove = event.getWorld().getBlockState(event.getPos().up()).getBlock();

    boolean isplacedBlockARestrictedBlock = isRestrictedBlock(placedBlock);
    boolean isInHotbath = isInHotbath((World) event.getWorld(), event.getPos(), blockAbove);

    if (isInHotbath) {
      if (isplacedBlockARestrictedBlock) { // If the block is restricted and in hotbath, cancel the
        // event, drop as item
        handleRestrictedBlockPlacement(event, placedBlock);
      }
    }
  }

  private static boolean isRestrictedBlock(Block block) {
    return block instanceof AbstractCoralPlantBlock
        || block instanceof ConduitBlock
        || block instanceof AbstractTopPlantBlock
        || block instanceof SeaGrassBlock
        || block instanceof TallSeaGrassBlock;
  }

  private static boolean isInHotbath(World world, BlockPos pos, Block blockAbove) {
    return blockAbove instanceof IHotbathBlock;
  }

  private static void handleRestrictedBlockPlacement(
      BlockEvent.EntityPlaceEvent event, Block placedBlock) {
    event.setCanceled(true);
    BlockPos pos = event.getPos();
    ItemStack coralItemStack = new ItemStack(placedBlock.asItem());
    ItemEntity coralItemEntity =
        new ItemEntity(
            (World) event.getWorld(),
            pos.getX() + 0.5,
            pos.getY() + 0.5,
            pos.getZ() + 0.5,
            coralItemStack);
    event.getWorld().addEntity(coralItemEntity);
  }
}
