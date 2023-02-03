package cc.mehanika.ecl.block;

import cc.mehanika.ecl.Ecl;
import cc.mehanika.ecl.block.custom.ChunkLoader;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static ChunkLoader CHUNK_LOADER;

    public static void registerBlocks() {

        CHUNK_LOADER = Registry.register(Registries.BLOCK, new Identifier(Ecl.MOD_ID, "chunk_loader"),
                new ChunkLoader(FabricBlockSettings.of(Material.METAL).strength(1.5f).requiresTool()));

    }

}
