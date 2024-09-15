package com.crabmod.hotbath.register;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.item.ItemGroup;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegister {
  public static final DeferredRegister<Item> ITEMS =
      new DeferredRegister<>(ForgeRegistries.ITEMS, HotBath.MOD_ID);

  public static final RegistryObject<Item> HOT_WATER_BUCKET =
      ITEMS.register(
          "hot_water_bucket",
          () ->
              new ItemBucket(
                  FluidsRegister.HOT_WATER_FLUID,
                  new Item.Properties().group(ItemGroup.HOT_BATH).maxStackSize(1)));

  public static final RegistryObject<Item> HERBAL_BATH_BUCKET =
      ITEMS.register(
          "herbal_bath_bucket",
          () ->
              new ItemBucket(
                  FluidsRegister.HERBAL_BATH_FLUID,
                  new Item.Properties().group(ItemGroup.HOT_BATH).maxStackSize(1)));

  public static final RegistryObject<Item> HONEY_BATH_BUCKET =
      ITEMS.register(
          "honey_bath_bucket",
          () ->
              new ItemBucket(
                  FluidsRegister.HONEY_BATH_FLUID,
                  new Item.Properties().group(ItemGroup.HOT_BATH).maxStackSize(1)));

  public static final RegistryObject<Item> MILK_BATH_BUCKET =
      ITEMS.register(
          "milk_bath_bucket",
          () ->
              new ItemBucket(
                  FluidsRegister.MILK_BATH_FLUID,
                  new Item.Properties().group(ItemGroup.HOT_BATH).maxStackSize(1)));

  public static final RegistryObject<Item> PEONY_BATH_BUCKET =
      ITEMS.register(
          "peony_bath_bucket",
          () ->
              new ItemBucket(
                  FluidsRegister.PEONY_BATH_FLUID,
                  new Item.Properties().group(ItemGroup.HOT_BATH).maxStackSize(1)));

  public static final RegistryObject<Item> ROSE_BATH_BUCKET =
      ITEMS.register(
          "rose_bath_bucket",
          () ->
              new ItemBucket(
                  FluidsRegister.ROSE_BATH_FLUID,
                  new Item.Properties().group(ItemGroup.HOT_BATH).maxStackSize(1)));
  public static final RegistryObject<Item> BATH_HERB =
      ITEMS.register("bath_herb", () -> new Item(new Item.Properties().group(ItemGroup.HOT_BATH)));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
