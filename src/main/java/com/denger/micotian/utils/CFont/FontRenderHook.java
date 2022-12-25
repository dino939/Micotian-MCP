package com.denger.micotian.utils.CFont;

import net.minecraft.client.gui.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;

public final class FontRenderHook extends FontRenderer
{
    private final CustomFontRenderer fontRenderer;
    
    public FontRenderHook(final Font font, final boolean antiAliasing, final boolean fractionalMetrics) {
        super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
        this.fontRenderer = new CustomFontRenderer(font, antiAliasing, fractionalMetrics);
    }
    
    public int renderString(String text, final float x, final float y, int color, final boolean dropShadow) {
        if (text == null) {
            return 0;
        }
        if (this.bidiFlag) {
            text = this.bidiReorder(text);
        }
        if ((color & 0xFC000000) == 0x0) {
            color |= 0xFF000000;
        }
        if (dropShadow) {
            color = ((color & 0xFCFCFC) >> 2 | (color & 0xFF000000));
        }
        this.red = (color >> 16 & 0xFF) / 255.0f;
        this.blue = (color >> 8 & 0xFF) / 255.0f;
        this.green = (color & 0xFF) / 255.0f;
        this.alpha = (color >> 24 & 0xFF) / 255.0f;
        GlStateManager.color(this.red, this.blue, this.green, this.alpha);
        this.posX = x;
        this.posY = y;
        this.fontRenderer.drawString(text, x, y, color, dropShadow);
        return (int)this.posX;
    }
    
    @Override
    public int getStringWidth(final String text) {
        return this.fontRenderer.getStringWidth(text);
    }
}
