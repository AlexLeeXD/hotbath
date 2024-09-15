package com.crabmod.hotbath.events.enter_fluid_events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.util.CustomFluidHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class HotWaterEvents {
  private static final int TICK_NUMBER = 20;
  static final String HOT_WATER_ENTERED_NUMBER = "HotWaterEnteredNumber";
  static final String HOT_WATER_STAYED_TIME = "HotWaterStayedTime";
  static final String HAS_ENTERED_HOT_WATER = "HasEnteredHotWater";
  static final String HOT_WATER_ADVANCEMENT_ID = "hotbath:foot_health";
  private static final int HOT_WATER_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int HOT_WATER_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 15;

  // enter hot water block event
  @SubscribeEvent
  public static void enterHotWaterEvents(LivingEvent.LivingUpdateEvent event) {
    enterFluidEvents(
        event,
        HOT_WATER_ENTERED_COUNT_TRIGGER_NUMBER,
        HOT_WATER_STAYED_EFFECT_TRIGGER_TIME_SECONDS,
        HOT_WATER_ENTERED_NUMBER,
        HOT_WATER_STAYED_TIME,
        HAS_ENTERED_HOT_WATER,
        HOT_WATER_ADVANCEMENT_ID);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingUpdateEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String hotWaterEnteredNumber,
      String hotWaterStayedTime,
      String hasEnteredHotWater,
      String hotWaterAdvancementId) {
    if (event.getEntityLiving() instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
      NBTTagCompound playerData = player.getEntityData();
      boolean isInHotWater = CustomFluidHandler.isPlayerInHotWaterBlock(player);

      if (isInHotWater) {
        if (!playerData.getBoolean(hasEnteredHotWater)) {
          int enteredCount = playerData.getInt(hotWaterEnteredNumber) + 1;
          playerData.setInt(hotWaterEnteredNumber, enteredCount);
          playerData.setBoolean(hasEnteredHotWater, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            Advancement advancement =
                Objects.requireNonNull(player.getServer())
                    .getAdvancementManager()
                    .getAdvancement(new ResourceLocation(hotWaterAdvancementId));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.setInt(hotWaterEnteredNumber, 0);
            }
          }
        }

        int hotBathTime = playerData.getInt(hotWaterStayedTime) + 1;
        playerData.setInt(hotWaterStayedTime, hotBathTime);

        if (playerData.getInt(hotWaterStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          player.addPotionEffect(
              new PotionEffect(MobEffects.SPEED, TICK_NUMBER * 20, 0, false, false));
        }
      } else {
        playerData.setInt(hotWaterStayedTime, 0);
        playerData.setBoolean(hasEnteredHotWater, false);
      }
    }
  }
}
