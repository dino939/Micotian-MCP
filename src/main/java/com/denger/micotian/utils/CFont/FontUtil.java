package com.denger.micotian.utils.CFont;

import java.awt.*;
import net.minecraft.util.*;

public class FontUtil
{
    private static CustomFontRenderer fontRenderer;
    
    public static void setupFontUtils() {
        FontUtil.fontRenderer = new CustomFontRenderer(new Font("Arial", 0, 17), true, true);
    }
    
    public static double getStringWidth(final String text) {
        return FontUtil.fontRenderer.getStringWidth(StringUtils.stripControlCodes(text));
    }
    
    public static int getFontHeight() {
        return FontUtil.fontRenderer.getHeight();
    }
    
    public static void drawString(final String text, final int x, final double y, final int color) {
        FontUtil.fontRenderer.drawString(text, (float)x, (float)(int)y, color);
    }
    
    public static void drawStringWithShadow(final String text, final double x, final double y, final int color) {
        FontUtil.fontRenderer.drawStringWithShadow(text, (float)x, (float)y, color);
    }
    
    public static void drawCenteredString(final String text, final double x, final double y, final int color) {
        drawString(text, (int)(x - FontUtil.fontRenderer.getStringWidth(text) / 2), y, color);
    }
    
    public static void drawCenteredStringWithShadow(final String text, final double x, final double y, final int color) {
        drawStringWithShadow(text, x - FontUtil.fontRenderer.getStringWidth(text) / 2, y, color);
    }
    
    public static void drawTotalCenteredString(final String text, final double x, final double y, final int color) {
        drawString(text, (int)(x - FontUtil.fontRenderer.getStringWidth(text) / 2), y - FontUtil.fontRenderer.getHeight() / 2, color);
    }
    
    public static void drawTotalCenteredStringWithShadow(final String text, final double x, final double y, final int color) {
        drawStringWithShadow(text, x - FontUtil.fontRenderer.getStringWidth(text) / 2, y - FontUtil.fontRenderer.getHeight() / 2.0f, color);
    }
}
