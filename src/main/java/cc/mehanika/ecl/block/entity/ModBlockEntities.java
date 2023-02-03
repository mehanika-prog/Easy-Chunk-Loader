package cc.mehanika.ecl.block.entity;

import cc.mehanika.ecl.Ecl;
import cc.mehanika.ecl.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static BlockEntityType<ChunkLoaderBlockEntity> CHUNK_LOADER;

    public static void registerBlockEntities() {

        CHUNK_LOADER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Ecl.MOD_ID, "chunk_loader"),
                FabricBlockEntityTypeBuilder.create(ChunkLoaderBlockEntity::new,
                        ModBlocks.CHUNK_LOADER).build(null));

    }

}
