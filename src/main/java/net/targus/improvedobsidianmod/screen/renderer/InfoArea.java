package net.targus.improvedobsidianmod.screen.renderer;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Rect2i;

public abstract class InfoArea extends DrawableHelper {
    protected final Rect2i area;

    protected InfoArea(Rect2i area) {
        this.area = area;
    }

    public abstract void draw(MatrixStack stack);
}