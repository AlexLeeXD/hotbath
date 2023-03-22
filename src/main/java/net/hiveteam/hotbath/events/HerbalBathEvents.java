package net.hiveteam.hotbath.events;

import static net.hiveteam.hotbath.util.EffectRemovalHandler.removeNegativeEffects;
import static net.hiveteam.hotbath.util.HealthRegenHandler.regenHealth;
import static net.hiveteam.hotbath.util.ResistanceBoostHandler.applyResistanceBoost;

import net.hiveteam.hotbath.HotBath;
import net.hiveteam.hotbath.util.CustomFluidHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      if (event.getEntityLiving() instanceof ZombieEntity
          || event.getEntityLiving() instanceof SkeletonEntity) {
        boolean isInHerbalBath =
            CustomFluidHandler.isEntityInHerbalBathBlock(event.getEntityLiving());

        if (isInHerbalBath) {
          int damageIntervalTicks = 20; // 每 20 刻（1秒）造成一次伤害
          float damagePerSecond = 0.5F;

          if (event.getEntityLiving().ticksExisted % damageIntervalTicks == 0) {
            event.getEntityLiving().attackEntityFrom(DamageSource.MAGIC, damagePerSecond);
          }
        }
      }
      return;
    }

    boolean isInHerbalBath =
        CustomFluidHandler.isPlayerInHerbalBathBlock((PlayerEntity) event.getEntityLiving());

    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();

      if (isInHerbalBath) {
        if (!playerData.getBoolean(hasEnteredHerbalBath)) {
          int enteredCount = playerData.getInt(enteredNumberInHerbalBath) + 1;
          playerData.putInt(enteredNumberInHerbalBath, enteredCount);
          playerData.putBoolean(hasEnteredHerbalBath, true);

          if (enteredCount >= enteredCountTriggerNumber) {
            Advancement advancement =
                player
                    .getServer()
                    .getAdvancementManager()
                    .getAdvancement(ResourceLocation.tryCreate(herbalBathAdvancementId));

            if (advancement != null) {
              player.getAdvancements().grantCriterion(advancement, "code_triggered");
              playerData.putInt(enteredNumberInHerbalBath, 0);
            }
          }
        }

        int hotBathTime = playerData.getInt(herbalBathStayedTime) + 1;
        playerData.putInt(herbalBathStayedTime, hotBathTime);

        regenHealth(0.25F, 2, player);

        if (playerData.getInt(herbalBathStayedTime) >= stayedEffectTriggerTime * TICK_NUMBER) {
          applyResistanceBoost(10, player);
        }

        if (playerData.getInt(herbalBathStayedTime) >= 15 * TICK_NUMBER) {
          // remove negative effects
          removeNegativeEffects(player);
        }
      } else {
        playerData.putInt(herbalBathStayedTime, 0);
        playerData.putBoolean(hasEnteredHerbalBath, false);
      }
    }
  }
}
