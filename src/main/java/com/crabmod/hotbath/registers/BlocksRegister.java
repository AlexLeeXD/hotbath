package com.crabmod.hotbath.registers;

import com.crabmod.hotbath.HotBath;
import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksRegister {
  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, HotBath.MOD_ID);

  public static final RegistryObject<LiquidBlock> HOT_WATER_BLOCK =
      BLOCKS.register(
          "hot_water_block",
          () ->
              new LiquidBlock(
                  FluidsRegister.HOT_WATER_FLUID, BlockBehaviour.Properties.copy(Blocks.WATER)));

  private static <T extends Block> RegistryObject<T> registerBlock(
      String name, Supplier<T> block, CreativeModeTab tab) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn, tab);
    return toReturn;
  }

  private static <T extends Block> RegistryObject<Item> registerBlockItem(
      String name, RegistryObject<T> block, CreativeModeTab tab) {
    return ItemRegister.ITEMS.register(
        name, () -> new BlockItem(block.get(), new Item.Properties()));
  }

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }
}
