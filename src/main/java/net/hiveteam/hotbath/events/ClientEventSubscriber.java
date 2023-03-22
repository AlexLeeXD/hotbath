// package net.hiveteam.hotbath.events;
//
// import net.hiveteam.hotbath.MilkBathCraftingContainer.MilkBathCraftingContainer;
// import net.minecraft.client.Minecraft;
// import net.minecraft.client.gui.screen.inventory.CraftingScreen;
// import net.minecraft.entity.player.PlayerInventory;
// import net.minecraft.util.IWorldPosCallable;
// import net.minecraft.util.math.BlockPos;
// import net.minecraft.util.text.ITextComponent;
// import net.minecraftforge.api.distmarker.Dist;
// import net.minecraftforge.client.event.GuiOpenEvent;
// import net.minecraftforge.eventbus.api.SubscribeEvent;
// import net.minecraftforge.fml.common.Mod;
//
// @Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
// public class ClientEventSubscriber {
//
//  @SubscribeEvent
//  public static void onOpenContainer(GuiOpenEvent event) {
//    if (event.getGui() instanceof CraftingScreen) {
//      PlayerInventory playerInventory = Minecraft.getInstance().player.inventory;
//      IWorldPosCallable callable =
//          IWorldPosCallable.of(Minecraft.getInstance().world, BlockPos.ZERO);
//
//      int windowId = ((CraftingScreen) event.getGui()).getContainer().windowId;
//      MilkBathCraftingContainer milkBathCraftingContainer =
//          new MilkBathCraftingContainer(windowId, playerInventory, callable);
//      CraftingScreen milkBathCraftingScreen =
//          new CraftingScreen(
//              milkBathCraftingContainer,
//              playerInventory,
//              ITextComponent.getTextComponentOrEmpty(""));
//
//      event.setGui(milkBathCraftingScreen);
//    }
//  }
//
//  // You can add more event subscriptions here as needed
//
// }
