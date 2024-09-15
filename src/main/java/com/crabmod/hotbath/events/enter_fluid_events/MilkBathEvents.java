package com.crabmod.hotbath.events.enter_fluid_events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.util.CustomFluidHandler;
import com.crabmod.hotbath.util.EffectRemovalHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static com.crabmod.hotbath.util.HealthRegenHandler.regenHealth;
import static com.crabmod.hotbath.util.HungerRegenHandler.regenHunger;

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

    if (event.getEntityLiving() instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
      NBTTagCompound playerData = player.getEntityData();
      boolean isInMilkBath = CustomFluidHandler.isPlayerInMilkBathBlock(player);

      if (isInMilkBath) {
        if (!playerData.getBoolean(hasEnteredMilkBath)) {
          int enteredCount = playerData.getInt(enteredNumberInMilkBath) + 1;
          playerData.setInt(enteredNumberInMilkBath, enteredCount);
          playerData.setBoolean(hasEnteredMilkBath, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            Advancement advancement =
                Objects.requireNonNull(player.getServer())
                    .getAdvancementManager()
                    .getAdvancement(new ResourceLocation(milkBathAdvancementId));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.setInt(enteredNumberInMilkBath, 0);
            }
          }
        }

        int hotBathTime = playerData.getInt(milkBathStayedTime) + 1;
        playerData.setInt(milkBathStayedTime, hotBathTime);
        regenHunger(1, 15, player);
        regenHealth(0.25F, 2, player);
        if (playerData.getInt(milkBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          EffectRemovalHandler.removeNegativeEffectsExceptUnluck(player);
        }
      } else {
        playerData.setInt(milkBathStayedTime, 0);
        playerData.setBoolean(hasEnteredMilkBath, false);
      }
    }
  }
}
