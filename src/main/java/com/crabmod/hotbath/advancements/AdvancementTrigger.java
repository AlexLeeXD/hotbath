package com.crabmod.hotbath.advancements;

import java.lang.reflect.Method;
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
  private final List<Listener> listeners = new ArrayList<>();
  private final ResourceLocation ID;

  public AdvancementTrigger(String modName, String advancementName) {
    this.ID = ResourceLocation.fromNamespaceAndPath(modName, advancementName);
  }

  @Override
  public void addPlayerListener(
      @NotNull PlayerAdvancements playerAdvancements, @NotNull Listener listener) {
    this.listeners.add(listener);
  }

  @Override
  public void removePlayerListener(
      @NotNull PlayerAdvancements playerAdvancements, @NotNull Listener listener) {
    this.listeners.remove(listener);
  }

  @Override
  public void removePlayerListeners(@NotNull PlayerAdvancements playerAdvancements) {
    this.listeners.clear();
  }

  @Override
  public @NotNull Codec<Instance> codec() {
    return Codec.unit(() -> new Instance(this.ID));
  }

  public void trigger(ServerPlayer player) {
    try {
      Method triggerMethod =
          CriterionTrigger.Listener.class.getDeclaredMethod("trigger", ServerPlayer.class);
      triggerMethod.setAccessible(true);
      for (Listener listener : this.listeners) {
        triggerMethod.invoke(listener, player);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static class Instance implements CriterionTriggerInstance {
    private final ResourceLocation id;

    public Instance(ResourceLocation id) {
      this.id = id;
    }

    @Override
    public void validate(@NotNull CriterionValidator validator) {}
  }
}
