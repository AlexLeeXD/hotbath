package com.crabmod.hotbath.fluid_details;

import static com.crabmod.hotbath.fluid_details.FluidsColor.DEFAULT_FOG_COLOR;

import com.crabmod.hotbath.HotBath;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HotbathFluidType {
  public static final ResourceLocation WATER_STILL_RL = ResourceLocation.parse("block/water_still");
  public static final ResourceLocation WATER_FLOWING_RL =
      ResourceLocation.parse("block/water_flow");
  public static final ResourceLocation WATER_OVERLAY_RL =
      ResourceLocation.parse("block/water_overlay");

  public static final DeferredRegister<FluidType> FLUID_TYPES =
      DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, HotBath.MOD_ID);

  public static RegistryObject<FluidType> getHotBathFluidType(
      String name,
      int color,
      ResourceLocation STILL_RL_TEXTURE,
      ResourceLocation FLOWING_RL_TEXTURE) {
    return register(
        name,
            FluidType.Properties.create()
                    .lightLevel(2)
                    .density(15)
                    .viscosity(5)
                    .canExtinguish(true)
                    .supportsBoating(true)
                    .fallDistanceModifier(0.0F)
                    .canDrown(true)
                    .canSwim(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true),
        color,
        STILL_RL_TEXTURE,
        FLOWING_RL_TEXTURE);
  }

  private static RegistryObject<FluidType> register(
      String name,
      FluidType.Properties properties,
      int FLUID_COLOR,
      ResourceLocation STILL_RL_TEXTURE,
      ResourceLocation FLOWING_RL_TEXTURE) {
    return FLUID_TYPES.register(
        name,
        () ->
            new BaseFluidType(
                STILL_RL_TEXTURE,
                FLOWING_RL_TEXTURE,
                WATER_OVERLAY_RL,
                FLUID_COLOR,
                DEFAULT_FOG_COLOR,
                properties));
  }

  public static void register(IEventBus eventBus) {
    FLUID_TYPES.register(eventBus);
  }
}
