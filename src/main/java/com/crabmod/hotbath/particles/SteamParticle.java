package com.crabmod.hotbath.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SteamParticle extends SpriteTexturedParticle {

  private SteamParticle(
      ClientWorld world,
      double x,
      double y,
      double z,
      double motionX,
      double motionY,
      double motionZ,
      boolean longLivingEmber) {
    super(world, x, y, z);
    this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
    this.multipleParticleScaleBy(2.5F + rand.nextFloat());
    this.setSize(0.25F, 0.25F);
    this.particleScale *= 0.2F;
    if (longLivingEmber) {
      this.maxAge = 90 + this.rand.nextInt(10) + 20;
    } else {
      this.maxAge = 80 + this.rand.nextInt(10) + 10;
    }

    this.particleGravity = 0.0002F;
    this.motionX =
        motionX
            + (double)
                (this.rand.nextFloat() / 250.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
    this.motionZ =
        motionZ
            + (double)
                (this.rand.nextFloat() / 250.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
    this.motionY = motionY + 0.025 + (double) (this.rand.nextFloat() / 250.0F);
  }

  public void tick() {

    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;

    if (this.age++ < this.maxAge && this.particleAlpha > 0.02F) {
      this.motionX +=
          (double) (this.rand.nextFloat() / 1000.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
      this.motionZ +=
          (double) (this.rand.nextFloat() / 1000.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
      this.motionY -= (double) this.particleGravity;
      this.move(this.motionX, this.motionY, this.motionZ);

      this.particleAlpha -= 0.01F + rand.nextFloat() * 0.01F;
    } else {
      this.setExpired();
    }
  }

  public IParticleRenderType getRenderType() {
    return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
  }

  @OnlyIn(Dist.CLIENT)
  public static class CozySmokeFactory implements IParticleFactory<BasicParticleType> {
    private final IAnimatedSprite spriteSet;

    public CozySmokeFactory(IAnimatedSprite spriteSet) {
      this.spriteSet = spriteSet;
    }

    @Nullable
    @Override
    public Particle makeParticle(
        BasicParticleType typeIn,
        World worldIn,
        double x,
        double y,
        double z,
        double xSpeed,
        double ySpeed,
        double zSpeed) {
      SteamParticle steamParticle =
          new SteamParticle((ClientWorld) worldIn, x, y, z, xSpeed, ySpeed, zSpeed, false);
      steamParticle.setAlphaF(0.9F);
      steamParticle.selectSpriteRandomly(this.spriteSet);
      return steamParticle;
    }
  }
}
