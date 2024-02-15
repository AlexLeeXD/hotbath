package com.crabmod.hotbath.registers;

import static com.crabmod.hotbath.fluid_details.FluidsColor.*;
import static com.crabmod.hotbath.fluid_details.HotbathFluidType.getHotBathFluidType;

import com.crabmod.hotbath.HotBath;
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
              getHotBathFluidType("hot_water_fluid_type", HOT_WATER_COLOR),
              HOT_WATER_FLUID,
              HOT_WATER_FLOWING)
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(BlocksRegister.HOT_WATER_BLOCK)
          .bucket(ItemRegister.HOT_WATER_BUCKET);

  public static final RegistryObject<FlowingFluid> HONEY_BATH_FLUID =
      FLUIDS.register(
          "honey_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsRegister.HONEY_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> HONEY_BATH_FLOWING =
      FLUIDS.register(
          "honey_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsRegister.HONEY_BATH_PROPERTIES));

  public static final ForgeFlowingFluid.Properties HONEY_BATH_PROPERTIES =
      new ForgeFlowingFluid.Properties(
              getHotBathFluidType("honey_bath_fluid_type", HONEY_BATH_COLOR),
              HONEY_BATH_FLUID,
              HONEY_BATH_FLOWING)
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(BlocksRegister.HONEY_BATH_BLOCK)
          .bucket(ItemRegister.HONEY_BATH_BUCKET);

  public static final RegistryObject<FlowingFluid> MILK_BATH_FLUID =
      FLUIDS.register(
          "milk_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsRegister.MILK_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> MILK_BATH_FLOWING =
      FLUIDS.register(
          "milk_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsRegister.MILK_BATH_PROPERTIES));

  public static final ForgeFlowingFluid.Properties MILK_BATH_PROPERTIES =
      new ForgeFlowingFluid.Properties(
              getHotBathFluidType("milk_bath_fluid_type", MILK_BATH_COLOR),
              MILK_BATH_FLUID,
              MILK_BATH_FLOWING)
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(BlocksRegister.MILK_BATH_BLOCK)
          .bucket(ItemRegister.MILK_BATH_BUCKET);

  public static final RegistryObject<FlowingFluid> PEONY_BATH_FLUID =
      FLUIDS.register(
          "peony_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsRegister.PEONY_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> PEONY_BATH_FLOWING =
      FLUIDS.register(
          "peony_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsRegister.PEONY_BATH_PROPERTIES));

  public static final ForgeFlowingFluid.Properties PEONY_BATH_PROPERTIES =
      new ForgeFlowingFluid.Properties(
              getHotBathFluidType("peony_bath_fluid_type", PEONY_BATH_COLOR),
              PEONY_BATH_FLUID,
              PEONY_BATH_FLOWING)
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(BlocksRegister.PEONY_BATH_BLOCK)
          .bucket(ItemRegister.PEONY_BATH_BUCKET);

  public static final RegistryObject<FlowingFluid> ROSE_BATH_FLUID =
      FLUIDS.register(
          "rose_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsRegister.ROSE_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> ROSE_BATH_FLOWING =
      FLUIDS.register(
          "rose_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsRegister.ROSE_BATH_PROPERTIES));

  public static final ForgeFlowingFluid.Properties ROSE_BATH_PROPERTIES =
      new ForgeFlowingFluid.Properties(
              getHotBathFluidType("rose_bath_fluid_type", ROSE_BATH_COLOR),
              ROSE_BATH_FLUID,
              ROSE_BATH_FLOWING)
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(BlocksRegister.ROSE_BATH_BLOCK)
          .bucket(ItemRegister.ROSE_BATH_BUCKET);

  public static final RegistryObject<FlowingFluid> HERBAL_BATH_FLUID =
      FLUIDS.register(
          "herbal_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsRegister.HERBAL_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> HERBAL_BATH_FLOWING =
      FLUIDS.register(
          "herbal_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsRegister.HERBAL_BATH_PROPERTIES));

  public static final ForgeFlowingFluid.Properties HERBAL_BATH_PROPERTIES =
      new ForgeFlowingFluid.Properties(
              getHotBathFluidType("herbal_bath_fluid_type", HERBAL_BATH_COLOR),
              HERBAL_BATH_FLUID,
              HERBAL_BATH_FLOWING)
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(BlocksRegister.HERBAL_BATH_BLOCK)
          .bucket(ItemRegister.HERBAL_BATH_BUCKET);

  public static void register(IEventBus eventBus) {
    FLUIDS.register(eventBus);
  }
}
