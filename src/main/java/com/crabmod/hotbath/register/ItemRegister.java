package com.crabmod.hotbath.register;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.item.CreativeTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(HotBath.MOD_ID)
public class ItemRegister {

  // Declare items as public static fields
  public static final Item HOT_WATER_BUCKET = null;
  public static final Item HERBAL_BATH_BUCKET = null;
  public static final Item HONEY_BATH_BUCKET = null;
  public static final Item MILK_BATH_BUCKET = null;
  public static final Item PEONY_BATH_BUCKET = null;
  public static final Item ROSE_BATH_BUCKET = null;
  public static final Item BATH_HERB = null;

  @SubscribeEvent
  public static void onRegisterItems(RegistryEvent.Register<Item> event) {
    // Register items here
    event
        .getRegistry()
        .registerAll(
            new ItemBucket(
                    FluidsRegister.HOT_WATER_FLUID,
                    new Item.Properties().group(CreativeTab.HOT_BATH).maxStackSize(1))
                .setRegistryName("hot_water_bucket"),
            new ItemBucket(
                    FluidsRegister.HERBAL_BATH_FLUID,
                    new Item.Properties().group(CreativeTab.HOT_BATH).maxStackSize(1))
                .setRegistryName("herbal_bath_bucket"),
            new ItemBucket(
                    FluidsRegister.HONEY_BATH_FLUID,
                    new Item.Properties().group(CreativeTab.HOT_BATH).maxStackSize(1))
                .setRegistryName("honey_bath_bucket"),
            new ItemBucket(
                    FluidsRegister.MILK_BATH_FLUID,
                    new Item.Properties().group(CreativeTab.HOT_BATH).maxStackSize(1))
                .setRegistryName("milk_bath_bucket"),
            new ItemBucket(
                    FluidsRegister.PEONY_BATH_FLUID,
                    new Item.Properties().group(CreativeTab.HOT_BATH).maxStackSize(1))
                .setRegistryName("peony_bath_bucket"),
            new ItemBucket(
                    FluidsRegister.ROSE_BATH_FLUID,
                    new Item.Properties().group(CreativeTab.HOT_BATH).maxStackSize(1))
                .setRegistryName("rose_bath_bucket"),
            new Item(new Item.Properties().group(CreativeTab.HOT_BATH))
                .setRegistryName("bath_herb"));
  }
}
