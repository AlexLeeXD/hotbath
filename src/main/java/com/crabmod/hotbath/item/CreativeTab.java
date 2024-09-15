package com.crabmod.hotbath.item;

import com.crabmod.hotbath.register.ItemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTab {
  public static final ItemGroup HOT_BATH =
      new ItemGroup("hotBathTab") {
        @Override
        public ItemStack createIcon() {
          return new ItemStack(ItemRegister.HOT_WATER_BUCKET.get());
        }
      };
}
