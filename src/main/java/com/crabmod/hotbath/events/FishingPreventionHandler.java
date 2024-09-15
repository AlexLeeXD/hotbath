package com.crabmod.hotbath.events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class FishingPreventionHandler {

  @SubscribeEvent
  public static void onItemFished(ItemFishedEvent event) {
    ItemStack itemStack = event.getEntityLiving().getHeldItemMainhand();

    // Check if the player is using a fishing rod
    if (itemStack.getItem() instanceof ItemFishingRod) {
      RayTraceResult rayTraceResult =
          event.getEntityLiving().rayTrace(5.0D, 1.0F, RayTraceFluidMode.NEVER);

      // Check if the ray trace hits a block
      assert rayTraceResult != null;
      if (rayTraceResult.type == RayTraceResult.Type.BLOCK) {
        BlockPos hitPos = rayTraceResult.getBlockPos();
        World world = event.getEntityLiving().getEntityWorld();

        boolean isHotBathBlock = world.getBlockState(hitPos).getBlock() instanceof IHotbathBlock;

        // If the fishing rod is in a HotBath block, cancel the fishing event
        if (isHotBathBlock) {
          event.setCanceled(true);
        }
      }
    }
  }
}
