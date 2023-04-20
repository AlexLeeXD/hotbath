package hivestandsteam.hotbath.events;

import hivestandsteam.hotbath.HotBath;
import hivestandsteam.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
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
      RayTraceResult rayTraceResult = event.getPlayer().pick(5.0D, 1.0F, true);
      World world = event.getWorld();

      // Check if the ray trace hits a block
      if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
        BlockPos pos = new BlockPos(rayTraceResult.getHitVec());
        lastFishingRodPosition = pos;
      }
    }
  }

  @SubscribeEvent
  public static void onItemFished(ItemFishedEvent event) {
    if (lastFishingRodPosition != null) {
      World world = event.getPlayer().getEntityWorld();
      boolean isHotBathBlock =
          world.getBlockState(lastFishingRodPosition).getBlock() instanceof IHotbathBlock;

      // If the fishing rod is in a HotBath block, cancel the fishing event
      if (isHotBathBlock) {
        event.setCanceled(true);
      }
    }
  }
}
