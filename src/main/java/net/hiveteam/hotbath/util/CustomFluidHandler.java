package net.hiveteam.hotbath.util;

import net.hiveteam.hotbath.HotBath;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos; // This file is for repository reference only. It is not

// part of the mod.

public class CustomFluidHandler {

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
