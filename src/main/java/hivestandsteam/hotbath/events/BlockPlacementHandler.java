package hivestandsteam.hotbath.events;

import static hivestandsteam.hotbath.HotBath.MOD_ID;

import hivestandsteam.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class BlockPlacementHandler {

  @SubscribeEvent
  public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
    World world = event.getWorld();
    BlockPos pos = event.getPos().offset(event.getFace());
    FluidState fluidState = world.getFluidState(pos);
    boolean isHotBathBlock = fluidState.getBlockState().getBlock() instanceof IHotbathBlock;

    if (isHotBathBlock) {
      Item heldItem = event.getItemStack().getItem();
      if (heldItem instanceof BlockItem) {
        BlockItem blockItem = (BlockItem) heldItem;
        Block heldBlock = blockItem.getBlock();

        // Check if the held block can be waterlogged
        if (heldBlock instanceof IWaterLoggable) {
          // Cancel the original block placement event
          event.setCanceled(true);

          // Get the HotBath FluidState
          FluidState hotBathFluidState =
              ((IHotbathBlock) fluidState.getBlockState().getBlock()).getHotBathFluidState();

          // Create a new block state with the waterlogged property set to true
          BlockState waterloggedState =
              heldBlock.getDefaultState().with(BlockStateProperties.WATERLOGGED, true);

          // Set the new block state in the world
          world.setBlockState(pos, waterloggedState);

          // Manually fill the block with your HotBath fluid
          ((IWaterLoggable) heldBlock)
              .receiveFluid(world, pos, waterloggedState, hotBathFluidState);

          // Consume the held item
          if (!event.getPlayer().isCreative()) {
            event.getItemStack().shrink(1);
          }
        }
      }
    }
  }
}
