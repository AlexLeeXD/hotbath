package net.hiveteam.hotbath.events;

import javax.annotation.Nullable;
import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.register.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID, bus = Bus.FORGE)
public class TradeEventHandler {
  @SubscribeEvent
  public static void onVillagerTradesEvent(VillagerTradesEvent event) {
    if (event.getType() == VillagerProfession.FARMER) {
      event
          .getTrades()
          .get(1)
          .add(new ItemsForEmeraldsTrade(ItemRegister.BATH_HERB.get(), 10, 1, 16, 2, 0.05F));
    }
  }

  @SubscribeEvent
  public static void onWanderingTraderEvent(WandererTradesEvent event) {
    event
        .getGenericTrades()
        .add(new ItemsForEmeraldsTrade(ItemRegister.BATH_HERB.get(), 15, 1, 12, 1, 0.05F));
  }

  public static class ItemsForEmeraldsTrade implements ITrade {
    private final Item sellingItem;
    private final int emeraldCount;
    private final int sellingItemCount;
    private final int maxUses;
    private final int xpValue;
    private final float priceMultiplier;

    public ItemsForEmeraldsTrade(
        Item sellingItem,
        int emeraldCount,
        int sellingItemCount,
        int maxUses,
        int xpValue,
        float priceMultiplier) {
      this.sellingItem = sellingItem;
      this.emeraldCount = emeraldCount;
      this.sellingItemCount = sellingItemCount;
      this.maxUses = maxUses;
      this.xpValue = xpValue;
      this.priceMultiplier = priceMultiplier;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity trader, java.util.Random rand) {
      return new MerchantOffer(
          new ItemStack(Items.EMERALD, emeraldCount),
          new ItemStack(sellingItem, sellingItemCount),
          maxUses,
          xpValue,
          priceMultiplier);
    }
  }
}
