package com.crabmod.hotbath.registers;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.*;
import com.crabmod.hotbath.fluid_details.HotbathFluidType;
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

  public static final RegistryObject<FlowingFluid> SOURCE_HOT_WATER =
      FLUIDS.register(
          "hot_water_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsRegister.HOT_WATER_FLUID_PROPERTIES));
  public static final RegistryObject<FlowingFluid> FLOWING_HOT_WATER =
      FLUIDS.register(
          "flowing_hot_water",
          () -> new ForgeFlowingFluid.Flowing(FluidsRegister.HOT_WATER_FLUID_PROPERTIES));

  public static final ForgeFlowingFluid.Properties HOT_WATER_FLUID_PROPERTIES =
      new ForgeFlowingFluid.Properties(
              HotbathFluidType.HOTBATH_FLUID_TYPE, SOURCE_HOT_WATER, FLOWING_HOT_WATER)
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(BlocksRegister.HOT_WATER_BLOCK)
          .bucket(ItemRegister.HOT_WATER_BUCKET);

  public static void register(IEventBus eventBus) {
    FLUIDS.register(eventBus);
  }
}
