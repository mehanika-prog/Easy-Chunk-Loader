package cc.mehanika.ecl.gui.custom;

import cc.mehanika.ecl.block.ChunkLoaderFuelSlot;
import cc.mehanika.ecl.gui.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ChunkLoaderScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    private final PropertyDelegate propertyDelegate;

    public ChunkLoaderScreenHandler(int syncId, PlayerInventory playerInventory) {

        this(syncId, playerInventory, new SimpleInventory(1), new ArrayPropertyDelegate(2));

    }

    public ChunkLoaderScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {

        super(ModScreenHandlers.CHUNK_LOADER_SCREEN_HANDLER, syncId);

        checkSize(inventory, 1);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        this.propertyDelegate = propertyDelegate;

        this.addSlot(new ChunkLoaderFuelSlot(inventory, 0, 80, 46));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);

    }

    public boolean isLoading() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {

        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int progressFireSize = 14;

        return maxProgress != 0 && progress != 0 ? progressFireSize - (progress * progressFireSize / maxProgress) : 0;

    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {

        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasStack()) {

            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if(index < this.inventory.size()) {

                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), false)) {
                    return ItemStack.EMPTY;
                }

            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false))  {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

        }

        return newStack;

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {

        for (int i = 0; i < 3; ++i) {

            for (int l = 0; l < 9; ++l) {

                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));

            }

        }

    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {

        for (int i = 0; i < 9; ++i) {

            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));

        }

    }

}
