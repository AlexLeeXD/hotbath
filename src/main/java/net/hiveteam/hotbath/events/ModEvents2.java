package net.hiveteam.hotbath.events;

import net.hiveteam.hotbath.HotBath;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
// This file is for repository reference only. It is not part of the mod.

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class ModEvents2 {

  @SubscribeEvent
  public static void logPlayerWhenTheyAreInHotWater(LivingEvent.LivingUpdateEvent event) {
    //    if (event.getEntityLiving() instanceof PlayerEntity) {
    //      PlayerEntity player = (PlayerEntity) event.getEntityLiving();
    //
    //      if (CustomFluidHandler.isPlayerInHotWaterBlock(player)) {
    //        HotBath.LOGGER.info("Player is in hot water!");
    //      }
    //    }
  }
}
