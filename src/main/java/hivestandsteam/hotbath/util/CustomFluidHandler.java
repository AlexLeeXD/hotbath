package hivestandsteam.hotbath.util;

import hivestandsteam.hotbath.HotBath;
import hivestandsteam.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class CustomFluidHandler {

  public static boolean isPlayerInHotBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    HotBath.LOGGER.info("isPlayerInHotBathBlock: " + stateAtPlayerPos.getBlock());

    return stateAtPlayerPos.getBlock() instanceof IHotbathBlock;
  }

  public static boolean isPlayerInHotWaterBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    Block specificBlock = HotBath.HOT_WATER_BLOCK;
    return stateAtPlayerPos.getBlock() == specificBlock;
  }

  public static boolean isPlayerInHoneyBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    Block specificBlock = HotBath.HONEY_BATH_BLOCK;
    return stateAtPlayerPos.getBlock() == specificBlock;
  }

  public static boolean isPlayerInPeonyBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    Block specificBlock = HotBath.PEONY_BATH_BLOCK;
    return stateAtPlayerPos.getBlock() == specificBlock;
  }

  public static boolean isPlayerInMilkBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    Block specificBlock = HotBath.MILK_BATH_BLOCK;
    return stateAtPlayerPos.getBlock() == specificBlock;
  }

  public static boolean isPlayerInHerbalBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    Block specificBlock = HotBath.HERBAL_BATH_BLOCK;
    return stateAtPlayerPos.getBlock() == specificBlock;
  }

  public static boolean isEntityInHerbalBathBlock(Entity entity) {
    BlockPos entityPos = entity.getPosition();
    BlockState stateAtEntityPos = entity.world.getBlockState(entityPos);
    Block specificBlock = HotBath.HERBAL_BATH_BLOCK;
    return stateAtEntityPos.getBlock() == specificBlock;
  }

  public static boolean isPlayerInRoseBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    Block specificBlock = HotBath.ROSE_BATH_BLOCK;
    return stateAtPlayerPos.getBlock() == specificBlock;
  }
}
