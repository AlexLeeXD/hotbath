package net.hiveteam.hotbath.fluid_blocks;

import static net.hiveteam.hotbath.util.ParticleGenerator.renderDefaultSteam;

import java.util.UUID;
import java.util.function.Supplier;

import net.hiveteam.hotbath.otherDomain.AttributeParameter;
import net.hiveteam.hotbath.otherDomain.EffectParameter;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Hot Water Block
 */
public class HotWaterBlock extends AbstractHotBathBlock {

    public HotWaterBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
        stayTimeDataId="HotWaterStayedTime";
        enteredCountDataId ="HotWaterEnteredNumber";
        advancementId ="hotbath:foot_health";
        enteredCount = 100;
        AttributeParameter attributeParameter = new AttributeParameter(Attributes.ATTACK_SPEED,
                UUID.fromString("3e3b0f20-7a04-11ec-9621-0242ac130003"),
                0.10,
                AttributeModifier.Operation.MULTIPLY_TOTAL,5);
        EffectParameter effectParameter = new EffectParameter(20, Effects.SPEED, 1,15);
        attributeParameterList.add(attributeParameter);
        effectParameterList.add(effectParameter);
    }

    @Override
    public void specialEffect(ServerPlayerEntity serverPlayerEntity, Integer stayTime,Integer enteredCount) {

    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, java.util.Random rand) {
        renderDefaultSteam(worldIn, pos, rand);
    }


}
