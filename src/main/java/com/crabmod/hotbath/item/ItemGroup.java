package com.crabmod.hotbath.item;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.register.ItemRegister;
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
                    .filter(Objects::nonNull)
                    .filter(
                        item ->
                            Objects.requireNonNull(item.getRegistryName())
                                .getNamespace()
                                .equals(HotBath.MOD_ID))
                    .map(ItemStack::new)
                    .collect(Collectors.toList());
            items.addAll(list);
          }
        }
      };
}
