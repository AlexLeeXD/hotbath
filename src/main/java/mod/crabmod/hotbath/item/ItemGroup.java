package mod.crabmod.hotbath.item;

import mod.crabmod.hotbath.HotBath;
import mod.crabmod.hotbath.registers.ItemRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemGroup {
  public static final DeferredRegister<CreativeModeTab> HOT_BATH_TABS =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HotBath.MOD_ID);

  public static final RegistryObject<CreativeModeTab> HOT_BATH_TAB =
      HOT_BATH_TABS.register(
          "hotbath_tab",
          () ->
              CreativeModeTab.builder()
                  .icon(() -> new ItemStack(ItemRegister.HOT_WATER_BUCKET.get()))
                  .title(Component.translatable("hotbath_tab"))
                  .displayItems(
                      (pParameters, pOutput) -> {
                        pOutput.accept(ItemRegister.HOT_WATER_BUCKET.get());
                        pOutput.accept(ItemRegister.HERBAL_BATH_BUCKET.get());
                        pOutput.accept(ItemRegister.HONEY_BATH_BUCKET.get());
                        pOutput.accept(ItemRegister.MILK_BATH_BUCKET.get());
                        pOutput.accept(ItemRegister.PEONY_BATH_BUCKET.get());
                        pOutput.accept(ItemRegister.ROSE_BATH_BUCKET.get());
                        pOutput.accept(ItemRegister.BATH_HERB.get());
                      })
                  .build());

  public static void register(IEventBus eventBus) {
    HOT_BATH_TABS.register(eventBus);
  }
}
