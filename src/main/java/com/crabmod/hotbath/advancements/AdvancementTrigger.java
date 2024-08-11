package com.crabmod.hotbath.advancements;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class AdvancementTrigger implements ICriterionTrigger<AdvancementTrigger.Instance> {
  private final ResourceLocation id;
  private final Map<PlayerAdvancements, Listener<Instance>> listeners = new HashMap<>();

  public AdvancementTrigger(String namespace, String path) {
    this.id = new ResourceLocation(namespace, path);
  }

  @Override
  public ResourceLocation getId() {
    return id;
  }

  @Override
  public void addListener(PlayerAdvancements playerAdvancements, Listener<Instance> listener) {
    listeners.put(playerAdvancements, listener);
  }

  @Override
  public void removeListener(PlayerAdvancements playerAdvancements, Listener<Instance> listener) {
    listeners.remove(playerAdvancements, listener);
  }

  @Override
  public void removeAllListeners(PlayerAdvancements playerAdvancements) {
    listeners.remove(playerAdvancements);
  }

  @Override
  public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
    return null;
  }

  public void trigger(PlayerAdvancements playerAdvancements) {
    Listener<Instance> listener = listeners.get(playerAdvancements);
    if (listener != null) {
      listener.notify();
    }
  }

  public static class Instance implements ICriterionInstance {
    private final ResourceLocation id;

    public Instance(ResourceLocation id) {
      this.id = id;
    }

    @Override
    public ResourceLocation getId() {
      return null;
    }

    @Override
    public JsonElement serialize() {
      return ICriterionInstance.super.serialize();
    }
  }
}
