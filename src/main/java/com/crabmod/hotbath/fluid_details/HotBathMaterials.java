package com.crabmod.hotbath.fluid_details;

import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class HotBathMaterials {
  public static final Material HOTBATH_MATERIAL =
      new Material.Builder(MaterialColor.COLOR_BLUE)
          .noCollider()
          .nonSolid()
          .replaceable()
          .liquid()
          .build();
}
