package net.hiveteam.hotbath.events;

import static net.hiveteam.hotbath.util.EffectRemovalHandler.removeNegativeEffectsExceptUnluckAndBadOmen;
import static net.hiveteam.hotbath.util.HealthRegenHandler.regenHealth;
import static net.hiveteam.hotbath.util.HungerRegenHandler.regenHunger;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.util.CustomFluidHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class MilkBathEvents {
  private static final int TICK_NUMBER = 20;
  static final String MILK_BATH_ENTERED_NUMBER = "MilkBathEnteredNumber";
  static final String MILK_BATH_STAYED_TIME = "MilkBathStayedTime";
  static final String HAS_ENTERED_MILK_BATH = "HasEnteredMilkBath";
  static final String MILK_BATH_ADVANCEMENT_ID = "hotbath:milk_skin";
  private static final int MILK_BATH_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int MILK_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;

  // enter hot water block event
  @SubscribeEvent
  public static void enterMilkBathEvents(LivingEvent.LivingUpdateEvent event) {
    enterFluidEvents(
        event,
        MILK_BATH_ENTERED_COUNT_TRIGGER_NUMBER,
        MILK_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS,
        MILK_BATH_ENTERED_NUMBER,
        MILK_BATH_STAYED_TIME,
        HAS_ENTERED_MILK_BATH,
        MILK_BATH_ADVANCEMENT_ID);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingUpdateEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String enteredNumberInMilkBath,
      String milkBathStayedTime,
      String hasEnteredMilkBath,
      String milkBathAdvancementId) {
    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();
      boolean isInMilkBath = CustomFluidHandler.isPlayerInMilkBathBlock(player);

      if (isInMilkBath) {
        if (!playerData.getBoolean(hasEnteredMilkBath)) {
          int enteredCount = playerData.getInt(enteredNumberInMilkBath) + 1;
          playerData.putInt(enteredNumberInMilkBath, enteredCount);
          playerData.putBoolean(hasEnteredMilkBath, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            Advancement advancement =
                player
                    .getServer()
                    .getAdvancementManager()
                    .getAdvancement(ResourceLocation.tryCreate(milkBathAdvancementId));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.putInt(enteredNumberInMilkBath, 0);
            }
          }
        }

        int hotBathTime = playerData.getInt(milkBathStayedTime) + 1;
        playerData.putInt(milkBathStayedTime, hotBathTime);

        if (playerData.getInt(milkBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          regenHealth(0.25F, 2, player);
          removeNegativeEffectsExceptUnluckAndBadOmen(player);
        }

        if (playerData.getInt(milkBathStayedTime) >= 60 * TICK_NUMBER) {
          regenHunger(1, 15, player);
        }
      } else {
        playerData.putInt(milkBathStayedTime, 0);
        playerData.putBoolean(hasEnteredMilkBath, false);
      }
    }
  }
}
