package com.crabmod.hotbath.util;

import com.crabmod.hotbath.fluid_blocks.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class CustomFluidHandler {

  public static boolean isPlayerInHotBathBlock(EntityPlayer player) {
    BlockPos playerPos = player.getPosition();
    IBlockState stateAtPlayerPos = player.world.getBlockState(playerPos);

    return stateAtPlayerPos.getBlock() instanceof IHotbathBlock;
  }

  public static boolean isPlayerInHotWaterBlock(EntityPlayer player) {
    BlockPos playerPos = player.getPosition();
    IBlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof HotWaterBlock;
  }

  public static boolean isPlayerInHoneyBathBlock(EntityPlayer player) {
    BlockPos playerPos = player.getPosition();
    IBlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof HoneyBathBlock;
  }

  public static boolean isPlayerInPeonyBathBlock(EntityPlayer player) {
    BlockPos playerPos = player.getPosition();
    IBlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof PeonyBathBlock;
  }

  public static boolean isPlayerInMilkBathBlock(EntityPlayer player) {
    BlockPos playerPos = player.getPosition();
    IBlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof MilkBathBlock;
  }

  public static boolean isPlayerInHerbalBathBlock(EntityPlayer player) {
    BlockPos playerPos = player.getPosition();
    IBlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof HerbalBathBlock;
  }

  public static boolean isEntityInHerbalBathBlock(Entity entity) {
    BlockPos entityPos = entity.getPosition();
    IBlockState stateAtEntityPos = entity.world.getBlockState(entityPos);
    return stateAtEntityPos.getBlock() instanceof HerbalBathBlock;
  }

  public static boolean isPlayerInRoseBathBlock(EntityPlayer player) {
    BlockPos playerPos = player.getPosition();
    IBlockState stateAtPlayerPos = player.world.getBlockState(playerPos);
    return stateAtPlayerPos.getBlock() instanceof RoseBathBlock;
  }
}
