package com.crabmod.hotbath.particles;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SteamParticle extends Particle {

  protected SteamParticle(
      World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
    super(world, x, y, z, motionX, motionY, motionZ);
    this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
    this.setSize(0.25F, 0.25F);
    this.particleScale *= 0.2F;
    this.maxAge = 80 + this.rand.nextInt(10) + 10;
    this.particleGravity = 0.0002F;
    this.motionX =
        motionX + (this.rand.nextFloat() / 250.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
    this.motionZ =
        motionZ + (this.rand.nextFloat() / 250.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
    this.motionY = motionY + 0.025 + (this.rand.nextFloat() / 250.0F);
  }

  @Override
  public void tick() {
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;

    if (this.age++ < this.maxAge && this.particleAlpha > 0.02F) {
      this.motionX +=
          (this.rand.nextFloat() / 1000.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
      this.motionZ +=
          (this.rand.nextFloat() / 1000.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
      this.motionY -= this.particleGravity;
      this.move(this.motionX, this.motionY, this.motionZ);

      this.particleAlpha -= 0.01F + rand.nextFloat() * 0.01F;
    } else {
      this.setExpired();
    }
  }

  @Override
  public int getBrightnessForRender(float partialTicks) {
    int i = super.getBrightnessForRender(partialTicks);
    int j = i >> 16 & 255;
    return 240 | j << 16;
  }

  public static class Factory implements IParticleFactory<BasicParticleType> {
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
      SteamParticle steamParticle = new SteamParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
      steamParticle.setColor(1.0F, 1.0F, 1.0F);
      return steamParticle;
    }
  }
}
