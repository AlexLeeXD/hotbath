package net.hiveteam.hotbath.events;

import java.util.UUID;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomAttributeData {
  public final Attribute attribute;
  public final UUID uuid;
  public final double value; // AttributeModifier的数值
  public final AttributeModifier.Operation operation;
  public int remainingDuration;

  public CustomAttributeData(
      Attribute attribute,
      UUID uuid,
      double value,
      AttributeModifier.Operation operation,
      int duration) {
    this.attribute = attribute;
    this.uuid = uuid;
    this.value = value;
    this.operation = operation;
    this.remainingDuration = duration;
  }

  public static CustomAttributeData fromNBT(CompoundNBT nbt) {
    Attribute attribute =
        ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(nbt.getString("AttributeName")));
    UUID uuid = nbt.getUniqueId("UUID");
    double value = nbt.getDouble("Value");
    AttributeModifier.Operation operation =
        AttributeModifier.Operation.byId(nbt.getInt("Operation"));
    int remainingDuration = nbt.getInt("RemainingDuration");

    return new CustomAttributeData(attribute, uuid, value, operation, remainingDuration);
  }

  public void writeToNBT(CompoundNBT nbt) {
    nbt.putString("AttributeName", attribute.getRegistryName().toString());
    nbt.putUniqueId("UUID", uuid);
    nbt.putDouble("Value", value);
    nbt.putInt("Operation", operation.ordinal());
    nbt.putInt("RemainingDuration", remainingDuration);
  }
}
