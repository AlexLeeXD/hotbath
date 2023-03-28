package net.hiveteam.hotbath.otherDomain;

import net.minecraft.potion.Effect;

public class EffectParameter {

    public Integer effectTime;
    public Effect effect;
    public Integer amplifierIn;
    public Integer stayTime;
    public EffectParameter (Integer effectTime,Effect effect,Integer amplifierIn,Integer stayTime){
        this.effectTime=effectTime;
        this.effect=effect;
        this.amplifierIn=amplifierIn;
        this.stayTime = stayTime;

    }
}
