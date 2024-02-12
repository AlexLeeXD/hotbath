package com.crabmod.hotbath.events;

import static com.crabmod.hotbath.HotBath.MOD_ID;

import com.crabmod.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class AquaticMobSpawnPreventionHandler {

  @SubscribeEvent
  public static void onCheckSpawn(LivingSpawnEvent.CheckSpawn event) {
    LevelAccessor world = event.getWorld();
    BlockPos pos = event.getEntity().blockPosition();

    // Check if the spawning location is a HotBath block
    boolean isHotBathBlock = world.getBlockState(pos).getBlock() instanceof IHotbathBlock;

    // If the spawning location is a HotBath block, cancel the spawn event
    if (isHotBathBlock) {
      event.setResult(Event.Result.DENY);
    }
  }
}
