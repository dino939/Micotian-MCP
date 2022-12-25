package com.denger.micotian.utils.font;


import net.minecraft.util.StringUtils;

import java.awt.*;


public class FontUtil {
	private static CustomFontRenderer fontRenderer;


	public static void setupFontUtils() {
		fontRenderer = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 17), true, true);
	}

	public static double getStringWidth(String text) {
		return fontRenderer.getStringWidth(StringUtils.stripControlCodes(text));
	}

	public static int getFontHeight() {
		return fontRenderer.getHeight();
	}

	public static void drawString(String text, int x, double y, int color) {
		fontRenderer.drawString(text, x, (int)(y), color);
	}

	public static void drawStringWithShadow(String text, double x, double y, int color) {
		fontRenderer.drawStringWithShadow(text, (float) x, (float) y, color);
	}

	public static void drawCenteredString(String text, double x, double y, int color) {
		drawString(text, (int) (x - fontRenderer.getStringWidth(text) / 2), y, color);
	}

	public static void drawCenteredStringWithShadow(String text, double x, double y, int color) {
		drawStringWithShadow(text, x - fontRenderer.getStringWidth(text) / 2, y, color);
	}

	public static void drawTotalCenteredString(String text, double x, double y, int color) {
		drawString(text, (int) (x - fontRenderer.getStringWidth(text) / 2), y - fontRenderer.getHeight() / 2, color);
	}

	public static void drawTotalCenteredStringWithShadow(String text, double x, double y, int color) {
		drawStringWithShadow(text, x - fontRenderer.getStringWidth(text) / 2, y - fontRenderer.getHeight() / 2F, color);
	}
}
