package mod.crabmod.hotbath.events;

import static mod.crabmod.hotbath.HotBath.MOD_ID;

import mod.crabmod.hotbath.fluid_blocks.AbstractHotbathBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class AquaticMobSpawnPreventionHandler {

  @SubscribeEvent
  public static void onCheckSpawn(MobSpawnEvent event) {
    LevelAccessor world = event.getLevel();
    BlockPos pos = event.getEntity().blockPosition();

    // Check if the spawning location is a HotBath block
    boolean isHotBathBlock = world.getBlockState(pos).getBlock() instanceof AbstractHotbathBlock;

    // If the spawning location is a HotBath block, cancel the spawn event
    if (isHotBathBlock) {
      event.setResult(Event.Result.DENY);
    }
  }
}
