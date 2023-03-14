package net.hiveteam.hotbath.fluid;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.block.ModBlocks;
import net.hiveteam.hotbath.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, HotBath.MOD_ID);

    public static final RegistryObject<FlowingFluid> HOT_WATER_FLUID = FLUIDS.register("hot_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.HOT_WATER_PROPERTIES));


    public static final RegistryObject<FlowingFluid> HOT_WATER_FLOWING = FLUIDS.register("hot_water_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.HOT_WATER_PROPERTIES));
    public static final ForgeFlowingFluid.Properties HOT_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> HOT_WATER_FLUID.get(), () -> HOT_WATER_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.BLOCK_WATER_AMBIENT).overlay(WATER_OVERLAY_RL)
            .color(0xffadd8e6)
    )
            .slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> ModFluids.HOT_WATER_BLOCK.get()).bucket(() -> ModItems.HOT_WATER_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> HOT_WATER_BLOCK = ModBlocks.BLOCKS.register("hot_water_block",
            () -> new FlowingFluidBlock(() -> ModFluids.HOT_WATER_FLUID.get(), AbstractBlock.Properties.create(Material.WATER)
//                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()
//                    -> ModFluids.HOT_WATER_BLOCK_SMOKE.get()));
//
                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()) {
                @Override
                public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
                    if (rand.nextInt(100) == 0) {
                        worldIn.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + rand.nextDouble(), (double) pos.getZ() + rand.nextDouble(), 0.0D, 0.05D, 0.0D);
                    }

                    if (rand.nextInt(100) == 0) {
                        worldIn.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + rand.nextDouble(), (double) pos.getZ() + rand.nextDouble(), 0.0D, 0.05D, 0.0D);
                    }
                }
            });








    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
