package net.hiveteam.hotbath.util;

import net.hiveteam.hotbath.HotBath;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HotBath.MOD_ID)
public class UndeadDamageInFluidHandler {
  private static final float DAMAGE_PER_SECOND = 0.5f;
  private static final int DAMAGE_INTERVAL_TICKS = 20;

  @SubscribeEvent
  public static void damageUndeadInHerbalBath(LivingEvent.LivingUpdateEvent event) {
    Entity entity = event.getEntity();

    if (entity instanceof ZombieEntity || entity instanceof SkeletonEntity) {
      if (entity.ticksExisted % DAMAGE_INTERVAL_TICKS == 0) {
        World world = entity.world;
        BlockPos entityPos = entity.getPosition();

        if (world.getFluidState(entityPos).isSource()) {
          entity.attackEntityFrom(DamageSource.MAGIC, DAMAGE_PER_SECOND * DAMAGE_INTERVAL_TICKS);
        }
      }
    }
  }
}
