package com.crabmod.hotbath.register;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.*;
import com.crabmod.hotbath.fluid_details.FluidsProperties;
import com.crabmod.hotbath.fluid_details.HotBathMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidsRegister {
  public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
  public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
  public static final ResourceLocation WATER_OVERLAY_RL =
      new ResourceLocation("block/water_overlay");

  public static final DeferredRegister<Fluid> FLUIDS =
      new DeferredRegister<>(ForgeRegistries.FLUIDS, HotBath.MOD_ID);

  // Register the fluid, flowing fluid and fluid block

  public static final RegistryObject<FlowingFluid> HERBAL_BATH_FLUID =
      FLUIDS.register(
          "herbal_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsProperties.HERBAL_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> HERBAL_BATH_FLOWING =
      FLUIDS.register(
          "herbal_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsProperties.HERBAL_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluidBlock> HERBAL_BATH_BLOCK =
      BlocksRegister.BLOCKS.register(
          "herbal_bath_block",
          () ->
              new HerbalBathBlock(
                  HERBAL_BATH_FLUID,
                  Block.Properties.create(HotBathMaterials.HOTBATH_MATERIAL)
                      .doesNotBlockMovement()
                      .hardnessAndResistance(100f)
                      .noDrops()));

  public static final RegistryObject<FlowingFluid> HONEY_BATH_FLUID =
      FLUIDS.register(
          "honey_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsProperties.HONEY_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> HONEY_BATH_FLOWING =
      FLUIDS.register(
          "honey_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsProperties.HONEY_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluidBlock> HONEY_BATH_BLOCK =
      BlocksRegister.BLOCKS.register(
          "honey_bath_block",
          () ->
              new HoneyBathBlock(
                  HONEY_BATH_FLUID,
                  Block.Properties.create(HotBathMaterials.HOTBATH_MATERIAL)
                      .doesNotBlockMovement()
                      .hardnessAndResistance(100f)
                      .noDrops()));
  public static final RegistryObject<FlowingFluid> HOT_WATER_FLUID =
      FLUIDS.register(
          "hot_water_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsProperties.HOT_WATER_PROPERTIES));

  public static final RegistryObject<FlowingFluid> HOT_WATER_FLOWING =
      FLUIDS.register(
          "hot_water_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsProperties.HOT_WATER_PROPERTIES));

  public static final RegistryObject<FlowingFluidBlock> HOT_WATER_BLOCK =
      BlocksRegister.BLOCKS.register(
          "hot_water_block",
          () ->
              new HotWaterBlock(
                  HOT_WATER_FLUID,
                  Block.Properties.create(HotBathMaterials.HOTBATH_MATERIAL)
                      .doesNotBlockMovement()
                      .hardnessAndResistance(1000f)
                      .noDrops()));

  public static final RegistryObject<FlowingFluid> MILK_BATH_FLUID =
      FLUIDS.register(
          "milk_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsProperties.MILK_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> MILK_BATH_FLOWING =
      FLUIDS.register(
          "milk_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsProperties.MILK_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluidBlock> MILK_BATH_BLOCK =
      BlocksRegister.BLOCKS.register(
          "milk_bath_block",
          () ->
              new MilkBathBlock(
                  MILK_BATH_FLUID,
                  Block.Properties.create(HotBathMaterials.HOTBATH_MATERIAL)
                      .doesNotBlockMovement()
                      .hardnessAndResistance(100f)
                      .noDrops()));

  public static final RegistryObject<FlowingFluid> PEONY_BATH_FLUID =
      FLUIDS.register(
          "peony_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsProperties.PEONY_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> PEONY_BATH_FLOWING =
      FLUIDS.register(
          "peony_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsProperties.PEONY_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluidBlock> PEONY_BATH_BLOCK =
      BlocksRegister.BLOCKS.register(
          "peony_bath_block",
          () ->
              new PeonyBathBlock(
                  PEONY_BATH_FLUID,
                  Block.Properties.create(HotBathMaterials.HOTBATH_MATERIAL)
                      .doesNotBlockMovement()
                      .hardnessAndResistance(100f)
                      .noDrops()));

  public static final RegistryObject<FlowingFluid> ROSE_BATH_FLUID =
      FLUIDS.register(
          "rose_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsProperties.ROSE_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> ROSE_BATH_FLOWING =
      FLUIDS.register(
          "rose_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsProperties.ROSE_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluidBlock> ROSE_BATH_BLOCK =
      BlocksRegister.BLOCKS.register(
          "rose_bath_block",
          () ->
              new RoseBathBlock(
                  ROSE_BATH_FLUID,
                  Block.Properties.create(HotBathMaterials.HOTBATH_MATERIAL)
                      .doesNotBlockMovement()
                      .hardnessAndResistance(100f)
                      .noDrops()));

  public static void register(IEventBus eventBus) {
    FLUIDS.register(eventBus);
  }
}
