package com.denger.micotian.module.hud;

import com.denger.micotian.utils.CFont.FontUtils;
import net.minecraft.client.gui.GuiScreen;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.RenderUtils;

import java.awt.*;

public class Coords extends Module {
    public Coords() {
        super("Coords", Category.Hud, 0);
    }
    public static int Xpos = -10;
    public static int Ypos = -10;
    public static int textwidth;
    @Override
    public void onRender2D() {
        super.onRender2D();
        if (Ypos< 1|Xpos< -5|Ypos > GuiScreen.height|Xpos > GuiScreen.width){
            Xpos = 7;
            Ypos = GuiScreen.height - 7 - FontUtils.fr.getHeight();
        }

        final double prevZ = mc.player.posZ - mc.player.prevPosZ;
        final double prevX = mc.player.posX - mc.player.prevPosX;
        final double lastDist = Math.sqrt(prevX * prevX + prevZ * prevZ);
        final double currSpeed = lastDist * 15.3571428571;
        final String speed = String.format("%.2f bps", currSpeed);

        String text = "X: " + (int) mc.player.posX + " Y: " + (int) mc.player.posY + " Z: " + (int) mc.player.posZ+" "+"Speed: " + speed;
        textwidth = FontUtils.fr.getStringWidth(text);
        RenderUtils.drawCastomLitium(Xpos, Ypos - 4, Xpos + textwidth + 2, Ypos + 6,2);
        FontUtils.fr.drawString(text, Xpos, Ypos - 4 , Color.white.getRGB());
    }
}
