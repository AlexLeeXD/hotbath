package com.crabmod.hotbath.util;

import com.crabmod.hotbath.fluid_blocks.*;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class CustomFluidHandler {

  public static boolean isPlayerInHotBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);

    return stateAtPlayerPos.getBlock() instanceof IHotbathBlock;
  }

  public static boolean isPlayerInHotWaterBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof HotWaterBlock;
  }

  public static boolean isPlayerInHoneyBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof HoneyBathBlock;
  }

  public static boolean isPlayerInPeonyBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof PeonyBathBlock;
  }

  public static boolean isPlayerInMilkBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof MilkBathBlock;
  }

  public static boolean isPlayerInHerbalBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof HerbalBathBlock;
  }

  public static boolean isEntityInHerbalBathBlock(Entity entity) {
    BlockPos entityPos = entity.getPosition();
    BlockState stateAtEntityPos = entity.world.getBlockState(entityPos);
    return stateAtEntityPos.getBlock() instanceof HerbalBathBlock;
  }

  public static boolean isPlayerInRoseBathBlock(PlayerEntity player) {
    BlockPos playerPos = player.getPosition();
    BlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof RoseBathBlock;
  }
}
