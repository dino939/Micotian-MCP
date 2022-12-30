package com.denger.micotian.module.render;

import com.denger.micotian.utils.ColorUtils;
import com.denger.micotian.utils.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;

import java.awt.*;

public class ChinaHat extends Module {


    public ChinaHat() {
        super("ChinaHat", Category.Render, 0);

    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        final double height = true ? mc.player.isSneaking() ? -0.2 : 0.05 : mc.player.isSneaking() ? -0.30 : -0.1;
        if (mc.gameSettings.thirdPersonView == 1 || mc.gameSettings.thirdPersonView == 2) {
            GlStateManager.pushMatrix();
            GL11.glBlendFunc(770, 771);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            RenderUtils.enableSmoothLine(2.5f);
            GL11.glShadeModel(7425);
            GL11.glDisable(2884);
            GL11.glEnable(3042);
            GL11.glEnable(2929);
            GL11.glTranslatef(0.0f, (float) (mc.player.height + height), 0.0f);
            GL11.glRotatef(-mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
            GL11.glBegin(6);
            RenderUtils.glColor(ColorUtils.getColor());
            GL11.glVertex3d(0.0, 0.3, 0.0);
            for (float n2 = 0.0f; n2 < 360.5; ++n2) {
                RenderUtils.glColor(ColorUtils.getColor2());
                GL11.glVertex3d(Math.cos(n2 * 3.141592653589793 / 180.0) * 0.66, 0.0, Math.sin(n2 * 3.141592653589793 / 180.0) * 0.66);
            }
            GL11.glVertex3d(0.0, 0.3, 0.0);
            GL11.glEnd();
            GL11.glBegin(2);
            for (float n3 = 0.0f; n3 < 360.5; ++n3) {
                RenderUtils.glColor(ColorUtils.getColor2());
                GL11.glVertex3d(Math.cos(n3 * 3.141592653589793 / 180.0) * 0.66, 0.0, Math.sin(n3 * 3.141592653589793 / 180.0) * 0.66);
            }
            GL11.glEnd();
            GlStateManager.enableAlpha();
            RenderUtils.disableSmoothLine();
            GL11.glShadeModel(7424);
            GL11.glEnable(2884);
            GL11.glDisable(3042);
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            GlStateManager.resetColor();
            GlStateManager.popMatrix();
        }
    }
}
