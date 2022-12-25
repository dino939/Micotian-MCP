package com.denger.micotian.module.modules.render;

import com.denger.micotian.Micotian;
import com.denger.micotian.utils.ColorUtils;
import com.denger.micotian.utils.Referents;
import net.minecraft.util.text.TextFormatting;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.RenderUtils;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WaterMark extends Module {
    public WaterMark() {
        super("WaterMark", Category.Render, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int Xpos = 7;
        int Ypos = 7;
        String time = (new SimpleDateFormat("HH:mm")).format(Calendar.getInstance().getTime());
        String text = Referents.NAME + " Client " + Referents.VERSION  + " I Server: " + (mc.isSingleplayer()? "Localhost" : mc.getCurrentServerData().serverIP+" I "+"Ping: "+ mc.getCurrentServerData().pingToServer ) + " I " + time+ " I " ;

        RenderUtils.drawShadowRect(Xpos - 2, Ypos- 1, Xpos + fr.getStringWidth(text)+ 2, Ypos + 6,10);
        RenderUtils.drawCastomLitium(Xpos - 2, Ypos- 1, Xpos + fr.getStringWidth(text)+ 2, Ypos + 6,2);
        fr.drawString(text, Xpos, Ypos, Color.white.getRGB());
    }
}
