package com.crabmod.hotbath.register;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.CustomFluid;
import com.crabmod.hotbath.fluid_blocks.CustomFluidBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FluidsRegister {

  @ObjectHolder("hotbath:herbal_bath_fluid")
  public static final Fluid HERBAL_BATH_FLUID = null;

  @ObjectHolder("hotbath:honey_bath_fluid")
  public static final Fluid HONEY_BATH_FLUID = null;

  @ObjectHolder("hotbath:hot_water_fluid")
  public static final Fluid HOT_WATER_FLUID = null;

  @ObjectHolder("hotbath:milk_bath_fluid")
  public static final Fluid MILK_BATH_FLUID = null;

  @ObjectHolder("hotbath:peony_bath_fluid")
  public static final Fluid PEONY_BATH_FLUID = null;

  @ObjectHolder("hotbath:rose_bath_fluid")
  public static final Fluid ROSE_BATH_FLUID = null;

  @ObjectHolder("hotbath:herbal_bath_block")
  public static final Block HERBAL_BATH_BLOCK = null;

  @ObjectHolder("hotbath:honey_bath_block")
  public static final Block HONEY_BATH_BLOCK = null;

  @ObjectHolder("hotbath:hot_water_block")
  public static final Block HOT_WATER_BLOCK = null;

  @ObjectHolder("hotbath:milk_bath_block")
  public static final Block MILK_BATH_BLOCK = null;

  @ObjectHolder("hotbath:peony_bath_block")
  public static final Block PEONY_BATH_BLOCK = null;

  @ObjectHolder("hotbath:rose_bath_block")
  public static final Block ROSE_BATH_BLOCK = null;

  @SubscribeEvent
  public static void registerFluids(RegistryEvent.Register<Fluid> event) {
    event
        .getRegistry()
        .registerAll(
            new CustomFluid(
                "herbal_bath_fluid",
                new ResourceLocation(HotBath.MOD_ID, "block/water_still"),
                new ResourceLocation(HotBath.MOD_ID, "block/water_flow")),
            new CustomFluid(
                "honey_bath_fluid",
                new ResourceLocation(HotBath.MOD_ID, "block/water_still"),
                new ResourceLocation(HotBath.MOD_ID, "block/water_flow")),
            new CustomFluid(
                "hot_water_fluid",
                new ResourceLocation(HotBath.MOD_ID, "block/water_still"),
                new ResourceLocation(HotBath.MOD_ID, "block/water_flow")),
            new CustomFluid(
                "milk_bath_fluid",
                new ResourceLocation(HotBath.MOD_ID, "block/water_still"),
                new ResourceLocation(HotBath.MOD_ID, "block/water_flow")),
            new CustomFluid(
                "peony_bath_fluid",
                new ResourceLocation(HotBath.MOD_ID, "block/water_still"),
                new ResourceLocation(HotBath.MOD_ID, "block/water_flow")),
            new CustomFluid(
                "rose_bath_fluid",
                new ResourceLocation(HotBath.MOD_ID, "block/water_still"),
                new ResourceLocation(HotBath.MOD_ID, "block/water_flow")));
  }

  @SubscribeEvent
  public static void registerFluidBlocks(RegistryEvent.Register<Block> event) {
    event
        .getRegistry()
        .registerAll(
            new CustomFluidBlock(HERBAL_BATH_FLUID, Block.Properties.create(Material.WATER))
                .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "herbal_bath_block")),
            new CustomFluidBlock(HONEY_BATH_FLUID, Block.Properties.create(Material.WATER))
                .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "honey_bath_block")),
            new CustomFluidBlock(HOT_WATER_FLUID, Block.Properties.create(Material.WATER))
                .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "hot_water_block")),
            new CustomFluidBlock(MILK_BATH_FLUID, Block.Properties.create(Material.WATER))
                .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "milk_bath_block")),
            new CustomFluidBlock(PEONY_BATH_FLUID, Block.Properties.create(Material.WATER))
                .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "peony_bath_block")),
            new CustomFluidBlock(ROSE_BATH_FLUID, Block.Properties.create(Material.WATER))
                .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "rose_bath_block")));
  }
}
