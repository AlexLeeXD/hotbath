package com.crabmod.hotbath.fluid_details;

import static com.crabmod.hotbath.fluid_details.FluidsColor.DEFAULT_FOG_COLOR;
import static com.crabmod.hotbath.fluid_details.FluidsColor.HOT_WATER_COLOR;

import com.crabmod.hotbath.HotBath;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HotbathFluidType {
  public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
  public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
  public static final ResourceLocation WATER_OVERLAY_RL =
      new ResourceLocation("block/water_overlay");

  public static final DeferredRegister<FluidType> FLUID_TYPES =
      DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, HotBath.MOD_ID);

  public static final RegistryObject<FluidType> HOTBATH_FLUID_TYPE =
      register(
          "hotbath_fluid_type",
          FluidType.Properties.create().lightLevel(2).density(15).viscosity(5));

  private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
    return FLUID_TYPES.register(
        name,
        () ->
            new BaseFluidType(
                WATER_STILL_RL,
                WATER_FLOWING_RL,
                WATER_OVERLAY_RL,
                HOT_WATER_COLOR,
                DEFAULT_FOG_COLOR,
                properties));
  }

  public static void register(IEventBus eventBus) {
    FLUID_TYPES.register(eventBus);
  }
}
