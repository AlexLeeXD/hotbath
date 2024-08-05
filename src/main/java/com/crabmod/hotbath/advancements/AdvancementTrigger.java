package com.crabmod.hotbath.advancements;

import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.Codec;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.CriterionValidator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class AdvancementTrigger implements CriterionTrigger<AdvancementTrigger.Instance> {
  private final List<Listener<Instance>> listeners = new ArrayList<>();

  public AdvancementTrigger(String modName, String advancementName) {
    ResourceLocation ID = new ResourceLocation(modName, advancementName);
  }

  @Override
  public void addPlayerListener(
      @NotNull PlayerAdvancements playerAdvancements, @NotNull Listener<Instance> listener) {
    this.listeners.add(listener);
  }

  @Override
  public void removePlayerListener(
      @NotNull PlayerAdvancements playerAdvancements, @NotNull Listener<Instance> listener) {
    this.listeners.remove(listener);
  }

  @Override
  public void removePlayerListeners(@NotNull PlayerAdvancements playerAdvancements) {
    this.listeners.clear();
  }

  @Override
  public @NotNull Codec<Instance> codec() {
    return Codec.unit(() -> new Instance(new ResourceLocation("")));
  }

  public void trigger(ServerPlayer player) {
    List<Listener<Instance>> listenersCopy = new ArrayList<>(this.listeners);
    listenersCopy.forEach(listener -> listener.run(player.getAdvancements()));
  }

  public static class Instance implements CriterionTriggerInstance {

    public Instance(ResourceLocation id) {}

    @Override
    public void validate(@NotNull CriterionValidator pValidator) {}
  }
}
