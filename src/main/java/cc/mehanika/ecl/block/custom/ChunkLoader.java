package cc.mehanika.ecl.block.custom;

import cc.mehanika.ecl.Ecl;
import cc.mehanika.ecl.block.entity.ChunkLoaderBlockEntity;
import cc.mehanika.ecl.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ChunkLoader extends BlockWithEntity implements BlockEntityProvider {

    public ChunkLoader(Settings settings) {

        super(settings);

    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {

        return BlockRenderType.MODEL;

    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {

        if (state.getBlock() != newState.getBlock()) {

            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof ChunkLoaderBlockEntity) {

                if (((ChunkLoaderBlockEntity) blockEntity).isLoading()) {

                    ((ChunkLoaderBlockEntity) blockEntity).load(world, false);

                }

                ItemScatterer.spawn(world, pos, ((ChunkLoaderBlockEntity) blockEntity).getItems());
                world.updateComparators(pos, this);
            }

        }

        super.onStateReplaced(state, world, pos, newState, moved);

    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (Ecl.CONFIG != null && !Ecl.CONFIG.burnEnderPearls) return ActionResult.PASS;

        if(!world.isClient) {

            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if(screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }

        }

        return ActionResult.SUCCESS;

    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {

        return new ChunkLoaderBlockEntity(pos, state);

    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.CHUNK_LOADER, ChunkLoaderBlockEntity::tick);
    }
}
