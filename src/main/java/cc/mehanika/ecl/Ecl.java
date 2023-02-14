package cc.mehanika.ecl;

import cc.mehanika.ecl.block.ModBlocks;
import cc.mehanika.ecl.block.entity.ModBlockEntities;
import cc.mehanika.ecl.config.ModConfig;
import cc.mehanika.ecl.gui.ModScreenHandlers;
import cc.mehanika.ecl.item.ModItems;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class Ecl implements ModInitializer {

    public static final String MOD_ID = "ecl";

    public static ModConfig CONFIG;

    @Override
    public void onInitialize() {

        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {

            ModConfig.init();

            CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        } else CONFIG = null;

        ModBlocks.registerBlocks();

        ModBlockEntities.registerBlockEntities();

        ModItems.registerItems();

        ModItems.addItemsToGroups();

        ModScreenHandlers.registerScreenHandlers();

    }

}
