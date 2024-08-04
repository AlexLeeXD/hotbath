package com.crabmod.hotbath.fluid_details;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class HotBathMaterials {
  public static final Material HOTBATH_MATERIAL =
      new Material.Builder(MaterialColor.WATER)
          .noCollider()
          .nonSolid()
          .replaceable()
          .liquid()
          .build();
}
