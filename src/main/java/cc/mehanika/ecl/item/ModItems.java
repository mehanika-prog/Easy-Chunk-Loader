package cc.mehanika.ecl.item;

import cc.mehanika.ecl.Ecl;
import cc.mehanika.ecl.block.ModBlocks;
import cc.mehanika.ecl.item.custom.ChunkLoaderItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static ChunkLoaderItem CHUNK_LOADER_ITEM;

    public static void registerItems() {

        CHUNK_LOADER_ITEM = Registry.register(Registries.ITEM, new Identifier(Ecl.MOD_ID, "chunk_loader"),
                new ChunkLoaderItem(ModBlocks.CHUNK_LOADER));

    }

    public static void addItemsToGroups() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.addAfter(Items.RESPAWN_ANCHOR, CHUNK_LOADER_ITEM);
        });

    }

}
