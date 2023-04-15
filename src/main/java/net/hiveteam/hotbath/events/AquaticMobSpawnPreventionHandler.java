package net.hiveteam.hotbath.events;

import net.hiveteam.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AquaticMobSpawnPreventionHandler {

  @SubscribeEvent
  public static void onCheckSpawn(LivingSpawnEvent.CheckSpawn event) {
    World world = (World) event.getWorld();
    BlockPos pos = event.getEntity().getPosition();

    // Check if the spawning location is a HotBath block
    boolean isHotBathBlock = world.getBlockState(pos).getBlock() instanceof IHotbathBlock;

    // If the spawning location is a HotBath block, cancel the spawn event
    if (isHotBathBlock) {
      event.setResult(Event.Result.DENY);
    }
  }
}
