package net.hiveteam.hotbath.item;

import java.util.List;
import java.util.stream.Collectors;
import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.register.ItemRegister;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;

public class ItemGroup {
  public static final net.minecraft.item.ItemGroup HOT_BATH =
      new net.minecraft.item.ItemGroup("hotBathTab") {
        @Override
        public ItemStack createIcon() {
          return new ItemStack(ItemRegister.HOT_WATER_BUCKET.get());
        }

        @Override
        public void fill(net.minecraft.util.NonNullList<ItemStack> items) {
          // add bath_herb to the tail of the creative tab

          if (this == HOT_BATH) {
            List<ItemStack> list =
                ItemRegister.ITEMS.getEntries().stream()
                    .map(RegistryObject::get)
                    .filter(item -> item.getRegistryName().getNamespace().equals(HotBath.MOD_ID))
                    .map(item -> new ItemStack(item))
                    .collect(Collectors.toList());
            items.addAll(list);
          }
        }
      };
}
