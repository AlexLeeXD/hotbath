package net.hiveteam.hotbath.fluid_details;

import static net.hiveteam.hotbath.fluid_details.FluidsColor.*;
import static net.hiveteam.hotbath.register.FluidsRegister.*;
import static net.hiveteam.hotbath.register.ItemRegister.*;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

public class FluidsProperties {

  public static final ForgeFlowingFluid.Properties HERBAL_BATH_PROPERTIES =
      getDefaultHotBathProperties(
          HERBAL_BATH_FLUID,
          HERBAL_BATH_FLOWING,
          HERBAL_BATH_BLOCK,
          HERBAL_BATH_BUCKET,
          HERBAL_BATH_COLOR);

  public static final ForgeFlowingFluid.Properties HONEY_BATH_PROPERTIES =
      getDefaultHotBathProperties(
          HONEY_BATH_FLUID,
          HONEY_BATH_FLOWING,
          HONEY_BATH_BLOCK,
          HONEY_BATH_BUCKET,
          HONEY_BATH_COLOR);

  public static final ForgeFlowingFluid.Properties HOT_WATER_PROPERTIES =
      getDefaultHotBathProperties(
          HOT_WATER_FLUID, HOT_WATER_FLOWING, HOT_WATER_BLOCK, HOT_WATER_BUCKET, HOT_WATER_COLOR);

  public static final ForgeFlowingFluid.Properties MILK_BATH_PROPERTIES =
      getDefaultHotBathProperties(
          MILK_BATH_FLUID, MILK_BATH_FLOWING, MILK_BATH_BLOCK, MILK_BATH_BUCKET, MILK_BATH_COLOR);

  public static final ForgeFlowingFluid.Properties PEONY_BATH_PROPERTIES =
      getDefaultHotBathProperties(
          PEONY_BATH_FLUID,
          PEONY_BATH_FLOWING,
          PEONY_BATH_BLOCK,
          PEONY_BATH_BUCKET,
          PEONY_BATH_COLOR);

  public static final ForgeFlowingFluid.Properties ROSE_BATH_PROPERTIES =
      getDefaultHotBathProperties(
          ROSE_BATH_FLUID, ROSE_BATH_FLOWING, ROSE_BATH_BLOCK, ROSE_BATH_BUCKET, ROSE_BATH_COLOR);

  public static ForgeFlowingFluid.Properties getDefaultHotBathProperties(
      RegistryObject<FlowingFluid> DEFAULT_HOT_BATH_FLUID,
      RegistryObject<FlowingFluid> DEFAULT_HOT_BATH_FLOWING,
      RegistryObject<FlowingFluidBlock> DEFAULT_HOT_BATH_BLOCK,
      RegistryObject<Item> DEFAULT_BUCKET,
      int FLUID_COLOR) {

    return new ForgeFlowingFluid.Properties(
            () -> DEFAULT_HOT_BATH_FLUID.get(),
            () -> DEFAULT_HOT_BATH_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                .density(15)
                .luminosity(2)
                .viscosity(5)
                .sound(SoundEvents.BLOCK_WATER_AMBIENT)
                .overlay(WATER_OVERLAY_RL)
                .color(FLUID_COLOR))
        .slopeFindDistance(2)
        .levelDecreasePerBlock(1)
        .block(() -> DEFAULT_HOT_BATH_BLOCK.get())
        .bucket(() -> DEFAULT_BUCKET.get());
  }
}
