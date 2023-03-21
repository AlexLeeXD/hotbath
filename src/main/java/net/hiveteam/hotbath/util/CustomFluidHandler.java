package net.hiveteam.hotbath.util;

import java.util.HashMap;
import java.util.UUID;
import net.hiveteam.hotbath.HotBath;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos; // This file is for repository reference only. It is not
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// part of the mod.

public class CustomFluidHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TICKS_PER_SECOND = 20;
    private final HashMap<UUID, Integer> playerTimesInFluid = new HashMap<>();
    private final HashMap<UUID, Integer> playerEnterCount = new HashMap<>();

    public void handlePlayerInFluid(World world, PlayerEntity player) {
      if (!world.isRemote()) {
        UUID playerUUID = player.getUniqueID();
        boolean isPlayerInCustomFluid = isPlayerInHotWaterBlock(player);

        if (isPlayerInCustomFluid) {
          playerTimesInFluid.put(playerUUID, playerTimesInFluid.getOrDefault(playerUUID, 0) + 1);
          if (!playerEnterCount.containsKey(playerUUID)) {
            playerEnterCount.put(playerUUID, 1);
            LOGGER.info("Player {} entered the custom fluid",
   player.getDisplayName().getString());
          }
        } else {
          playerEnterCount.remove(playerUUID);
        }
      }
    }

    public static boolean isPlayerInHotWaterBlock(PlayerEntity player) {
      BlockPos playerPos = player.getPosition();
      BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);

    Block specificBlock = HotBath.HOT_WATER_BLOCK;
      return stateAtPlayerPos.getBlock() == specificBlock;
    }

    public boolean isPlayerInHotWaterBlockLongerThan(PlayerEntity player, int seconds) {
      return getPlayerTimeInCustomFluid(player) >= seconds;
    }

  public int getPlayerTimeInCustomFluid(PlayerEntity player) {
      return playerTimesInFluid.getOrDefault(player.getUniqueID(), 0) / TICKS_PER_SECOND;
    }

    public int getPlayerEnterCount(PlayerEntity player) {
      return playerEnterCount.getOrDefault(player.getUniqueID(), 0);
    }

    public void resetPlayerTimeInCustomFluid(PlayerEntity player) {
      playerTimesInFluid.put(player.getUniqueID(), 0);
    }

    public void resetPlayerEnterCount(PlayerEntity player) {
      playerEnterCount.put(player.getUniqueID(), 0);
    }
}
