package com.crabmod.hotbath.events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.register.ItemRegister;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import static com.crabmod.hotbath.register.ItemRegister.BATH_HERB;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class TradeEventHandler {

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void onVillagerSpawn(LivingSpawnEvent.SpecialSpawn event) {
    if (event.getEntity() instanceof EntityVillager) {
      EntityVillager villager = (EntityVillager) event.getEntity();
      if (villager.getProfessionForge() == VillagerRegistry.getById(0)) { // Check if the villager is a Farmer
        addCustomTrades(villager);
      }
    }
  }

  private static void addCustomTrades(EntityVillager villager) {
    MerchantRecipeList trades = villager.getRecipes(null);

    if (trades != null) {
      trades.add(new MerchantRecipe(
              new ItemStack(Items.EMERALD, 10),
              new ItemStack(BATH_HERB, 1)
      ));
    }
  }
}
