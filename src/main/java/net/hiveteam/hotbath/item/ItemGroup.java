package net.hiveteam.hotbath.item;

import net.hiveteam.hotbath.register.ItemRegister;
import net.minecraft.item.ItemStack;

public class ItemGroup {

  public static final net.minecraft.item.ItemGroup HOT_BATH =
      new net.minecraft.item.ItemGroup("hotBathTab") {
        @Override
        public ItemStack createIcon() {
          return new ItemStack(ItemRegister.HOT_WATER_BUCKET.get());
        }
      };
}
