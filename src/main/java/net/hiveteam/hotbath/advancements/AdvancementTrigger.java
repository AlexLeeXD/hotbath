package net.hiveteam.hotbath.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class AdvancementTrigger extends AbstractCriterionTrigger<AdvancementTrigger.Instance> {
  private final ResourceLocation ID;

  public AdvancementTrigger(String modName, String advancementName) {
    this.ID = new ResourceLocation(modName, advancementName);
  }

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
    return new Instance(entityPredicate, ID);
  }

  // 自定义 Instance 类
  public static class Instance extends CriterionInstance {
    public Instance(EntityPredicate.AndPredicate playerPredicate, ResourceLocation id) {
      super(id, playerPredicate);
    }
  }
}
