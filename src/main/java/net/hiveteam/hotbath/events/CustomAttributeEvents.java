package net.hiveteam.hotbath.events;

import static net.hiveteam.hotbath.util.EffectChangeUtil.*;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CustomAttributeEvents {

  @SubscribeEvent
  public static void checkCustomAttributeExpiry(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof ServerPlayerEntity) {
      ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
      CompoundNBT playerData = player.getPersistentData();
      ListNBT customAttributeEffects =
          playerData.getList("CustomAttributeEffects", Constants.NBT.TAG_COMPOUND);

      for (int i = 0; i < customAttributeEffects.size(); i++) {
        CompoundNBT attributeDataNBT = customAttributeEffects.getCompound(i);
        CustomAttributeData attributeData = CustomAttributeData.fromNBT(attributeDataNBT);

        attributeData.remainingDuration--;

        if (attributeData.remainingDuration <= 0) {
          removeAttributeModifier(player, attributeData.attribute, attributeData.uuid);
          customAttributeEffects.remove(i);
          i--; // Adjust the index since we removed an element from the list.
        } else {
          attributeData.writeToNBT(attributeDataNBT);
        }
      }

      // Update the CustomAttributeEffects list in the player's data.
      playerData.put("CustomAttributeEffects", customAttributeEffects);
    }
  }
}
