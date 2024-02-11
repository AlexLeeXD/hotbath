package com.crabmod.hotbath.util;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CustomFluidHandler {

  public static boolean isPlayerInHotBathBlock(Player player) {
    BlockPos playerPos = player.blockPosition();
    BlockState stateAtPlayerPos = player.level.getBlockState(playerPos);

    return stateAtPlayerPos.getBlock() instanceof IHotbathBlock;
  }

  public static boolean isPlayerInHerbalBathBlock(Player player) {
    BlockPos playerPos = player.blockPosition();
    BlockState stateAtPlayerPos = player.level.getBlockState(playerPos);
    Block specificBlock = HotBath.HERBAL_BATH_BLOCK;
    return stateAtPlayerPos.getBlock() == specificBlock;
  }

  public static boolean isEntityInHerbalBathBlock(Entity entity) {
    BlockPos entityPos = entity.blockPosition();
    BlockState stateAtEntityPos = entity.level.getBlockState(entityPos);
    Block specificBlock = HotBath.HERBAL_BATH_BLOCK;
    return stateAtEntityPos.getBlock() == specificBlock;
  }
}
