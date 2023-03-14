package net.hiveteam.hotbath.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class HotBathItemGroup {

    public static final ItemGroup HOT_BATH = new ItemGroup("hotBathTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(HotBathItemRegister.HOT_WATER_BUCKET.get());
        }
    };
}
