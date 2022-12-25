package com.denger.micotian.module.modules.render;

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
        final double height = true ? mc.player.isSneaking() ? -0.18 : 0.04 : mc.player.isSneaking() ? -0.30 : -0.08;

            GL11.glPushMatrix();
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(false);
            GL11.glColor4f(Color.magenta.getRed() / 255.0f, Color.magenta.getGreen() / 255.0f,
                    Color.magenta.getBlue() / 255.0f, 125.0f / 255.0f);
            GL11.glTranslatef(0f, (float) ((float) (mc.player.height + 0.36) + height), 0f);
            GL11.glRotatef(90f, 1f, 0f, 0f);
            Cylinder c = new Cylinder();
            c.setDrawStyle(GLU.GLU_SMOOTH);
            c.setDrawStyle(GLU.GLU_LINE);
            c.draw(0f, 0.55f, 0.3f, 150, 100);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        }

}
