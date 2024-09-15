package com.crabmod.hotbath;

import com.crabmod.hotbath.advancements.AdvancementTrigger;
import com.crabmod.hotbath.fluid_blocks.*;
import com.crabmod.hotbath.register.BlocksRegister;
import com.crabmod.hotbath.register.FluidsRegister;
import com.crabmod.hotbath.register.ItemRegister;
import com.crabmod.hotbath.register.ParticleRegister;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HotBath.MOD_ID)
public class HotBath {
  // Directly reference a log4j logger.
  private static final Logger LOGGER = LogManager.getLogger();
  public static final String MOD_ID = "hotbath";

  public HotBath() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    ItemRegister.register(eventBus);
    BlocksRegister.register(eventBus);
    FluidsRegister.register(eventBus);
    ParticleRegister.register(eventBus);
    // Register the setup method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    // Register the enqueueIMC method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
    // Register the processIMC method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
    // Register the doClientStuff method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(final FMLCommonSetupEvent event) {
    // some preinit code
    LOGGER.info("HELLO FROM PREINIT");
    LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
  }

  private void doClientStuff(final FMLClientSetupEvent event) {
    // do something that can only be done on the client
    LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    CriteriaTriggers.register(new AdvancementTrigger("hotbath", "foot_health"));
    CriteriaTriggers.register(new AdvancementTrigger("hotbath", "milk_skin"));
    CriteriaTriggers.register(new AdvancementTrigger("hotbath", "chronic_invalid"));
    CriteriaTriggers.register(new AdvancementTrigger("hotbath", "rose_body_fragrance"));
  }

  private void enqueueIMC(final InterModEnqueueEvent event) {
    // some example code to dispatch IMC to another mod
    InterModComms.sendTo(
        "examplemod",
        "helloworld",
        () -> {
          LOGGER.info("Hello world from the MDK");
          return "Hello world";
        });
  }

  private void processIMC(final InterModProcessEvent event) {
    // some example code to receive and process InterModComms from other mods
    LOGGER.info(
        "Got IMC {}",
        event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(FMLServerStartingEvent event) {
    // do something when the server starts
    LOGGER.info("HELLO from server starting");
  }

  // You can use EventBusSubscriber to automatically subscribe events on the contained class (this
  // is subscribing to the MOD
  // Event bus for receiving Registry Events)
  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
      // register a new block here
      LOGGER.info("HELLO from Register Block");
    }
  }

  // Register the setup method for modloading
  @ObjectHolder("hotbath:hot_water_block")
  public static final HotWaterBlock HOT_WATER_BLOCK = null;

  @ObjectHolder("hotbath:herbal_bath_block")
  public static final HerbalBathBlock HERBAL_BATH_BLOCK = null;

  @ObjectHolder("hotbath:honey_bath_block")
  public static final HoneyBathBlock HONEY_BATH_BLOCK = null;

  @ObjectHolder("hotbath:milk_bath_block")
  public static final MilkBathBlock MILK_BATH_BLOCK = null;

  @ObjectHolder("hotbath:peony_bath_block")
  public static final PeonyBathBlock PEONY_BATH_BLOCK = null;

  @ObjectHolder("hotbath:rose_bath_block")
  public static final RoseBathBlock ROSE_BATH_BLOCK = null;
}
