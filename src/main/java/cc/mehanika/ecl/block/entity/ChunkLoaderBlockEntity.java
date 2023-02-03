package cc.mehanika.ecl.block.entity;

import cc.mehanika.ecl.block.ChunkLoaderFuelSlot;
import cc.mehanika.ecl.gui.custom.ChunkLoaderScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ChunkLoaderBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;

    private int progress = 0;
    private int maxProgress = 9231; // 8 pearls per real hour.

    public ChunkLoaderBlockEntity(BlockPos pos, BlockState state) {

        super(ModBlockEntities.CHUNK_LOADER, pos, state);

        this.propertyDelegate = new PropertyDelegate() {

            @Override
            public int get(int index) {

                switch (index){
                    case 0: return ChunkLoaderBlockEntity.this.progress;
                    case 1: return ChunkLoaderBlockEntity.this.maxProgress;
                    default: return 0;
                }

            }

            @Override
            public void set(int index, int value) {

                switch (index){
                    case 0: ChunkLoaderBlockEntity.this.progress = value; break;
                    case 1: ChunkLoaderBlockEntity.this.maxProgress = value; break;
                }

            }

            @Override
            public int size() {
                return 2;
            }

        };

    }

    @Override
    public DefaultedList<ItemStack> getItems() {

        return this.inventory;

    }

    @Override
    public Text getDisplayName() {

        return Text.translatable("gui.ecl.chunk_loader");

    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {

        return new ChunkLoaderScreenHandler(syncId, inv, this, this.propertyDelegate);

    }

    @Override
    protected void writeNbt(NbtCompound nbt) {

        super.writeNbt(nbt);

        nbt.putInt("chunk_loader.progress", progress);
        Inventories.writeNbt(nbt, inventory);

    }

    @Override
    public void readNbt(NbtCompound nbt) {

        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("chunk_loader.progress");

        super.readNbt(nbt);

    }

    public boolean isLoading() {
        return this.progress > 0;
    }

    public void doProgress() {
        this.progress++;
    }

    public void resetProgress() {
        this.progress = 0;
    }

    public boolean done() {
        return this.progress >= this.maxProgress;
    }

    public void load(World world, boolean load) {

        ChunkPos chunkpos = new ChunkPos(this.pos);

        ((ServerWorld) world).setChunkForced(chunkpos.x, chunkpos.z, load);

    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, ChunkLoaderBlockEntity entity) {

        if(world.isClient) return;

        if (entity.done()) {

            entity.resetProgress();
            entity.load(world, false);

        }

        if (entity.isLoading()) {

            entity.load(world, true);

            entity.doProgress();

        } else {

            ItemStack itemStack = entity.getItems().get(0);

            if (itemStack.isOf(ChunkLoaderFuelSlot.FUEL)) {

                itemStack.setCount(itemStack.getCount() - 1);
                entity.setStack(0, itemStack);

                entity.load(world, true);

                entity.doProgress();

            }

        }

    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (ChunkLoaderFuelSlot.isFuel(stack)) {
            ImplementedInventory.super.setStack(slot, stack);
        }
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return ChunkLoaderFuelSlot.isFuel(stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }
}
