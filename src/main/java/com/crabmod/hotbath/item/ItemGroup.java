package com.crabmod.hotbath.item;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.registers.ItemRegister;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemGroup {
  public static CreativeModeTab HOT_BATH;

  @SubscribeEvent
  public static void registerItemGroup(CreativeModeTabEvent.Register event) {
    HOT_BATH =
        event.registerCreativeModeTab(
            new ResourceLocation(HotBath.MOD_ID, "hotBathTab"),
            builder ->
                builder
                    .icon(() -> new ItemStack(ItemRegister.HOT_WATER_BUCKET.get()))
                    .title(Component.translatable("itemGroup.hotBathTab")));
  }
}
