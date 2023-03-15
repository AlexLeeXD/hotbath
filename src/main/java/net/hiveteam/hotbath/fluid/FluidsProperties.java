package net.hiveteam.hotbath.fluid;

import net.hiveteam.hotbath.item.ItemRegister;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

import static net.hiveteam.hotbath.fluid.FluidsRegister.*;

public class FluidsProperties {
    private static int FLUID_COLOR;
    public static final ForgeFlowingFluid.Properties HOT_WATER_PROPERTIES = getDefaultHotBathProperties(HOT_WATER_FLUID, HOT_WATER_FLOWING, HOT_WATER_BLOCK, FLUID_COLOR = 0xffadd8e6);

    public static ForgeFlowingFluid.Properties getDefaultHotBathProperties(RegistryObject<FlowingFluid> DEFAULT_HOT_BATH_FLUID, RegistryObject<FlowingFluid> DEFAULT_HOT_BATH_FLOWING, RegistryObject<FlowingFluidBlock> DEFAULT_HOT_BATH_BLOCK, int FLUID_COLOR) {
        final ForgeFlowingFluid.Properties DEFAULT_HOT_BATH_PROPERTIES = new ForgeFlowingFluid.Properties(
                () -> DEFAULT_HOT_BATH_FLUID.get(), () -> DEFAULT_HOT_BATH_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                .density(15).luminosity(2).viscosity(5).sound(SoundEvents.BLOCK_WATER_AMBIENT).overlay(WATER_OVERLAY_RL)
                .color(FLUID_COLOR)
        )
                .slopeFindDistance(2).levelDecreasePerBlock(2)
                .block(() -> DEFAULT_HOT_BATH_BLOCK.get()).bucket(() -> ItemRegister.HOT_WATER_BUCKET.get());

        return DEFAULT_HOT_BATH_PROPERTIES;
    }
}