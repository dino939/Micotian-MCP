package com.denger.micotian.module.modules.render;

import com.denger.micotian.Micotian;
import com.denger.micotian.utils.ColorUtils;
import com.denger.micotian.utils.Referents;
import net.minecraft.util.text.TextFormatting;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.RenderUtils;

import java.awt.*;

public class WaterMark extends Module {
    public WaterMark() {
        super("WaterMark", Category.Render, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int Xpos = 7;
        int Ypos = 7;
        String text = Referents.NAME  + " " + Referents.VERSION;
        String text2 = "Coded by" + TextFormatting.GREEN +  " Кто?";
        int wight = fr.getStringWidth(text2);
        RenderUtils.drawGradientRectY(Xpos, Ypos, Xpos + wight, Ypos + 20, ColorUtils.getColor(), ColorUtils.getColor2());
        fr.drawString(text, Xpos, Ypos + 5 - (fr.getHeight() / 2), Color.white.getRGB());
        fr.drawString(text2, Xpos, Ypos + 15 - (fr.getHeight() / 2), Color.white.getRGB());
    }
}
