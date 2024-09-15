package com.crabmod.hotbath.events;

import com.crabmod.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static com.crabmod.hotbath.HotBath.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class BlockPlacementHandler {

  @SubscribeEvent
  public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
    World world = event.getWorld();
    BlockPos pos = event.getPos().offset(Objects.requireNonNull(event.getFace()));
    IBlockState blockState = world.getBlockState(pos);
    boolean isHotBathBlock = blockState.getBlock() instanceof IHotbathBlock;

    if (isHotBathBlock) {
      EntityPlayer player = event.getEntityPlayer();
      ItemBlock heldItem = (ItemBlock) event.getItemStack().getItem();
      Block heldBlock = heldItem.getBlock();

      // Check if the held block is a custom fluid block or similar (custom logic for waterlogging)
      if (heldBlock instanceof IHotbathBlock) { // Adjust to fit your custom logic
        // Cancel the original block placement event
        event.setCanceled(true);

        // Replace the block with the held block and simulate waterlogging by placing the block
        IBlockState newState = heldBlock.getDefaultState();
        world.setBlockState(pos, newState, 3);

        // Fill the block with HotBath fluid
        IBlockState hotBathState =
            ((IHotbathBlock) blockState.getBlock()).getHotBathFluidState().getBlockState();
        world.setBlockState(pos, hotBathState, 3);

        // Consume the held item if the player is not in creative mode
        if (!player.isCreative()) {
          event.getItemStack().shrink(1);
        }
      }
    }
  }
}
