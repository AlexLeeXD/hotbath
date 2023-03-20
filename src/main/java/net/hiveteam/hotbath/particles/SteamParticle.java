package net.hiveteam.hotbath.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
    this.multiplyParticleScaleBy(3.0F);
    this.setSize(0.25F, 0.25F);
    if (longLivingEmber) {
      this.maxAge = this.rand.nextInt(50) + 280;
    } else {
      this.maxAge = this.rand.nextInt(50) + 10;
    }

    this.particleGravity = Float.MIN_VALUE;
    this.motionX = motionX;
    this.motionY = motionY + (double) (this.rand.nextFloat() / 500.0F) * 0.5D;
    this.motionZ = motionZ;
  }

  public void tick() {
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;

    if (this.particleAlpha > 0.0F) {
      this.age++;

      this.motionX +=
          (double) (this.rand.nextFloat() / 5000.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
      this.motionZ +=
          (double) (this.rand.nextFloat() / 5000.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
      this.motionY -= this.particleGravity;
      this.move(this.motionX, this.motionY, this.motionZ);

      this.particleAlpha -= 0.05F;

      this.particleAlpha = Math.max(this.particleAlpha, 0.0F);

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

    public Particle makeParticle(
        BasicParticleType typeIn,
        ClientWorld worldIn,
        double x,
        double y,
        double z,
        double xSpeed,
        double ySpeed,
        double zSpeed) {
      SteamParticle steamParticle =
          new SteamParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, false);
      steamParticle.setAlphaF(0.9F);
      steamParticle.selectSpriteRandomly(this.spriteSet);
      return steamParticle;
    }
  }
}
