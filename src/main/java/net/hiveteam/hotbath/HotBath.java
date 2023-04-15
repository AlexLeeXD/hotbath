package net.hiveteam.hotbath;

import java.util.stream.Collectors;
import net.hiveteam.hotbath.fluid_blocks.*;
import net.hiveteam.hotbath.register.BlocksRegister;
import net.hiveteam.hotbath.register.FluidsRegister;
import net.hiveteam.hotbath.register.ItemRegister;
import net.hiveteam.hotbath.register.ParticleRegister;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HotBath.MOD_ID)
public class HotBath {
  // Directly reference a log4j logger.
  public static final Logger LOGGER = LogManager.getLogger();

  public static final String MOD_ID = "hotbath";

  public HotBath() {

    // Register the setup method for modloading
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    ItemRegister.register(eventBus);
    BlocksRegister.register(eventBus);
    FluidsRegister.register(eventBus);
    ParticleRegister.register(eventBus);
    eventBus.addListener(this::setup);
    // Register the enqueueIMC method for modloading
    eventBus.addListener(this::enqueueIMC);
    // Register the processIMC method for modloading
    eventBus.addListener(this::processIMC);
    // Register the doClientStuff method for modloading
    eventBus.addListener(this::doClientStuff);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(final FMLCommonSetupEvent event) {
    // some preinit code
    LOGGER.info("HELLO FROM PREINIT");
    LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
  }

  private void doClientStuff(final FMLClientSetupEvent event) {
    event.enqueueWork(
        () -> {
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HERBAL_BATH_BLOCK.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HERBAL_BATH_FLOWING.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HERBAL_BATH_FLUID.get(), RenderType.getTranslucent());

          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HONEY_BATH_BLOCK.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HONEY_BATH_FLOWING.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HONEY_BATH_FLUID.get(), RenderType.getTranslucent());

          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HOT_WATER_FLUID.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HOT_WATER_BLOCK.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.HOT_WATER_FLOWING.get(), RenderType.getTranslucent());

          RenderTypeLookup.setRenderLayer(
              FluidsRegister.MILK_BATH_FLUID.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.MILK_BATH_BLOCK.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.MILK_BATH_FLOWING.get(), RenderType.getTranslucent());

          RenderTypeLookup.setRenderLayer(
              FluidsRegister.PEONY_BATH_BLOCK.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.PEONY_BATH_FLOWING.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.PEONY_BATH_FLUID.get(), RenderType.getTranslucent());

          RenderTypeLookup.setRenderLayer(
              FluidsRegister.ROSE_BATH_BLOCK.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.ROSE_BATH_FLOWING.get(), RenderType.getTranslucent());
          RenderTypeLookup.setRenderLayer(
              FluidsRegister.ROSE_BATH_FLUID.get(), RenderType.getTranslucent());
        });
  }

  private void enqueueIMC(final InterModEnqueueEvent event) {
    // some example code to dispatch IMC to another mod
    InterModComms.sendTo(
        "hotbath",
        "helloworld",
        () -> {
          LOGGER.info("Hello world from Hot Bath");
          return "Hello world from Hot Bath";
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
    LOGGER.info("HELLO from Hot Bath");
  }

  // You can use EventBusSubscriber to automatically subscribe events on the contained class (this
  // is subscribing to the MOD
  // Event bus for receiving Registry Events)
  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
      // register a new block here
      LOGGER.info("HELLO from Hot Bath");
    }
  }

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
