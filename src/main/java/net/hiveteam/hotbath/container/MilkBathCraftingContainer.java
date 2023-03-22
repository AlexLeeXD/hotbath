// package net.hiveteam.hotbath.container;
//
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.entity.player.PlayerInventory;
// import net.minecraft.inventory.CraftResultInventory;
// import net.minecraft.inventory.CraftingInventory;
// import net.minecraft.inventory.container.Slot;
// import net.minecraft.inventory.container.WorkbenchContainer;
// import net.minecraft.item.ItemStack;
// import net.minecraft.item.Items;
// import net.minecraft.util.IWorldPosCallable;
// import net.minecraft.world.World;
//
// public class MilkBathCraftingContainer extends WorkbenchContainer {
////
////  private final CraftingInventory craftMatrix = new CraftingInventory(this, 3, 3);
////  private final CraftResultInventory craftResult = new CraftResultInventory();
////
////  public MilkBathCraftingContainer(
////      int id, PlayerInventory playerInventory, IWorldPosCallable callable) {
////    super(id, playerInventory, callable);
////  }
////
////  @Override
////  public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
////    ItemStack itemstack = ItemStack.EMPTY;
////    Slot slot = this.inventorySlots.get(index);
////
////    if (slot != null && slot.getHasStack()) {
////      ItemStack itemstack1 = slot.getStack();
////      itemstack = itemstack1.copy();
////
////      if (index == 0) {
////        itemstack1.getItem().onCreated(itemstack1, playerIn.world, playerIn);
////
////        if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
////          return ItemStack.EMPTY;
////        }
////
////        slot.onSlotChange(itemstack1, itemstack);
////      } else if (index >= 10 && index < 37) {
////        if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
////          return ItemStack.EMPTY;
////        }
////      } else if (index >= 37 && index < 46) {
////        if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
////          return ItemStack.EMPTY;
////        }
////      } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
////        return ItemStack.EMPTY;
////      }
////
////      if (itemstack1.isEmpty()) {
////        slot.putStack(ItemStack.EMPTY);
////      } else {
////        slot.onSlotChanged();
////      }
////
////      if (itemstack1.getCount() == itemstack.getCount()) {
////        return ItemStack.EMPTY;
////      }
////
////      ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
////
////      if (index == 0) {
////        playerIn.dropItem(itemstack2, false);
////      }
////    }
////
////    return itemstack;
////  }
////
////  @Override
////  public void onCraftMatrixChanged(net.minecraft.inventory.IInventory inventoryIn) {
////    World world = this.getWorldPosCallable().apply(World::getWorld).orElse(null);
////    if (world == null) return;
////
////    super.onCraftMatrixChanged(inventoryIn);
////
////    Slot slot = this.inventorySlots.get(0);
////    if (slot != null && slot.getHasStack()) {
////      ItemStack itemStack = slot.getStack();
////      if (itemStack.getItem() == Items.MILK_BUCKET || itemStack.getItem() == Items.BUCKET) {
////        slot.putStack(ItemStack.EMPTY);
////      }
////    }
////  }
// }
