package com.crabmod.hotbath.registers;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.HerbalBathBlock;
import com.crabmod.hotbath.fluid_details.FluidsProperties;
import com.crabmod.hotbath.fluid_details.HotBathMaterials;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** FluidsRegister */
public class FluidsRegister {
  public static final ModelResourceLocation WATER_STILL_RL =
      new ModelResourceLocation("block/water_still");
  public static final ModelResourceLocation WATER_FLOWING_RL =
      new ModelResourceLocation("block/water_flow");
  public static final ModelResourceLocation WATER_OVERLAY_RL =
      new ModelResourceLocation("block/water_overlay");

  public static final DeferredRegister<Fluid> FLUIDS =
      DeferredRegister.create(ForgeRegistries.FLUIDS, HotBath.MOD_ID);

  // Register the fluid, flowing fluid and fluid block

  public static final RegistryObject<FlowingFluid> HERBAL_BATH_FLUID =
      FLUIDS.register(
          "herbal_bath_fluid",
          () -> new ForgeFlowingFluid.Source(FluidsProperties.HERBAL_BATH_PROPERTIES));

  public static final RegistryObject<FlowingFluid> HERBAL_BATH_FLOWING =
      FLUIDS.register(
          "herbal_bath_flowing",
          () -> new ForgeFlowingFluid.Flowing(FluidsProperties.HERBAL_BATH_PROPERTIES));
  public static final ForgeFlowingFluid.Properties HERBAL_BATH_PROPERTIES =
      new ForgeFlowingFluid.Properties(
              () -> HERBAL_BATH_FLUID.get(),
              () -> HERBAL_BATH_FLOWING.get(),
              FluidAttributes.builder(
                      new ResourceLocation(HotBath.MOD_ID, "block/herbal_bath_still"),
                      new ResourceLocation(HotBath.MOD_ID, "block/herbal_bath_flowing"))
                  .density(15)
                  .luminosity(2)
                  .viscosity(5)
                  .sound(SoundEvents.HONEY_DRINK)
                  .overlay(new ResourceLocation("block/water_overlay"))
                  .color(0xbffcba03))
          .slopeFindDistance(2)
          .levelDecreasePerBlock(2)
          .block(() -> FluidsRegister.HERBAL_BATH_BLOCK.get())
          .bucket(() -> ItemRegister.HERBAL_BATH_BUCKET.get());

  public static final RegistryObject<LiquidBlock> HERBAL_BATH_BLOCK =
      BlocksRegister.BLOCKS.register(
          "herbal_bath_block",
          () ->
              new HerbalBathBlock(
                  () -> FluidsRegister.HERBAL_BATH_FLUID.get(),
                  BlockBehaviour.Properties.of(HotBathMaterials.HOTBATH_MATERIAL)
                      .noCollission()
                      .strength(100.0F)
                      .noDrops()));

  //
  //  public static final RegistryObject<FlowingFluid> HONEY_BATH_FLUID =
  //      FLUIDS.register(
  //          "honey_bath_fluid",
  //          () -> new
  // ForgeFlowingFluid.Source(hivestandsteam.hotbath.fluid_details.FluidsProperties.HONEY_BATH_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluid> HONEY_BATH_FLOWING =
  //      FLUIDS.register(
  //          "honey_bath_flowing",
  //          () -> new
  // ForgeFlowingFluid.Flowing(hivestandsteam.hotbath.fluid_details.FluidsProperties.HONEY_BATH_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluidBlock> HONEY_BATH_BLOCK =
  //      BlocksRegister.BLOCKS.register(
  //          "honey_bath_block",
  //          () ->
  //              new HoneyBathBlock(
  //                  () -> HONEY_BATH_FLUID.get(),
  //
  // AbstractBlock.Properties.create(hivestandsteam.hotbath.fluid_details.HotBathMaterials.HOTBATH_MATERIAL)
  //                      .doesNotBlockMovement()
  //                      .hardnessAndResistance(100f)
  //                      .noDrops()));
  //  public static final RegistryObject<FlowingFluid> HOT_WATER_FLUID =
  //      FLUIDS.register(
  //          "hot_water_fluid",
  //          () -> new
  // ForgeFlowingFluid.Source(hivestandsteam.hotbath.fluid_details.FluidsProperties.HOT_WATER_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluid> HOT_WATER_FLOWING =
  //      FLUIDS.register(
  //          "hot_water_flowing",
  //          () -> new
  // ForgeFlowingFluid.Flowing(hivestandsteam.hotbath.fluid_details.FluidsProperties.HOT_WATER_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluidBlock> HOT_WATER_BLOCK =
  //      BlocksRegister.BLOCKS.register(
  //          "hot_water_block",
  //          () ->
  //              new HotWaterBlock(
  //                  () -> HOT_WATER_FLUID.get(),
  //
  // AbstractBlock.Properties.create(hivestandsteam.hotbath.fluid_details.HotBathMaterials.HOTBATH_MATERIAL)
  //                      .doesNotBlockMovement()
  //                      .hardnessAndResistance(1000f)
  //                      .noDrops()));
  //
  //  public static final RegistryObject<FlowingFluid> MILK_BATH_FLUID =
  //      FLUIDS.register(
  //          "milk_bath_fluid",
  //          () -> new
  // ForgeFlowingFluid.Source(hivestandsteam.hotbath.fluid_details.FluidsProperties.MILK_BATH_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluid> MILK_BATH_FLOWING =
  //      FLUIDS.register(
  //          "milk_bath_flowing",
  //          () -> new
  // ForgeFlowingFluid.Flowing(hivestandsteam.hotbath.fluid_details.FluidsProperties.MILK_BATH_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluidBlock> MILK_BATH_BLOCK =
  //      BlocksRegister.BLOCKS.register(
  //          "milk_bath_block",
  //          () ->
  //              new MilkBathBlock(
  //                  () -> MILK_BATH_FLUID.get(),
  //
  // AbstractBlock.Properties.create(hivestandsteam.hotbath.fluid_details.HotBathMaterials.HOTBATH_MATERIAL)
  //                      .doesNotBlockMovement()
  //                      .hardnessAndResistance(100f)
  //                      .noDrops()));
  //
  //  public static final RegistryObject<FlowingFluid> PEONY_BATH_FLUID =
  //      FLUIDS.register(
  //          "peony_bath_fluid",
  //          () -> new
  // ForgeFlowingFluid.Source(hivestandsteam.hotbath.fluid_details.FluidsProperties.PEONY_BATH_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluid> PEONY_BATH_FLOWING =
  //      FLUIDS.register(
  //          "peony_bath_flowing",
  //          () -> new
  // ForgeFlowingFluid.Flowing(hivestandsteam.hotbath.fluid_details.FluidsProperties.PEONY_BATH_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluidBlock> PEONY_BATH_BLOCK =
  //      BlocksRegister.BLOCKS.register(
  //          "peony_bath_block",
  //          () ->
  //              new PeonyBathBlock(
  //                  () -> PEONY_BATH_FLUID.get(),
  //
  // AbstractBlock.Properties.create(hivestandsteam.hotbath.fluid_details.HotBathMaterials.HOTBATH_MATERIAL)
  //                      .doesNotBlockMovement()
  //                      .hardnessAndResistance(100f)
  //                      .noDrops()));
  //
  //  public static final RegistryObject<FlowingFluid> ROSE_BATH_FLUID =
  //      FLUIDS.register(
  //          "rose_bath_fluid",
  //          () -> new
  // ForgeFlowingFluid.Source(hivestandsteam.hotbath.fluid_details.FluidsProperties.ROSE_BATH_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluid> ROSE_BATH_FLOWING =
  //      FLUIDS.register(
  //          "rose_bath_flowing",
  //          () -> new
  // ForgeFlowingFluid.Flowing(hivestandsteam.hotbath.fluid_details.FluidsProperties.ROSE_BATH_PROPERTIES));
  //
  //  public static final RegistryObject<FlowingFluidBlock> ROSE_BATH_BLOCK =
  //      BlocksRegister.BLOCKS.register(
  //          "rose_bath_block",
  //          () ->
  //              new RoseBathBlock(
  //                  () -> ROSE_BATH_FLUID.get(),
  //
  // AbstractBlock.Properties.create(hivestandsteam.hotbath.fluid_details.HotBathMaterials.HOTBATH_MATERIAL)
  //                      .doesNotBlockMovement()
  //                      .hardnessAndResistance(100f)
  //                      .noDrops()));

  public static void register(IEventBus eventBus) {
    FLUIDS.register(eventBus);
  }
}
