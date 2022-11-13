package net.targus.improvedobsidianmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;
import net.targus.improvedobsidianmod.screen.renderer.EnergyInfoArea;
import net.targus.improvedobsidianmod.util.MouseUtil;

import java.util.Optional;

public class ObsideriteInfusingScreen extends HandledScreen<ObsideriteInfusingScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(ImprovedObsidianMod.MOD_ID, "textures/gui/obsiderite_infusing_station_gui.png");
    private EnergyInfoArea energyInfoArea;
    public ObsideriteInfusingScreen(ObsideriteInfusingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        assignEnergyInfoArea();
    }

    private void assignEnergyInfoArea() {
        energyInfoArea = new EnergyInfoArea(((width - backgroundWidth) / 2) + 38,
                ((height - backgroundHeight) / 2) + 17, handler.blockEntity.energyStorage);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        renderEnergyAreaToolTips(matrices,mouseX,mouseY,x,y);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(matrices, x, y);
        energyInfoArea.draw(matrices);
    }
    private void renderEnergyAreaToolTips(MatrixStack matrices, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX,pMouseY,x,y,37,16,5,35)) {
            renderTooltip(matrices,energyInfoArea.getTooltips(),
                    Optional.empty(),pMouseX - x,pMouseY - y);
        }
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX,pMouseY, x + offsetX,y+offsetY,width,height);
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        if(handler.isCrafting()) {
            drawTexture(matrices, x + 81, y + 36, 177, 15, 25, handler.getScaledProgress());
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
