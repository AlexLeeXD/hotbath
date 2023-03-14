package net.hiveteam.hotbath.item;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.fluid.HotBathFluidsRegister;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotBathItemRegister {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HotBath.MOD_ID);

    public static final RegistryObject<Item> HOT_WATER_BUCKET = ITEMS.register("hot_water_bucket",
            () -> new BucketItem(() -> HotBathFluidsRegister.HOT_WATER_FLUID.get(), new Item.Properties().group(HotBathItemGroup.HOT_BATH)));

    public static final RegistryObject<Item> HERBAL_BATH_BUCKET = ITEMS.register("herbal_bath_bucket",
            () -> new Item(new Item.Properties().group(HotBathItemGroup.HOT_BATH)));

    public static final RegistryObject<Item> HONEY_BATH_BUCKET = ITEMS.register("honey_bath_bucket",
            () -> new Item(new Item.Properties().group(HotBathItemGroup.HOT_BATH)));

    public static final RegistryObject<Item> MILK_BATH_BUCKET = ITEMS.register("milk_bath_bucket",
            () -> new Item(new Item.Properties().group(HotBathItemGroup.HOT_BATH)));

    public static final RegistryObject<Item> ROSE_BATH_BUCKET = ITEMS.register("rose_bath_bucket",
            () -> new Item(new Item.Properties().group(HotBathItemGroup.HOT_BATH)));

    public static final RegistryObject<Item> BATH_HERB = ITEMS.register("bath_herb",
            () -> new Item(new Item.Properties().group(HotBathItemGroup.HOT_BATH)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
