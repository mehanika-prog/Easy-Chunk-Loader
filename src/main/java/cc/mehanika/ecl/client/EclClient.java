package cc.mehanika.ecl.client;

import cc.mehanika.ecl.gui.ModScreenHandlers;
import cc.mehanika.ecl.gui.custom.ChunkLoaderScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class EclClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.CHUNK_LOADER_SCREEN_HANDLER, ChunkLoaderScreen::new);

    }

}
