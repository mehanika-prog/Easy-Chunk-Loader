package cc.mehanika.ecl.gui;

import cc.mehanika.ecl.Ecl;
import cc.mehanika.ecl.gui.custom.ChunkLoaderScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static ScreenHandlerType<ChunkLoaderScreenHandler> CHUNK_LOADER_SCREEN_HANDLER;

    public static void registerScreenHandlers() {

        CHUNK_LOADER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Ecl.MOD_ID, "chunk_loader"), ChunkLoaderScreenHandler::new);

    }

}
