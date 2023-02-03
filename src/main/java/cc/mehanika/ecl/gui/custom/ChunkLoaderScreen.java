package cc.mehanika.ecl.gui.custom;

import cc.mehanika.ecl.Ecl;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ChunkLoaderScreen extends HandledScreen<ChunkLoaderScreenHandler> {

    private static final Identifier texture = new Identifier(Ecl.MOD_ID, "textures/gui/container/chunk_loader.png");

    public ChunkLoaderScreen(ChunkLoaderScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressFire(matrices, x, y);
    }

    private void renderProgressFire(MatrixStack matrices, int x, int y) {

        if (handler.isLoading()) {
            int scaledProgress = handler.getScaledProgress();
            drawTexture(matrices, x + 80, y + 29 + 14 - scaledProgress, 176, 14 - scaledProgress, 14, scaledProgress);
        }

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

}
