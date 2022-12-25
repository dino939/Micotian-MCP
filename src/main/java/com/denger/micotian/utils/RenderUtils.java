package com.denger.micotian.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public final class RenderUtils {
    public static Minecraft mc = Minecraft.getMinecraft();

    public static void setupColor(Color color, int alpha){
        GL11.glColor4d((double)((float)color.getRed() / 255.0F), (double)((float)color.getGreen() / 255.0F), (double)((float)color.getBlue() / 255.0F), (double)((float)alpha / 255.0F));

    }
    public static void drawGradientRect(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(n3, n2, 0.0).color(n8, n9, n10, n7).endVertex();
        buffer.pos(n, n2, 0.0).color(n8, n9, n10, n7).endVertex();
        buffer.pos(n, n4, 0.0).color(n12, n13, n14, n11).endVertex();
        buffer.pos(n3, n4, 0.0).color(n12, n13, n14, n11).endVertex();
        instance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    public static void drawSmoothGradientRect(float left, float top, float right, float bottom, int color1, int color2) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        drawGradientRect(left, top, right, bottom, color1, color2);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawGradientRect(left * 2 - 1, top * 2, left * 2, bottom * 2 - 1, color1, color1);
        drawGradientRect(left * 2, top * 2 - 1, right * 2, top * 2, color1, color2);
        drawGradientRect(right * 2, top * 2, right * 2 + 1, bottom * 2 - 1, color1, color2);
        drawGradientRect(left * 2, bottom * 2 - 1, right * 2, bottom * 2, color1, color2);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glScalef(2F, 2F, 2F);
    }
    public static void drawGradientRectx(float left, float top, float right, float bottom, int color1, int color2) {
        float f1 = (float) (color1 >> 24 & 0xFF) / 255.0f;
        float f2 = (float) (color1 >> 16 & 0xFF) / 255.0f;
        float f3 = (float) (color1 >> 8 & 0xFF) / 255.0f;
        float f4 = (float) (color1 & 0xFF) / 255.0f;
        float f5 = (float) (color2 >> 24 & 0xFF) / 255.0f;
        float f6 = (float) (color2 >> 16 & 0xFF) / 255.0f;
        float f7 = (float) (color2 >> 8 & 0xFF) / 255.0f;
        float f8 = (float) (color2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f1);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    public static void drawSmoothRect( float left, float top, float right, float bottom, int color) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        drawRect( (int) left, (int) top, (int) right, (int) bottom, color);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawRect( (int) (left * 2 - 1), (int) (top * 2), (int) (left * 2), (int) (bottom * 2 - 1), color);
        drawRect( (int) (left * 2), (int) (top * 2 - 1), (int) (right * 2), (int) (top * 2), color);
        drawRect( (int) (right * 2), (int) (top * 2), (int) (right * 2 + 1), (int) (bottom * 2 - 1), color);
        drawRect( (int) (left * 2), (int) (bottom * 2 - 1), (int) (right * 2), (int) (bottom * 2), color);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glScalef(2F, 2F, 2F);

    }
    public static void drawCastomLitium(int startX, int startY, int endX, int endY, int r){
        drawGradientRectx(startX - r,startY - r,endX + r,endY + r,ColorUtils.getColor(),ColorUtils.getColor2());
        drawSmoothRect(startX - r,startY - r,endX + r,endY + r,new Color(0x51000000, true).hashCode());
        drawGradientRectx(startX,startY,endX ,endY,ColorUtils.getColor(),ColorUtils.getColor2());

    }
    public static void drawShadowRect(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        glColor(n6);
        drawRect(n, n2, n3, n4, n6);
        drawGradientRect(n, n2 - n5, n3, n2, false, true, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawGradientRect(n, n4, n3, n4 + n5, false, false, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawSector2(n3, n4, 0, 90, n5, n6);
        drawSector2(n3, n2, 90, 180, n5, n6);
        drawSector2(n, n2, 180, 270, n5, n6);
        drawSector2(n, n4, 270, 360, n5, n6);
        drawGradientRect(n - n5, n2, n, n4, true, true, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawGradientRect(n3, n2, n3 + n5, n4, true, false, n6, ColorUtils.swapAlpha(n6, 0.0f));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawSector2(final double n, final double n2, final int n3, final int n4, final int n5, final int n6) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(6);
        glColor(n6);
        GL11.glVertex2d(n, n2);
        glColor(ColorUtils.swapAlpha(n6, 0.0f));
        int i = n3;
        while (i <= n4) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n5, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n5);
            final Object o = null;
            ++i;
            if (o != null) {
                return;
            }
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }

    public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
        Tessellator ts = Tessellator.getInstance();
        BufferBuilder buffer = ts.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
    }

    public static void scissorRect(float x, float y, float width, double height) {
        ScaledResolution sr = new ScaledResolution(mc);
        int factor = sr.getScaleFactor();
        glScissor((int) (x * (float) factor), (int) (((float) sr.getScaledHeight() - height) * (float) factor), (int) ((width - x) * (float) factor), (int) ((height - y) * (float) factor));
    }

    private static final Frustum frustum = new Frustum();

    public static boolean isInViewFrustum(Entity entity)
    {
        return isInViewFrustum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }

    private static boolean isInViewFrustum(AxisAlignedBB bb)
    {
        Entity current = mc.getRenderViewEntity();

        frustum.setPosition(current.posX, current.posY, current.posZ);

        return frustum.isBoundingBoxInFrustum(bb);
    }

    public static double interpolate(final double old, final double current, final double partialTicks)
    {
        return old + (current - old) * partialTicks;
    }

    public static void glColor(final int hex)
    {
        final float alpha = (hex >> 24 & 0xFF) / 255F;
        final float red = (hex >> 16 & 0xFF) / 255F;
        final float green = (hex >> 8 & 0xFF) / 255F;
        final float blue = (hex & 0xFF) / 255F;

        GlStateManager.color(red, green, blue, alpha);
    }

    public static void glColor(final Color color)
    {
        final float red = color.getRed() / 255F;
        final float green = color.getGreen() / 255F;
        final float blue = color.getBlue() / 255F;
        final float alpha = color.getAlpha() / 255F;

        GlStateManager.color(red, green, blue, alpha);
    }

    public static void glColor(final Color color, final float alpha)
    {
        final float red = color.getRed() / 255F;
        final float green = color.getGreen() / 255F;
        final float blue = color.getBlue() / 255F;

        GlStateManager.color(red, green, blue, alpha);
    }

    public static void drawRect(final float startX, final float startY, final float endX, final float endY, final int color)
    {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        glColor(color);

        glBegin(GL_QUADS);

        glVertex2f(endX, startY);
        glVertex2f(startX, startY);
        glVertex2f(startX, endY);
        glVertex2f(endX, endY);

        glEnd();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void drawRect(final double startX, final double startY, final double endX, final double endY, final int color)
    {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        glColor(color);

        glBegin(GL_QUADS);

        glVertex2d(endX, startY);
        glVertex2d(startX, startY);
        glVertex2d(startX, endY);
        glVertex2d(endX, endY);

        glEnd();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void drawRoundedUpRect(final float startX, final float startY, final float endX, final float endY, final float radius, final int color) {
        float x1 = startX + radius;
        float y1 = startY + radius;
        float x2 = endX - radius;

        drawRect(startX, y1, endX, endY, color);
        drawRect(x1, startY, x2, y1, color);
        drawSector(x2, y1, radius, 90, 180, color);
        drawSector(x1, y1, radius, 180, 270, color);
    }

    public static void drawRoundedDownRect(final float startX, final float startY, final float endX, final float endY, final float radius, final int color) {
        float x1 = startX + radius;
        float x2 = endX - radius;
        float y2 = endY - radius;

        drawRect(startX, startY, endX, y2, color);
        drawRect(x1, y2, x2, endY, color);
        drawSector(x2, y2, radius, 0, 90, color);
        drawSector(x1, y2, radius, 270, 360, color);
    }

    public static void drawRoundedRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, float radius, int color) {
        float x1 = paramXStart + radius;
        float y1 = paramYStart + radius;
        float x2 = paramXEnd - radius;
        float y2 = paramYEnd - radius;

        drawRect(x1, y1, x2, y2, color);
        drawRect(x1, paramYStart, x2, y1, color);
        drawRect(x1, y2, x2, paramYEnd, color);
        drawRect(paramXStart, y1, x1, y2, color);
        drawRect(x2, y1, paramXEnd, y2, color);
        drawSector(x2, y2, radius, 0, 90, color);
        drawSector(x2, y1, radius, 90, 180, color);
        drawSector(x1, y1, radius, 180, 270, color);
        drawSector(x1, y2, radius, 270, 360, color);
    }

    public static void drawUpShadow(final float startX, final float startY, final float endX, final float endY) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(GL_SMOOTH);

        glBegin(GL_QUADS);

        glColor(new Color(0, 0, 0, 100).getRGB());
        glVertex2d(startX, startY);
        glColor(new Color(0, 0, 0, 0).getRGB());
        glVertex2d(startX, endY);
        glVertex2d(endX, endY);
        glColor(new Color(0, 0, 0, 100).getRGB());
        glVertex2d(endX, startY);

        glEnd();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(GL_FLAT);
    }

    public static void drawDownShadow(final float startX, final float startY, final float endX, final float endY) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(GL_SMOOTH);

        glBegin(GL_QUADS);

        glColor(new Color(0, 0, 0, 100).getRGB());
        glVertex2d(endX, endY);
        glColor(new Color(0, 0, 0, 0).getRGB());
        glVertex2d(endX, startY);
        glVertex2d(startX, startY);
        glColor(new Color(0, 0, 0, 100).getRGB());
        glVertex2d(startX, endY);

        glEnd();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(GL_FLAT);
    }

    public static void drawSector(float paramX, float paramY, float radius, int angleStart, int angleEnd, int color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        glColor(color);

        glBegin(GL_TRIANGLE_FAN);

        glVertex2d(paramX, paramY);
        for (int i = angleStart; i <= angleEnd; i++) {
            glVertex2d(paramX + Math.sin(i * Math.PI / 180) * radius, paramY + Math.cos(i * Math.PI / 180) * radius);
        }

        glEnd();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void renderItem(ItemStack itemStack, int x, int y) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableDepth();
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().zLevel = -100.0f;
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
        mc.getRenderItem().renderItemOverlays(mc.fontRendererObj, itemStack, x, y);
        mc.getRenderItem().zLevel = 0.0f;

        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.disableDepth();
    }

    public static void drawGradientRect(final double startX, final double startY, final double endX, final double endY, final boolean sideways, final int startColor, final int endColor)
    {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(GL_SMOOTH);

        glColor(startColor);

        glBegin(GL_QUADS);

        if (sideways) {
            glVertex2d(startX, startY);
            glVertex2d(startX, endY);
            glColor(endColor);
            glVertex2d(endX, endY);
            glVertex2d(endX, startY);
        } else {
            glVertex2d(startX, startY);
            glColor(endColor);
            glVertex2d(startX, endY);
            glVertex2d(endX, endY);
            glColor(startColor);
            glVertex2d(endX, startY);
        }

        glEnd();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(GL_FLAT);
    }

    public static void drawShadowRect(final double startX, final double startY, final double endX, final double endY, final int radius) {
        drawGradientRect(startX, startY - radius, endX, startY, false, true, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());
        drawGradientRect(startX, endY, endX, endY + radius, false, false, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());

        drawSector2(endX, endY, 0, 90, radius);
        drawSector2(endX, startY, 90, 180, radius);
        drawSector2(startX, startY, 180, 270, radius);
        drawSector2(startX, endY, 270, 360, radius);

        drawGradientRect(startX - radius, startY, startX, endY, true, true, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());
        drawGradientRect(endX, startY, endX + radius, endY, true, false, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());
    }

    public static void drawSector2(final double x, final double y, final int startAngle, final int endAngle, final int radius)
    {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(GL_SMOOTH);

        glBegin(GL_TRIANGLE_FAN);

        glColor(new Color(0, 0, 0, 100).getRGB());
        glVertex2d(x, y);
        glColor(new Color(0, 0, 0, 0).getRGB());
        for (int i = startAngle; i <= endAngle; i++) {
            glVertex2d(x + Math.sin(i * Math.PI / 180) * radius, y + Math.cos(i * Math.PI / 180) * radius);
        }

        glEnd();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(GL_FLAT);
    }

    public static void drawGradientRect(final double startX, final double startY, final double endX, final double endY, final boolean sideways, final boolean reversed, final int startColor, final int endColor)
    {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(GL_SMOOTH);

        glBegin(GL_QUADS);

        glColor(startColor);

        if (sideways) {
            if (reversed) {
                glVertex2d(endX, endY);
                glVertex2d(endX, startY);
                glColor(endColor);
                glVertex2d(startX, startY);
                glVertex2d(startX, endY);
            } else {
                glVertex2d(startX, startY);
                glVertex2d(startX, endY);
                glColor(endColor);
                glVertex2d(endX, endY);
                glVertex2d(endX, startY);
            }
        } else {
            if (reversed) {
                glVertex2d(endX, endY);
                glColor(endColor);
                glVertex2d(endX, startY);
                glVertex2d(startX, startY);
                glColor(startColor);
                glVertex2d(startX, endY);
            } else {
                glVertex2d(startX, startY);
                glColor(endColor);
                glVertex2d(startX, endY);
                glVertex2d(endX, endY);
                glColor(startColor);
                glVertex2d(endX, startY);
            }
        }

        glEnd();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(GL_FLAT);
    }
    public static void drawBorderedRect(double left, double top, double right, double bottom, double borderWidth, int insideColor, int borderColor, boolean borderIncludedInBounds) {
        drawRect(left - (!borderIncludedInBounds ? borderWidth : 0.0), top - (!borderIncludedInBounds ? borderWidth : 0.0), right + (!borderIncludedInBounds ? borderWidth : 0.0), bottom + (!borderIncludedInBounds ? borderWidth : 0.0), borderColor);
        drawRect(left + (borderIncludedInBounds ? borderWidth : 0.0), top + (borderIncludedInBounds ? borderWidth : 0.0), right - (borderIncludedInBounds ? borderWidth : 0.0), bottom - (borderIncludedInBounds ? borderWidth : 0.0), insideColor);
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height, int color) {
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
        glColor(color);

        mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);

        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
    }

    public static void drawCircle3D(Entity entity, double radius, float partialTicks, int points, float width, int color) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glLineWidth(width);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        double x = interpolate(entity.lastTickPosX, entity.posX,  partialTicks) - mc.getRenderManager().viewerPosX;
        double y = interpolate(entity.lastTickPosY, entity.posY,  partialTicks) - mc.getRenderManager().viewerPosY;
        double z = interpolate(entity.lastTickPosZ, entity.posZ,  partialTicks) - mc.getRenderManager().viewerPosZ;
        glColor(color);
        for (int i = 0; i <= points; i++) {
            GL11.glVertex3d(x + radius * Math.cos(i * MathHelper.PI2 / points), y, z + radius * Math.sin(i * MathHelper.PI2 / points));
        }
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
}