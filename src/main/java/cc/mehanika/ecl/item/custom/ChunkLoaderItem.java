package cc.mehanika.ecl.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChunkLoaderItem extends BlockItem {
    public ChunkLoaderItem(Block block) {

        super(block, new FabricItemSettings()
                .maxCount(16)
                .rarity(Rarity.RARE)
        );

    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.clear();

        tooltip.add(Text.translatable("item.ecl.chunk_loader"));

        super.appendTooltip(stack, world, tooltip, context);

    }

}
