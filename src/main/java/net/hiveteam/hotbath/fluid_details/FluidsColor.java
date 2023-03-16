package net.hiveteam.hotbath.fluid_details;

public final class FluidsColor {
  // return 16 bits color
  public static int getFluidColor(int red, int green, int blue) {
    return 0xff000000 | (red << 16) | (green << 8) | blue;
  }

  public static final int HERBAL_BATH_COLOR = getFluidColor(36, 202, 173);
  public static final int HOT_WATER_COLOR = getFluidColor(69, 225, 233);
  public static final int HONEY_BATH_COLOR = getFluidColor(254, 209, 122);

  public static final int MILK_BATH_COLOR = getFluidColor(248, 248, 248);
  public static final int PEONY_BATH_COLOR = getFluidColor(253, 104, 159);
  public static final int ROSE_BATH_COLOR = getFluidColor(254, 119, 142);
}
