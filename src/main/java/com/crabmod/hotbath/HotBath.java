package com.crabmod.hotbath;

import com.crabmod.hotbath.fluid_blocks.*;
import com.crabmod.hotbath.fluid_details.HotbathFluidType;
import com.crabmod.hotbath.item.ItemGroup;
import com.crabmod.hotbath.registers.BlocksRegister;
import com.crabmod.hotbath.registers.FluidsRegister;
import com.crabmod.hotbath.registers.ItemRegister;
import com.crabmod.hotbath.registers.ParticleRegister;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HotBath.MOD_ID)
public class HotBath {
  public static final String MOD_ID = "hotbath";
  public static final Logger LOGGER = LogUtils.getLogger();

  public HotBath() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    modEventBus.addListener(this::commonSetup);
    ItemGroup.register(modEventBus);
    FluidsRegister.register(modEventBus);
    BlocksRegister.register(modEventBus);
    ItemRegister.register(modEventBus);
    ParticleRegister.register(modEventBus);
    HotbathFluidType.register(modEventBus);
    MinecraftForge.EVENT_BUS.register(this);
    modEventBus.addListener(this::addCreative);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {}

  private void addCreative(BuildCreativeModeTabContentsEvent event) {
    if (event.getTab() == ItemGroup.HOT_BATH_TAB.get()) {
      event.accept(ItemRegister.HERBAL_BATH_BUCKET);
      event.accept(ItemRegister.HONEY_BATH_BUCKET);
      event.accept(ItemRegister.HOT_WATER_BUCKET);
      event.accept(ItemRegister.MILK_BATH_BUCKET);
      event.accept(ItemRegister.PEONY_BATH_BUCKET);
      event.accept(ItemRegister.ROSE_BATH_BUCKET);
      event.accept(ItemRegister.BATH_HERB);
    }
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {}

  // You can use EventBusSubscriber to automatically register all static methods in the class
  // annotated with @SubscribeEvent
  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.HOT_WATER_FLUID.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.HOT_WATER_FLOWING.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.HONEY_BATH_FLUID.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.HONEY_BATH_FLOWING.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.MILK_BATH_FLUID.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.MILK_BATH_FLOWING.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.PEONY_BATH_FLUID.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.PEONY_BATH_FLOWING.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.ROSE_BATH_FLUID.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.ROSE_BATH_FLOWING.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.HERBAL_BATH_FLUID.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(
          FluidsRegister.HERBAL_BATH_FLOWING.get(), RenderType.translucent());
    }
  }

  // Register the setup method for modloading
  @ObjectHolder(registryName = "minecraft:block", value = "hotbath:hot_water_block")
  public static final HotWaterBlock HOT_WATER_BLOCK = null;

  @ObjectHolder(registryName = "minecraft:block", value = "hotbath:herbal_bath_block")
  public static final HerbalBathBlock HERBAL_BATH_BLOCK = null;

  @ObjectHolder(registryName = "minecraft:block", value = "hotbath:honey_bath_block")
  public static final HoneyBathBlock HONEY_BATH_BLOCK = null;

  @ObjectHolder(registryName = "minecraft:block", value = "hotbath:milk_bath_block")
  public static final MilkBathBlock MILK_BATH_BLOCK = null;

  @ObjectHolder(registryName = "minecraft:block", value = "hotbath:peony_bath_block")
  public static final PeonyBathBlock PEONY_BATH_BLOCK = null;

  @ObjectHolder(registryName = "minecraft:block", value = "hotbath:rose_bath_block")
  public static final RoseBathBlock ROSE_BATH_BLOCK = null;
}
