package hivestandsteam.hotbath.fluid_details;

import static hivestandsteam.hotbath.register.FluidsRegister.*;

import hivestandsteam.hotbath.register.ItemRegister;
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
          ItemRegister.HERBAL_BATH_BUCKET,
          FluidsColor.HERBAL_BATH_COLOR,
          FluidsTexture.HERBAL_BATH_STILL_TEXTURE,
          FluidsTexture.HERBAL_BATH_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties HONEY_BATH_PROPERTIES =
      setHotBathProperties(
          HONEY_BATH_FLUID,
          HONEY_BATH_FLOWING,
          HONEY_BATH_BLOCK,
          ItemRegister.HONEY_BATH_BUCKET,
          FluidsColor.HONEY_BATH_COLOR,
          FluidsTexture.HONEY_BATH_STILL_TEXTURE,
          FluidsTexture.HONEY_BATH_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties HOT_WATER_PROPERTIES =
      setHotBathProperties(
          HOT_WATER_FLUID,
          HOT_WATER_FLOWING,
          HOT_WATER_BLOCK,
          ItemRegister.HOT_WATER_BUCKET,
          FluidsColor.HOT_WATER_COLOR,
          FluidsTexture.HOT_WATER_STILL_TEXTURE,
          FluidsTexture.HOT_WATER_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties MILK_BATH_PROPERTIES =
      setHotBathProperties(
          MILK_BATH_FLUID,
          MILK_BATH_FLOWING,
          MILK_BATH_BLOCK,
          ItemRegister.MILK_BATH_BUCKET,
          FluidsColor.MILK_BATH_COLOR,
          FluidsTexture.MILK_BATH_STILL_TEXTURE,
          FluidsTexture.MILK_BATH_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties PEONY_BATH_PROPERTIES =
      setHotBathProperties(
          PEONY_BATH_FLUID,
          PEONY_BATH_FLOWING,
          PEONY_BATH_BLOCK,
          ItemRegister.PEONY_BATH_BUCKET,
          FluidsColor.PEONY_BATH_COLOR,
          FluidsTexture.PEONY_BATH_STILL_TEXTURE,
          FluidsTexture.PEONY_BATH_FLOWING_TEXTURE);

  public static final ForgeFlowingFluid.Properties ROSE_BATH_PROPERTIES =
      setHotBathProperties(
          ROSE_BATH_FLUID,
          ROSE_BATH_FLOWING,
          ROSE_BATH_BLOCK,
          ItemRegister.ROSE_BATH_BUCKET,
          FluidsColor.ROSE_BATH_COLOR,
          FluidsTexture.ROSE_BATH_STILL_TEXTURE,
          FluidsTexture.ROSE_BATH_FLOWING_TEXTURE);

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
