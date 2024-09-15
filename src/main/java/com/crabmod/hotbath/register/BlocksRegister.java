package com.crabmod.hotbath.register;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.fluid_blocks.*;
import com.crabmod.hotbath.item.CreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlocksRegister {

  @ObjectHolder(HotBath.MOD_ID)
  public static final Block HERBAL_BATH_BLOCK = null;
  public static final Block HONEY_BATH_BLOCK = null;
  public static final Block HOT_WATER_BLOCK = null;
  public static final Block MILK_BATH_BLOCK = null;
  public static final Block PEONY_BATH_BLOCK = null;
  public static final Block ROSE_BATH_BLOCK = null;

  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
    event.getRegistry().registerAll(
            new HerbalBathBlock(Block.Properties.create(Material.WATER))
                    .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "herbal_bath_block")),
            new HoneyBathBlock(Block.Properties.create(Material.WATER))
                    .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "honey_bath_block")),
            new HotWaterBlock(Block.Properties.create(Material.WATER))
                    .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "hot_water_block")),
            new MilkBathBlock(Block.Properties.create(Material.WATER))
                    .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "milk_bath_block")),
            new PeonyBathBlock(Block.Properties.create(Material.WATER))
                    .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "peony_bath_block")),
            new RoseBathBlock(Block.Properties.create(Material.WATER))
                    .setRegistryName(new ResourceLocation(HotBath.MOD_ID, "rose_bath_block"))
    );
  }

  @SubscribeEvent
  public static void registerBlockItems(RegistryEvent.Register<Item> event) {
    event
        .getRegistry()
        .registerAll(
            new ItemBlock(HERBAL_BATH_BLOCK, new Item.Properties().group(CreativeTab.HOT_BATH))
                .setRegistryName(HERBAL_BATH_BLOCK.getRegistryName()),
            new ItemBlock(HONEY_BATH_BLOCK, new Item.Properties().group(CreativeTab.HOT_BATH))
                .setRegistryName(HONEY_BATH_BLOCK.getRegistryName()),
            new ItemBlock(HOT_WATER_BLOCK, new Item.Properties().group(CreativeTab.HOT_BATH))
                .setRegistryName(HOT_WATER_BLOCK.getRegistryName()),
            new ItemBlock(MILK_BATH_BLOCK, new Item.Properties().group(CreativeTab.HOT_BATH))
                .setRegistryName(MILK_BATH_BLOCK.getRegistryName()),
            new ItemBlock(PEONY_BATH_BLOCK, new Item.Properties().group(CreativeTab.HOT_BATH))
                .setRegistryName(PEONY_BATH_BLOCK.getRegistryName()),
            new ItemBlock(ROSE_BATH_BLOCK, new Item.Properties().group(CreativeTab.HOT_BATH))
                .setRegistryName(ROSE_BATH_BLOCK.getRegistryName()));
  }
}
