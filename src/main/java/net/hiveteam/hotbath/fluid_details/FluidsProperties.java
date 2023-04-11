package net.hiveteam.hotbath.fluid_details;

import static net.hiveteam.hotbath.fluid_details.FluidsColor.*;
import static net.hiveteam.hotbath.fluid_details.FluidsTexture.*;
import static net.hiveteam.hotbath.register.FluidsRegister.*;
import static net.hiveteam.hotbath.register.ItemRegister.*;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

public class FluidsProperties {

  public static final ForgeFlowingFluid.Properties HERBAL_BATH_PROPERTIES =
      setHotBathProperties(
          HERBAL_BATH_FLUID,
          HERBAL_BATH_FLOWING,
          HERBAL_BATH_BLOCK,
          HERBAL_BATH_BUCKET,
          HERBAL_BATH_COLOR,
          HERBAL_BATH_STILL_TEXTURE,
          HERBAL_BATH_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties HONEY_BATH_PROPERTIES =
      setHotBathProperties(
          HONEY_BATH_FLUID,
          HONEY_BATH_FLOWING,
          HONEY_BATH_BLOCK,
          HONEY_BATH_BUCKET,
          HONEY_BATH_COLOR,
          HONEY_BATH_STILL_TEXTURE,
          HONEY_BATH_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties HOT_WATER_PROPERTIES =
      setHotBathProperties(
          HOT_WATER_FLUID,
          HOT_WATER_FLOWING,
          HOT_WATER_BLOCK,
          HOT_WATER_BUCKET,
          HOT_WATER_COLOR,
          HOT_WATER_STILL_TEXTURE,
          HOT_WATER_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties MILK_BATH_PROPERTIES =
      setHotBathProperties(
          MILK_BATH_FLUID,
          MILK_BATH_FLOWING,
          MILK_BATH_BLOCK,
          MILK_BATH_BUCKET,
          MILK_BATH_COLOR,
          MILK_BATH_STILL_TEXTURE,
          MILK_BATH_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties PEONY_BATH_PROPERTIES =
      setHotBathProperties(
          PEONY_BATH_FLUID,
          PEONY_BATH_FLOWING,
          PEONY_BATH_BLOCK,
          PEONY_BATH_BUCKET,
          PEONY_BATH_COLOR,
          PEONY_BATH_STILL_TEXTURE,
          PEONY_BATH_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties ROSE_BATH_PROPERTIES =
      setHotBathProperties(
          ROSE_BATH_FLUID,
          ROSE_BATH_FLOWING,
          ROSE_BATH_BLOCK,
          ROSE_BATH_BUCKET,
          ROSE_BATH_COLOR,
          ROSE_BATH_STILL_TEXTURE,
          ROSE_BATH_FLOWING_TEXTURE);

  public static ForgeFlowingFluid.Properties setHotBathProperties(
      RegistryObject<FlowingFluid> DEFAULT_HOT_BATH_FLUID,
      RegistryObject<FlowingFluid> DEFAULT_HOT_BATH_FLOWING,
      RegistryObject<FlowingFluidBlock> DEFAULT_HOT_BATH_BLOCK,
      RegistryObject<Item> DEFAULT_BUCKET,
      int FLUID_COLOR,
      ResourceLocation STILL_TEXTURE,
      ResourceLocation FLOWING_TEXTURE) {

    return new ForgeFlowingFluid.Properties(
            () -> DEFAULT_HOT_BATH_FLUID.get(),
            () -> DEFAULT_HOT_BATH_FLOWING.get(),
            FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE)
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
