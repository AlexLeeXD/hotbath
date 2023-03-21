package net.hiveteam.hotbath.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class FootHealthTrigger extends AbstractCriterionTrigger<FootHealthTrigger.Instance> {
  private static final ResourceLocation ID = new ResourceLocation("hotbath", "foot_health");

  @Override
  public ResourceLocation getId() {
    return ID;
  }

  public void trigger(ServerPlayerEntity player) {
    this.triggerListeners(player, instance -> true);
  }

  @Override
  protected Instance deserializeTrigger(
      JsonObject json,
      EntityPredicate.AndPredicate entityPredicate,
      ConditionArrayParser conditionsParser) {
    return new Instance(entityPredicate);
  }

  // 自定义 Instance 类
  public static class Instance extends CriterionInstance {
    public Instance(EntityPredicate.AndPredicate playerPredicate) {
      super(FootHealthTrigger.ID, playerPredicate);
    }
  }
}
