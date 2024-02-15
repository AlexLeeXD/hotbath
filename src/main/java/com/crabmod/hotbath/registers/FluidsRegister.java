package com.crabmod.hotbath.registers;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.HotWaterBlock;
import com.crabmod.hotbath.fluid_details.HotBathMaterials;
import com.crabmod.hotbath.fluid_details.HotbathFluidType;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** FluidsRegister */
public class FluidsRegister {
  public static final DeferredRegister<Fluid> FLUIDS =
      DeferredRegister.create(ForgeRegistries.FLUIDS, HotBath.MOD_ID);

  public static final RegistryObject<FlowingFluid> HOT_WATER_FLUID =
      FLUIDS.register(
          "hot_water_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsRegister.HOT_WATER_PROPERTIES));
  public static final RegistryObject<FlowingFluid> HOT_WATER_FLOWING =
      FLUIDS.register(
          "hot_water_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsRegister.HOT_WATER_PROPERTIES));

  public static final ForgeFlowingFluid.Properties HOT_WATER_PROPERTIES =
      new ForgeFlowingFluid.Properties(
              HotbathFluidType.HOTBATH_FLUID_TYPE, HOT_WATER_FLUID, HOT_WATER_FLOWING)
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(BlocksRegister.HOT_WATER_BLOCK)
          .bucket(ItemRegister.HOT_WATER_BUCKET);

  public static void register(IEventBus eventBus) {
    FLUIDS.register(eventBus);
  }
}
