package com.crabmod.hotbath.events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class FishingPreventionHandler {

  private static BlockPos lastFishingRodPosition = null;

  @SubscribeEvent
  public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
    ItemStack itemStack = event.getItemStack();

    // Check if the player is using a fishing rod
    if (itemStack.getItem() instanceof FishingRodItem) {
      HitResult rayTraceResult = event.getPlayer().pick(5.0D, 1.0F, true);
      Level world = event.getWorld();

      // Check if the ray trace hits a block
      if (rayTraceResult.getType() == HitResult.Type.BLOCK) {
        lastFishingRodPosition = new BlockPos(rayTraceResult.getLocation());
      }
    }
  }

  @SubscribeEvent
  public static void onItemFished(ItemFishedEvent event) {
    if (lastFishingRodPosition != null) {
      Level world = event.getPlayer().getCommandSenderWorld();
      boolean isHotBathBlock =
          world.getBlockState(lastFishingRodPosition).getBlock() instanceof IHotbathBlock;

      // If the fishing rod is in a HotBath block, cancel the fishing event
      if (isHotBathBlock) {
        event.setCanceled(true);
      }
    }
  }
}
