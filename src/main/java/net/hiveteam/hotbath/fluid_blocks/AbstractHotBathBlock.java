package net.hiveteam.hotbath.fluid_blocks;

import net.hiveteam.hotbath.otherDomain.AttributeParameter;
import net.hiveteam.hotbath.otherDomain.EffectParameter;
import net.hiveteam.hotbath.util.EffectChangeUtil;
import net.minecraft.advancements.Advancement;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractHotBathBlock extends FlowingFluidBlock {
    public static final int TICK_NUMBER = 20;
    public long lastTick = 0;
    public Integer stayTime;// 在液体里呆的时间
    public String stayTimeDataId;
    public Integer enteredCount;// 在液体里呆的时间
    public String enteredCountDataId;
    public String advancementId;
    public List<AttributeParameter> attributeParameterList = new ArrayList<>();
    public List<EffectParameter> effectParameterList = new ArrayList<>();

    public AbstractHotBathBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    public static void enterHotBathEvents(ServerPlayerEntity serverPlayerEntity, EffectParameter effectParameter, AttributeParameter attributeParameter, String playerDataId) {
        CompoundNBT playerData = serverPlayerEntity.getPersistentData();
        if (effectParameter != null) {
            if (playerData.getInt(playerDataId) >= effectParameter.stayTime * TICK_NUMBER) {
                serverPlayerEntity.addPotionEffect(
                        new EffectInstance(effectParameter.effect, TICK_NUMBER * effectParameter.effectTime, effectParameter.amplifierIn, false, false, true));
            }
        }
        if (attributeParameter != null) {
            if (playerData.getInt(playerDataId) >= attributeParameter.stayTime * TICK_NUMBER) {

                EffectChangeUtil.applyAttributeModifier(
                        serverPlayerEntity,
                        attributeParameter.attribute, attributeParameter.modifier);
            }
        }
    }

    public static void enterHotBathAdancements(ServerPlayerEntity serverPlayerEntity, Integer enteredCount, String playerDataId, String AdvancementId) {
        CompoundNBT playerData = serverPlayerEntity.getPersistentData();
        playerData.putInt(playerDataId, playerData.getInt(playerDataId) + 1);

        if (playerData.getInt(playerDataId) >= enteredCount) {
            Advancement advancement =
                    serverPlayerEntity
                            .getServer()
                            .getAdvancementManager()
                            .getAdvancement(ResourceLocation.tryCreate(AdvancementId));

            if (advancement != null) {
                serverPlayerEntity.getAdvancements().grantCriterion(advancement, "code_triggered");
                playerData.putInt(playerDataId, 0);
            }
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {

        if (entityIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entityIn;
            CompoundNBT playerData = serverPlayerEntity.getPersistentData();
            if (lastTick + 5 <= worldIn.getGameTime()) {
                playerData.putInt(stayTimeDataId, 0);
                playerData.putInt(enteredCountDataId, playerData.getInt(enteredCountDataId) + 1);
            }
            lastTick = worldIn.getGameTime();
            playerData.putInt(stayTimeDataId, playerData.getInt(stayTimeDataId) + 1);
            //此处偷懒
            for(EffectParameter effectParameter:effectParameterList){
                enterHotBathEvents(serverPlayerEntity, effectParameter,null, stayTimeDataId);
            }
            for(AttributeParameter attributeParameter:attributeParameterList){
                enterHotBathEvents(serverPlayerEntity,null, attributeParameter, stayTimeDataId);
            }

            specialEffect(serverPlayerEntity, playerData.getInt(stayTimeDataId), playerData.getInt(enteredCountDataId));
            enterHotBathAdancements(serverPlayerEntity, enteredCount, enteredCountDataId, advancementId);
        }
    }

    abstract public void specialEffect(ServerPlayerEntity serverPlayerEntity, Integer stayTime, Integer enteredCount);
}
