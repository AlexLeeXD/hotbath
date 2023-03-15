package net.hiveteam.hotbath.item.register;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.block.HotWaterBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.hiveteam.hotbath.util.FluidsParticles.*;
import static net.hiveteam.hotbath.fluid.FluidsProperties.*;


/**
 * 液体注册类
 */
public class FluidsRegister {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, HotBath.MOD_ID);

    // Register the fluid, flowing fluid and fluid block
    public static final RegistryObject<FlowingFluid> HOT_WATER_FLUID = FLUIDS.register("hot_water_fluid",
            () -> new ForgeFlowingFluid.Source(HOT_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HOT_WATER_FLOWING = FLUIDS.register("hot_water_flowing",
            () -> new ForgeFlowingFluid.Flowing(HOT_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> HOT_WATER_BLOCK = ModBlocks.BLOCKS.register("hot_water_block",
            () -> new HotWaterBlock(() -> HOT_WATER_FLUID.get(), AbstractBlock.Properties.create(Material.WATER)
                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
