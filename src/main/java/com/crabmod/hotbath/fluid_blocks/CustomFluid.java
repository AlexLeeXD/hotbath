package com.crabmod.hotbath.fluid_blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReaderBase;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class CustomFluid extends Fluid implements IForgeRegistryEntry<Fluid> {

  private final ResourceLocation stillTexture;
  private final ResourceLocation flowingTexture;
  private ResourceLocation registryName;

  public CustomFluid(String fluidName, ResourceLocation stillTexture, ResourceLocation flowingTexture) {
    this.stillTexture = stillTexture;
    this.flowingTexture = flowingTexture;
    this.registryName = new ResourceLocation("hotbath", fluidName);
    this.setRegistryName(this.registryName);
  }

  @Override
  public CustomFluid setRegistryName(ResourceLocation name) {
    this.registryName = name;
    return this;
  }

  @Override
  public ResourceLocation getRegistryName() {
    return this.registryName;
  }

  @Override
  public Class<Fluid> getRegistryType() {
    return Fluid.class;
  }

  @Override
  public BlockRenderLayer getRenderLayer() {
    return null;
  }

  @Override
  public Item getFilledBucket() {
    return null;
  }

  @Override
  protected boolean canOtherFlowInto(IFluidState state, Fluid fluidIn, EnumFacing direction) {
    return false;
  }

  @Override
  protected Vec3d getFlow(IWorldReaderBase worldIn, BlockPos pos, IFluidState state) {
    return null;
  }

  @Override
  public int getTickRate(IWorldReaderBase worldIn) {
    return 0;
  }

  @Override
  protected float getExplosionResistance() {
    return 0;
  }

  @Override
  public float getHeight(IFluidState state) {
    return 0;
  }

  @Override
  protected IBlockState getBlockState(IFluidState state) {
    return null;
  }

  @Override
  public boolean isSource(IFluidState state) {
    return false;
  }

  @Override
  public int getLevel(IFluidState state) {
    return 0;
  }
}
