package com.crabmod.hotbath.fluid_details;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class HotBathMaterials {
  public static final Material HOTBATH_MATERIAL =
      new Material.Builder(MaterialColor.WATER)
          .doesNotBlockMovement()
          .notSolid()
          .replaceable()
          .liquid()
          .build();
}
