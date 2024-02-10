package com.crabmod.hotbath.ModItems;


import java.util.function.Supplier;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.item.ItemGroup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksRegister {
  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, HotBath.MOD_ID);

  // Register the block and the item at the same time. So you don't have to do it twice.
  private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
  }

  private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
    ItemRegister.ITEMS.register(
        name, () -> new BlockItem(block.get(), new Item.Properties().tab(ItemGroup.HOT_BATH)));
  }

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }
}
