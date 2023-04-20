package hivestandsteam.hotbath.events;

import static hivestandsteam.hotbath.HotBath.MOD_ID;

import hivestandsteam.hotbath.fluid_blocks.IHotbathBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.fish.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class AquaticMobHurtsHandler {

  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    LivingEntity entity = event.getEntityLiving();
    World world = entity.getEntityWorld();
    BlockPos pos = entity.getPosition();

    // Check if the entity is in a hot bath block
    boolean inHotBath = world.getBlockState(pos).getBlock() instanceof IHotbathBlock;

    if (inHotBath && isNonTropicalAquatic(entity)) {
      entity.attackEntityFrom(DamageSource.MAGIC, 1.0F);
    }
  }

  private static boolean isNonTropicalAquatic(LivingEntity livingEntity) {
    return (livingEntity instanceof AbstractFishEntity
            && !(livingEntity instanceof TropicalFishEntity))
        || livingEntity instanceof SquidEntity;
  }
}
