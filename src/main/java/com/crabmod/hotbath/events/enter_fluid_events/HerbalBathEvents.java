package com.crabmod.hotbath.events.enter_fluid_events;

import com.crabmod.hotbath.HotBath;
import com.crabmod.hotbath.util.CustomFluidHandler;
import com.crabmod.hotbath.util.EffectRemovalHandler;
import com.crabmod.hotbath.util.HealthRegenHandler;
import com.crabmod.hotbath.util.ResistanceBoostHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class HerbalBathEvents {
  private static final int TICK_NUMBER = 20;
  static final String HERBAL_BATH_ENTERED_NUMBER = "HerbalBathEnteredNumber";
  static final String HERBAL_BATH_STAYED_TIME = "HerbalBathStayedTime";
  static final String HAS_ENTERED_HERBAL_BATH = "HasEnteredHerbalBath";
  static final String HERBAL_BATH_ADVANCEMENT_ID = "hotbath:chronic_invalid";
  private static final int HERBAL_BATH_ENTERED_COUNT_TRIGGER_NUMBER = 100;
  private static final int HERBAL_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS = 5;

  // enter hot water block event
  @SubscribeEvent
  public static void enterHerbalBathBlockEvent(LivingEvent.LivingUpdateEvent event) {
    enterFluidEvents(
        event,
        HERBAL_BATH_ENTERED_COUNT_TRIGGER_NUMBER,
        HERBAL_BATH_STAYED_EFFECT_TRIGGER_TIME_SECONDS,
        HERBAL_BATH_ENTERED_NUMBER,
        HERBAL_BATH_STAYED_TIME,
        HAS_ENTERED_HERBAL_BATH,
        HERBAL_BATH_ADVANCEMENT_ID);
  }

  public static void enterFluidEvents(
      LivingEvent.LivingUpdateEvent event,
      int enteredCountTriggerNumber,
      int stayedEffectTriggerTime,
      String enteredNumberInHerbalBath,
      String herbalBathStayedTime,
      String hasEnteredHerbalBath,
      String herbalBathAdvancementId) {

    if (!(event.getEntityLiving() instanceof EntityPlayer)) {
      if (event.getEntityLiving() instanceof EntityZombie
          || event.getEntityLiving() instanceof EntitySkeleton) {
        boolean isInHerbalBath =
            CustomFluidHandler.isEntityInHerbalBathBlock(event.getEntityLiving());

        if (isInHerbalBath) {
          int damageIntervalTicks = 20;
          float damagePerSecond = 0.5F;

          if (event.getEntityLiving().ticksExisted % damageIntervalTicks == 0) {
            event.getEntityLiving().attackEntityFrom(DamageSource.MAGIC, damagePerSecond);
          }
        }
      }
      return;
    }

    boolean isInHerbalBath =
        CustomFluidHandler.isPlayerInHerbalBathBlock((EntityPlayer) event.getEntityLiving());

    if (event.getEntityLiving() instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
      NBTTagCompound playerData = player.getEntityData();

      if (isInHerbalBath) {
        if (!playerData.getBoolean(hasEnteredHerbalBath)) {
          int enteredCount = playerData.getInt(enteredNumberInHerbalBath) + 1;
          playerData.setInt(enteredNumberInHerbalBath, enteredCount);
          playerData.setBoolean(hasEnteredHerbalBath, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            Advancement advancement =
                Objects.requireNonNull(player.getServer())
                    .getAdvancementManager()
                    .getAdvancement(new ResourceLocation(herbalBathAdvancementId));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.setInt(enteredNumberInHerbalBath, 0);
            }
          }
        }

        int hotBathTime = playerData.getInt(herbalBathStayedTime) + 1;
        playerData.setInt(herbalBathStayedTime, hotBathTime);

        HealthRegenHandler.regenHealth(0.25F, 2, player);

        if (playerData.getInt(herbalBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          ResistanceBoostHandler.applyResistanceBoost(10, player);
        }

        if (playerData.getInt(herbalBathStayedTime) >= 15 * TICK_NUMBER) {
          // remove negative effects
          EffectRemovalHandler.removeNegativeEffects(player);
        }
      } else {
        playerData.setInt(herbalBathStayedTime, 0);
        playerData.setBoolean(hasEnteredHerbalBath, false);
      }
    }
  }
}
