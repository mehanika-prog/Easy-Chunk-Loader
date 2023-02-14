package cc.mehanika.ecl.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Rarity;

public class ChunkLoaderItem extends BlockItem {
    public ChunkLoaderItem(Block block) {

        super(block, new FabricItemSettings()
                .rarity(Rarity.RARE)
        );

    }

}
