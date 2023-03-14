package net.hiveteam.hotbath.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup HOT_BATH = new ItemGroup("hotBathTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.HOT_WATER_BUCKET.get());
        }
    };
}
