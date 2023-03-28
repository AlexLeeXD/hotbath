package net.hiveteam.hotbath.otherDomain;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class AttributeParameter {
    public Attribute attribute;
    public AttributeModifier modifier;
    public Integer stayTime;
    public AttributeParameter(Attribute attribute, UUID uuid, double amountIn, AttributeModifier.Operation operationIn,Integer stayTime){
        this.attribute = attribute;
        modifier =
                new AttributeModifier(uuid, attribute.getAttributeName(), amountIn, operationIn);
        this.stayTime = stayTime;
    }
}
