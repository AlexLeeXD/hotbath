package com.crabmod.hotbath.events;

import com.crabmod.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityCod;
import net.minecraft.entity.passive.EntitySalmon;
import net.minecraft.entity.passive.EntityPufferFish;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.crabmod.hotbath.HotBath.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class AquaticMobHurtsHandler {

  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    EntityLivingBase entity = event.getEntityLiving();
    World world = entity.getEntityWorld();
    BlockPos pos = entity.getPosition();

    // Check if the entity is in a hot bath block
    boolean inHotBath = world.getBlockState(pos).getBlock() instanceof IHotbathBlock;

    if (inHotBath && isNonTropicalAquatic(entity)) {
      entity.attackEntityFrom(DamageSource.MAGIC, 1.0F);
    }
  }

  private static boolean isNonTropicalAquatic(EntityLivingBase entity) {
    return (entity instanceof EntityCod
            || entity instanceof EntitySalmon
            || entity instanceof EntityPufferFish)
        || entity instanceof EntitySquid;
  }
}
