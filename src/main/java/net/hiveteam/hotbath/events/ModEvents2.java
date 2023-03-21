package net.hiveteam.hotbath.events;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.util.CustomFluidHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class ModEvents2 {

  private static final String HOT_WATER_ENTERED_NUMBER = "HotWaterEnteredNumber";
  private static final String HOT_WATER_STAYED_TIME = "HotWaterStayedTime";
  private static final String HAS_ENTERED_HOT_WATER = "HasEnteredHotWater";
  private static final String ADVANCEMENT_ID = "hotbath:foot_health";
  private static final Logger LOGGER = LogManager.getLogger();

  @SubscribeEvent
  public static void hotWaterEvent(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();
      boolean isInHotWater = CustomFluidHandler.isPlayerInHotWaterBlock(player);

      if (isInHotWater) {
        if (!playerData.getBoolean(HAS_ENTERED_HOT_WATER)) {
          int hotBathCount = playerData.getInt(HOT_WATER_ENTERED_NUMBER) + 1;
          playerData.putInt(HOT_WATER_ENTERED_NUMBER, hotBathCount);
          playerData.putBoolean(HAS_ENTERED_HOT_WATER, true);

          if (hotBathCount >= 10) {
            Advancement advancement =
                player
                    .getServer()
                    .getAdvancementManager()
                    .getAdvancement(ResourceLocation.tryCreate(ADVANCEMENT_ID));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.putInt(HOT_WATER_ENTERED_NUMBER, 0);
            }
          }
        }

        int hotBathTime = playerData.getInt(HOT_WATER_STAYED_TIME) + 1;
        playerData.putInt(HOT_WATER_STAYED_TIME, hotBathTime);

      } else {
        playerData.putBoolean(HAS_ENTERED_HOT_WATER, false);
      }

      LOGGER.info(
          player.getUniqueID()
              + " current hot bath count: "
              + playerData.getInt(HOT_WATER_ENTERED_NUMBER)
              + " and "
              + playerData.getInt(HOT_WATER_STAYED_TIME));
    }
  }
}
