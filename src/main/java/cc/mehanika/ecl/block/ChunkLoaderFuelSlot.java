package cc.mehanika.ecl.block;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class ChunkLoaderFuelSlot extends Slot {

    public static final Item FUEL = Items.ENDER_PEARL;

    public ChunkLoaderFuelSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isOf(FUEL);
    }

    public static boolean isFuel(ItemStack stack) {
        return stack.isOf(ChunkLoaderFuelSlot.FUEL);
    }
}
