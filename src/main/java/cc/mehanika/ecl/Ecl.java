package cc.mehanika.ecl;

import cc.mehanika.ecl.block.ModBlocks;
import cc.mehanika.ecl.block.entity.ModBlockEntities;
import cc.mehanika.ecl.gui.ModScreenHandlers;
import cc.mehanika.ecl.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class Ecl implements ModInitializer {

    public static final String MOD_ID = "ecl";

    @Override
    public void onInitialize() {

        ModBlocks.registerBlocks();

        ModBlockEntities.registerBlockEntities();

        ModItems.registerItems();

        ModItems.addItemsToGroups();

        ModScreenHandlers.registerScreenHandlers();

    }

}
