package com.crabmod.hotbath;

import com.crabmod.hotbath.item.ItemGroup;
import com.crabmod.hotbath.registers.ItemRegister;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HotBath.MOD_ID)
public class HotBath {
    public static final String MOD_ID = "hotbath";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HotBath() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
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
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}