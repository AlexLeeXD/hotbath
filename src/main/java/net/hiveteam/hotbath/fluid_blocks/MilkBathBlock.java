package net.hiveteam.hotbath.fluid_blocks;

import static net.hiveteam.hotbath.util.EffectChangeUtil.removeNegativeEffectsExceptUnluck;
import static net.hiveteam.hotbath.util.ParticleGenerator.renderDefaultSteam;
import  net.hiveteam.hotbath.util.RegenHandler;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Milk Bath Block */
public class MilkBathBlock extends AbstractHotBathBlock {

  public MilkBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
    super(supplier, properties);
    stayTimeDataId="MilkBathStayedTimee";
    enteredCountDataId ="HasEnteredMilkBath";
    advancementId ="otbath:milk_skin";
    enteredCount = 100;
  }

  @Override
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
    renderDefaultSteam(worldIn, pos, rand);
  }

  @Override
  public void specialEffect(ServerPlayerEntity serverPlayerEntity, Integer stayTime,Integer enteredCount){
    if(RegenHandler.juedgeTime(stayTime,2)){
      RegenHandler.regenHealth(0.25F,  serverPlayerEntity);
    }
    if(RegenHandler.juedgeTime(stayTime,15)){
      RegenHandler.regenHunger(1,  serverPlayerEntity);
    }
    if (stayTime >= stayTime * TICK_NUMBER) {
      removeNegativeEffectsExceptUnluck(serverPlayerEntity);
    }
  }
}
