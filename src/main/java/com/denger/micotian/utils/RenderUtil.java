/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package com.denger.micotian.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtil {
    public static Minecraft mc = Minecraft.getMinecraft();
    private static final Frustum frustrum = new Frustum();
    private static final double DOUBLE_PI = Math.PI * 2;

    public static double interpolate(double current, double old, double scale) {
        return old + (current - old) * scale;
    }




    public static Vec3d getRenderPos(double x, double y, double z) {

        x = x - mc.getRenderManager().viewerPosX;
        y = y - mc.getRenderManager().viewerPosY;
        z = z - mc.getRenderManager().viewerPosZ;

        return new Vec3d(x, y, z);
    }
    public static void putVertex3d(Vec3d vec) {
        GL11.glVertex3d(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    public static void glRenderStart()
    {
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public static void glRenderStop()
    {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void drawCircle(float x, float y, float radius, int color)
    {
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
        boolean flag = GL11.glIsEnabled(GL11.GL_BLEND);
        boolean flag1 = GL11.glIsEnabled(GL11.GL_LINE_SMOOTH);
        boolean flag2 = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);

        if (!flag)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }

        if (!flag1)
        {
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
        }

        if (flag2)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
        }

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_POLYGON);

        for (int i = 0; i <= 360; ++i)
        {
            GL11.glVertex2d((double)x + Math.sin((double)i * Math.PI / 180.0D) * (double)radius, (double)y + Math.cos((double)i * Math.PI / 180.0D) * (double)radius);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        if (flag2)
        {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        if (!flag1)
        {
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }

        if (!flag)
        {
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    public static void drawCircle228(float x, float y, float radius, int color, int xz_kak_nazvat)
    {
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
        boolean flag = GL11.glIsEnabled(GL11.GL_BLEND);
        boolean flag1 = GL11.glIsEnabled(GL11.GL_LINE_SMOOTH);
        boolean flag2 = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
        if (!flag)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }
        if (!flag1)
        {
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
        }
        if (flag2)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
        }
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glLineWidth(2.5F);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for (int i = 0; i <= xz_kak_nazvat; ++i)
        {
            GL11.glVertex2d((double)x + Math.sin((double)i * Math.PI / 180.0D) * (double)radius, (double)y + Math.cos((double)i * Math.PI / 180.0D) * (double)radius);
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        if (flag2)
        {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }
        if (!flag1)
        {
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }
        if (!flag)
        {
            GL11.glDisable(GL11.GL_BLEND);
        }
    }


    public static void drawCircle228(float x, float y, float radius, int lineWidth, int outsideC, int insideC, int jopaSlona)
    {
        drawCircle228(x, y, radius, insideC, jopaSlona);
    }

    public static void drawBorderedRect(float xPos, float yPos, float width, float height, float lineWidth, int lineColor, int bgColor)
    {
        drawRect((double)xPos, (double)yPos, (double)width, (double)height, bgColor);
        float f = (float)(lineColor >> 24 & 255) / 255.0F;
        float f1 = (float)(lineColor >> 16 & 255) / 255.0F;
        float f2 = (float)(lineColor >> 8 & 255) / 255.0F;
        float f3 = (float)(lineColor & 255) / 255.0F;
        glRenderStart();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glLineWidth(lineWidth);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d((double)xPos, (double)yPos);
        GL11.glVertex2d((double)width, (double)yPos);
        GL11.glVertex2d((double)width, (double)yPos);
        GL11.glVertex2d((double)width, (double)height);
        GL11.glVertex2d((double)width, (double)height);
        GL11.glVertex2d((double)xPos, (double)height);
        GL11.glVertex2d((double)xPos, (double)height);
        GL11.glVertex2d((double)xPos, (double)yPos);
        GL11.glEnd();
        glRenderStop();
    }

    public static void setColor(Color c)
    {
        GL11.glColor4d((double)((float)c.getRed() / 255.0F), (double)((float)c.getGreen() / 255.0F), (double)((float)c.getBlue() / 255.0F), (double)((float)c.getAlpha() / 255.0F));
    }


    public static void drawUnfilledCircle(float x, float y, float radius, float lineWidth, int color)
    {
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        float f3 = (float)(color >> 24 & 255) / 255.0F;
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST);
        GlStateManager.enableBlend();
        GL11.glColor4f(f, f1, f2, f3);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(GL11.GL_LINE_LOOP);

        for (int i = 0; i <= 360; ++i)
        {
            GL11.glVertex2d((double)x + Math.sin((double)i * Math.PI / 180.0D) * (double)radius, (double)y + Math.cos((double)i * Math.PI / 180.0D) * (double)radius);
        }

        GL11.glEnd();
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GlStateManager.disableBlend();
    }

    public static void drawBorderedCircle(float x, float y, float radius, int lineWidth, int outsideC, int insideC)
    {
        drawCircle(x, y, radius, insideC);
        drawUnfilledCircle(x, y, radius, (float)lineWidth, outsideC);
    }

    public static float convertColor(int count, int color)
    {
        return (float)(color >> count & 255) / 255.0F;
    }

    public static void drawOctagon(float xPos, float yPos, float width, float height, float length, float angle, int color)
    {
        float f = convertColor(24, color);
        float f1 = convertColor(16, color);
        float f2 = convertColor(8, color);
        float f3 = convertColor(0, color);
        glRenderStart();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glVertex2d((double)(xPos + length), (double)yPos);
        GL11.glVertex2d((double)(xPos + width - length), (double)yPos);
        GL11.glVertex2d((double)(xPos + width - length), (double)yPos);
        GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F - angle));
        GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F - angle));
        GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F + angle));
        GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F + angle));
        GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F + angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F + angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F - angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F - angle));
        GL11.glVertex2d((double)(xPos + length), (double)yPos);
        GL11.glEnd();
        glRenderStop();
    }

    public static void drawLinesAroundPlayer(Entity entity, double radius, float partialTicks, int points, double width, int color, float hight) {
        GL11.glPushMatrix();
        RenderUtil.enableGL2D3();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glDisable(2929);
        GL11.glLineWidth((float) width);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glBegin(3);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks - renderManager.viewerPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks - renderManager.viewerPosY + hight;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks - renderManager.viewerPosZ;
        RenderUtil.color228(color);
        for (int i = 0; i <= points; ++i) {
            GL11.glVertex3d(x + radius * Math.cos((double)i * (Math.PI * 2) / (double)points), y, z + radius * Math.sin((double)i * (Math.PI * 2) / (double)points));
        }
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        RenderUtil.disableGL2D3();
        GL11.glPopMatrix();
    }

    public static void renderItem(ItemStack itemStack, int x, int y) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
        mc.getRenderItem().renderItemOverlays(RenderUtil.mc.fontRendererObj, itemStack, x, y);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.disableDepth();
    }

    public static void drawRectWithEdge(double x, double y, double width, double height, Color color, Color color2) {
        RenderUtil.drawRect(x, y, x + width, y + height, color.getRGB());
        int c = color2.getRGB();
        RenderUtil.drawRect(x - 1.0, y, x, y + height, c);
        RenderUtil.drawRect(x + width, y, x + width + 1.0, y + height, c);
        RenderUtil.drawRect(x - 1.0, y - 1.0, x + width + 1.0, y, c);
        RenderUtil.drawRect(x - 1.0, y + height, x + width + 1.0, y + height + 1.0, c);
    }

    public static void drawRoundedRect(double x, double y, double x1, double y1, int borderC, int insideC) {
        RenderUtil.drawRect(x + 0.5, y, x1 - 0.5, y + 0.5, insideC);
        RenderUtil.drawRect(x + 0.5, y1 - 0.5, x1 - 0.5, y1, insideC);
        RenderUtil.drawRect(x, y + 0.5, x1, y1 - 0.5, insideC);
    }

    public static void drawRoundedRect(int xCoord, int yCoord, int xSize, int ySize, int colour) {
        int width = xCoord + xSize;
        int height = yCoord + ySize;
        RenderUtil.drawRect(xCoord + 1, yCoord, width - 1, height, colour);
        RenderUtil.drawRect(xCoord, yCoord + 1, width, height - 1, colour);
    }

    public static void drawBoundingBox(AxisAlignedBB axisalignedbb) {
        GL11.glBegin(7);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glEnd();
    }

    public static void drawFilledCircle(int xx, int yy, float radius, Color color) {
        int sections = 6;
        double dAngle = Math.PI * 2 / (double)sections;
        GL11.glPushAttrib(8192);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glBegin(6);
        for (int i = 0; i < sections; ++i) {
            float x = (float)((double)radius * Math.sin((double)i * dAngle));
            float y = (float)((double)radius * Math.cos((double)i * dAngle));
            GL11.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
            GL11.glVertex2f((float)xx + x, (float)yy + y);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnd();
        GL11.glPopAttrib();
    }

    public static final void drawSmoothRect(float left, float top, float right, float bottom, int color) {
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        RenderUtil.drawRect(left, top, right, bottom, color);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        RenderUtil.drawRect(left * 2.0f - 1.0f, top * 2.0f, left * 2.0f, bottom * 2.0f - 1.0f, color);
        RenderUtil.drawRect(left * 2.0f, top * 2.0f - 1.0f, right * 2.0f, top * 2.0f, color);
        RenderUtil.drawRect(right * 2.0f, top * 2.0f, right * 2.0f + 1.0f, bottom * 2.0f - 1.0f, color);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height) {
        GL11.glEnable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        RenderUtil.disableGL2D();
        GL11.glPopMatrix();
    }

    public static int raindbow(int deley){
        double raindbowState = Math.ceil((System.currentTimeMillis() + deley) / 20);
        raindbowState %= 360;
        return Color.getHSBColor((float) (raindbowState / 360.0f), 0.5f, 1f).getRGB();
    }

        public static void drawCircle(float cx, double cy, float r, int minus, int c) {
            GL11.glPushMatrix();
            cx *= 2.0f;
            cy *= 2.0;
            GlStateManager.glLineWidth(6);
            float f = (float)(c >> 24 & 0xFF) / 255.0f;
            float f1 = (float)(c >> 16 & 0xFF) / 255.0f;
            float f2 = (float)(c >> 8 & 0xFF) / 255.0f;
            float f3 = (float)(c & 0xFF) / 255.0f;
            float theta = (float)(6.2831852 / (double)360);
            float p = (float)Math.cos(theta);
            float s = (float)Math.sin(theta);
            float x = r *= 2.0f;
            float y = 0.0f;
            GL11.glEnable(2848);
            RenderUtil.enableGL2D();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            GL11.glColor4f(f1, f2, f3, f);
            GL11.glPointSize(6);
            GL11.glBegin(3);

            final int[] counter = {1};
            for (int ii = 0; ii < 360 - minus; ++ii) {
                c = raindbow(counter[0] * 10);
                f = (float)(c >> 24 & 0xFF) / 255.0f;
                f1 = (float)(c >> 16 & 0xFF) / 255.0f;
                f2 = (float)(c >> 8 & 0xFF) / 255.0f;
                f3 = (float)(c & 0xFF) / 255.0f;
                GL11.glColor4f(f1, f2, f3, f);
    
                GL11.glVertex2f(x + cx, (float)((double)y + cy));
                float t = x;
                x = p * x - s * y;
                y = s * t + p * y;
                counter[0]++;
            }
            GL11.glEnd();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            RenderUtil.disableGL2D();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.glLineWidth(1);
            GL11.glPopMatrix();
        }

    public static void prepareScissorBox(float x, float y, float x2, float y2) {
        ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
        int factor = scale.getScaleFactor();
        GL11.glScissor((int)(x * (float)factor), (int)(((float)scale.getScaledHeight() - y2) * (float)factor), (int)((x2 - x) * (float)factor), (int)((y2 - y) * (float)factor));
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col1 & 0xFF) / 255.0f;
        float f4 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void drawPolygonPart(final double x, final double y, final int radius, final int part, final int color, final int endcolor) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final float alpha2 = (endcolor >> 24 & 0xFF) / 255.0f;
        final float red2 = (endcolor >> 16 & 0xFF) / 255.0f;
        final float green2 = (endcolor >> 8 & 0xFF) / 255.0f;
        final float blue2 = (endcolor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(x, y, 0.0).color(red, green, blue, alpha).endVertex();
        final double TWICE_PI = 6.283185307179586;
        for (int i = part * 90; i <= part * 90 + 90; ++i) {
            final double angle = 6.283185307179586 * i / 360.0 + Math.toRadians(180.0);
            bufferbuilder.pos(x + Math.sin(angle) * radius, y + Math.cos(angle) * radius, 0.0).color(red2, green2, blue2, alpha2).endVertex();
        }
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawVGradientRect(final float left, final float top, final float right, final float bottom, final int startColor, final int endColor) {
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(right, top, 0.0).color(f2, f3, f4, f).endVertex();
        bufferbuilder.pos(left, top, 0.0).color(f2, f3, f4, f).endVertex();
        bufferbuilder.pos(left, bottom, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferbuilder.pos(right, bottom, 0.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static Color injectAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static void drawGlow(final double x, final double y, final double x1, final double y1, final int color,final double alpha) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        drawVGradientRect((float)(int)x, (float)(int)y, (float)(int)x1, (float)(int)(y + (y1 - y) / 2.0), injectAlpha(new Color(color), 0).getRGB(), injectAlpha(new Color(color), (int)alpha).getRGB());
        drawVGradientRect((float)(int)x, (float)(int)(y + (y1 - y) / 2.0), (float)(int)x1, (float)(int)y1, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
        final int radius = (int)((y1 - y) / 2);
        drawPolygonPart(x, y + (y1 - y) / 2.0, radius, 0, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
        drawPolygonPart(x, y + (y1 - y) / 2.0, radius, 1, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
        drawPolygonPart(x1, y + (y1 - y) / 2.0, radius, 2, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
        drawPolygonPart(x1, y + (y1 - y) / 2.0, radius, 3, injectAlpha(new Color(color), (int)alpha).getRGB(), injectAlpha(new Color(color), 0).getRGB());
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }



    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }

    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void relativeRect(float left, float top, float right, float bottom, int color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawBorderedRect(double left, double top, double right, double bottom, double borderWidth, int insideColor, int borderColor, boolean borderIncludedInBounds) {
        RenderUtil.drawRect(left - (!borderIncludedInBounds ? borderWidth : 0.0), top - (!borderIncludedInBounds ? borderWidth : 0.0), right + (!borderIncludedInBounds ? borderWidth : 0.0), bottom + (!borderIncludedInBounds ? borderWidth : 0.0), borderColor);
        RenderUtil.drawRect(left + (borderIncludedInBounds ? borderWidth : 0.0), top + (borderIncludedInBounds ? borderWidth : 0.0), right - (borderIncludedInBounds ? borderWidth : 0.0), bottom - (borderIncludedInBounds ? borderWidth : 0.0), insideColor);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRectOpacity(double left, double top, double right, double bottom, float opacity) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(0.1f, 0.1f, 0.1f, opacity);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawNewRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(left, bottom, 0.0).endVertex();
        vertexbuffer.pos(right, bottom, 0.0).endVertex();
        vertexbuffer.pos(right, top, 0.0).endVertex();
        vertexbuffer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static boolean isInViewFrustrum(Entity entity) {
        return RenderUtil.isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }

    public static void drawLinesAroundPlayer(Entity entity, double radius, float partialTicks, int points, float width, int color) {
        GL11.glPushMatrix();
        RenderUtil.enableGL2D3();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glDisable(2929);
        GL11.glLineWidth(width);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glBegin(3);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks - renderManager.viewerPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks - renderManager.viewerPosY;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks - renderManager.viewerPosZ;
        RenderUtil.color228(color);
        for (int i = 0; i <= points; ++i) {
            GL11.glVertex3d(x + radius * Math.cos((double)i * (Math.PI * 2) / (double)points), y, z + radius * Math.sin((double)i * (Math.PI * 2) / (double)points));
        }
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        RenderUtil.disableGL2D3();
        GL11.glPopMatrix();
    }

    public static void enableGL2D3() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }

    public static void disableGL2D3() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void color228(int color) {
        GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color & 0xFF), (byte)(color >> 24 & 0xFF));
    }

    private static boolean isInViewFrustrum(AxisAlignedBB bb) {
        Entity current = mc.getRenderViewEntity();
        frustrum.setPosition(current.posX, current.posY, current.posZ);
        return frustrum.isBoundingBoxInFrustum(bb);
    }

    public static void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 0xFF) / 255.0f;
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void blockEsp(BlockPos blockPos, Color c, double length, double length2) {
        double d = blockPos.getX();

        double x = d - mc.getRenderManager().viewerPosX;
        double d2 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        double y = d2 - mc.getRenderManager().viewerPosY;
        double d3 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        double z = d3 - mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, 0.15);
        RenderUtil.drawColorBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0, z + length), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0, z + length));
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void blockEspFrame(BlockPos blockPos, double red, double green, double blue) {
        double d = blockPos.getX();

        double x = d - mc.getRenderManager().viewerPosX;
        double d2 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        double y = d2 - mc.getRenderManager().viewerPosY;
        double d3 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        double z = d3 - mc.getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(red, green, blue, 0.5);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    /*
    public static void drawOutlinedString(String str, FontRenderer font, float x, float y, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        font.drawString(str, x - 0.3f, y, ColorUtils.getColor(0));
        font.drawString(str, x + 0.3f, y, ColorUtils.getColor(0));
        font.drawString(str, x, y + 0.3f, ColorUtils.getColor(0));
        font.drawString(str, x, y - 0.3f, ColorUtils.getColor(0));
        font.drawString(str, x, y, color);
    }

     */

    public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        tessellator.draw();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        tessellator.draw();
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        tessellator.draw();
    }

    public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
        Tessellator ts = Tessellator.getInstance();
        BufferBuilder vb = ts.getBuffer();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
    }
    /*
    public static void drawEntityESP(Entity entity, Color c) {
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, 0.15f);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderUtil.drawColorBox(new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + (entity.posX - mc.getRenderManager().viewerPosX), entity.getEntityBoundingBox().minY - entity.posY + (entity.posY - mc.getRenderManager().viewerPosY), entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + (entity.posZ - mc.getRenderManager().viewerPosZ), entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + (entity.posX - mc.getRenderManager().viewerPosX), entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + (entity.posY - RenderManager.renderPosY), entity.boundingBox.maxZ + 0.05 - entity.posZ + (entity.posZ - RenderManager.renderPosZ)), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + (entity.posX - RenderManager.renderPosX), entity.boundingBox.minY - entity.posY + (entity.posY - RenderManager.renderPosY), entity.boundingBox.minZ - 0.05 - entity.posZ + (entity.posZ - RenderManager.renderPosZ), entity.boundingBox.maxX + 0.05 - entity.posX + (entity.posX - RenderManager.renderPosX), entity.boundingBox.maxY + 0.1 - entity.posY + (entity.posY - RenderManager.renderPosY), entity.boundingBox.maxZ + 0.05 - entity.posZ + (entity.posZ - RenderManager.renderPosZ)));
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

     */

    public static void rectangleBordered(double x, double y, double x1, double y1, double width, int internalColor, int borderColor) {
        RenderUtil.drawRect(x + width, y + width, x1 - width, y1 - width, internalColor);
        RenderUtil.drawRect(x + width, y, x1 - width, y + width, borderColor);
        RenderUtil.drawRect(x, y, x + width, y1, borderColor);
        RenderUtil.drawRect(x1 - width, y, x1, y1, borderColor);
        RenderUtil.drawRect(x + width, y1 - width, x1 - width, y1, borderColor);
    }


}

