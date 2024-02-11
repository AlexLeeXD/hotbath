package com.crabmod.hotbath;

import com.crabmod.hotbath.registers.*;
import com.crabmod.hotbath.fluid_blocks.HerbalBathBlock;
import com.mojang.logging.LogUtils;
import java.util.stream.Collectors;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HotBath.MOD_ID)
public class HotBath {
  public static final String MOD_ID = "hotbath";

  // Directly reference a slf4j logger
  private static final Logger LOGGER = LogUtils.getLogger();

  public HotBath() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    modEventBus.addListener(this::setup);

    FluidsRegister.register(modEventBus);
    BlocksRegister.register(modEventBus);
    ItemRegister.register(modEventBus);
    ParticleRegister.register(modEventBus);

    // Register the setup method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    // Register the enqueueIMC method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
    // Register the processIMC method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
    // Register the doClientStuff method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void clientSetup(final FMLClientSetupEvent event) {
    // some preinit code
    LOGGER.info("HELLO FROM HotBath CLIENTSETUP");
    ItemBlockRenderTypes.setRenderLayer(FluidsRegister.HERBAL_BATH_BLOCK.get(), RenderType.translucent());
    ItemBlockRenderTypes.setRenderLayer(FluidsRegister.HERBAL_BATH_FLUID.get(), RenderType.translucent());
    ItemBlockRenderTypes.setRenderLayer(FluidsRegister.HERBAL_BATH_FLOWING.get(), RenderType.translucent());
  }

  private void setup(final FMLCommonSetupEvent event) {
    // some preinit code
    LOGGER.info("HELLO FROM HotBath PREINIT");
    LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
  }

  private void enqueueIMC(final InterModEnqueueEvent event) {
    // Some crabmod code to dispatch IMC to another mod
    InterModComms.sendTo(
        "hotbath",
        "helloworld",
        () -> {
          LOGGER.info("Hello world from the MDK");
          return "Hello world";
        });
  }

  private void processIMC(final InterModProcessEvent event) {
    // Some crabmod code to receive and process InterModComms from other mods
    LOGGER.info(
        "Got IMC {}",
        event.getIMCStream().map(m -> m.messageSupplier().get()).collect(Collectors.toList()));
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    // Do something when the server starts
    LOGGER.info("HELLO from HotBath onServerStarting");
  }

  // You can use EventBusSubscriber to automatically subscribe events on the contained class (this
  // is subscribing to the MOD
  // Event bus for receiving Registry Events)
  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
      // Register a new block here
      LOGGER.info("HELLO from HotBath onBlocksRegistry");
    }
  }

  // Register the setup method for modloading
//  @ObjectHolder("hotbath:hot_water_block")
//  public static final HotWaterBlock HOT_WATER_BLOCK = null;

  @ObjectHolder("hotbath:herbal_bath_block")
  public static final HerbalBathBlock HERBAL_BATH_BLOCK = null;
//
//  @ObjectHolder("hotbath:honey_bath_block")
//  public static final HoneyBathBlock HONEY_BATH_BLOCK = null;
//
//  @ObjectHolder("hotbath:milk_bath_block")
//  public static final MilkBathBlock MILK_BATH_BLOCK = null;
//
//  @ObjectHolder("hotbath:peony_bath_block")
//  public static final PeonyBathBlock PEONY_BATH_BLOCK = null;
//
//  @ObjectHolder("hotbath:rose_bath_block")
//  public static final RoseBathBlock ROSE_BATH_BLOCK = null;
}
