package com.denger.micotian.module.hud;

import com.denger.micotian.Micotian;
import com.denger.micotian.utils.*;
import com.denger.micotian.utils.CFont.FontUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WaterMark extends Module {
    public WaterMark() {
        super("WaterMark", Category.Hud, 0);
    }
    public static int Xpos = -10;
    public static int Ypos = -10;
    public static int textwidth;
    @Override
    public void onRender2D() {
        super.onRender2D();
        if (Ypos< 0|Xpos< -5|Ypos > GuiScreen.height|Xpos > GuiScreen.width){
            Xpos = 10;
            Ypos = 10;
        }
        try {
            String time = (new SimpleDateFormat("HH:mm")).format(Calendar.getInstance().getTime());
            String text = Referents.NAME + " Client " + Referents.VERSION + "  Server: " + mc.getCurrentServerData().serverIP + "  Ping: " + mc.getCurrentServerData().pingToServer + "  " + time;
            textwidth = FontUtils.fr.getStringWidth(text);
            RenderUtils.drawCastomLitium(Xpos - 4, Ypos - 1, Xpos + textwidth + 2, Ypos + 10,3);
            FontUtils.fr.drawString(text,  Xpos - 3, Ypos - 1, -1);

        }catch (Exception exception){
            String time = (new SimpleDateFormat("HH:mm")).format(Calendar.getInstance().getTime());
            String text =  Referents.NAME+ " Client " + Referents.VERSION + "  Server: " + "localhost" + "  " + time;
            textwidth = FontUtils.fr.getStringWidth(text);
            RenderUtils.drawCastomLitium(Xpos - 4, Ypos - 1, Xpos + textwidth + 2, Ypos + 10,3);
            FontUtils.fr.drawString(text,  Xpos - 3, Ypos - 1, -1);
        }


    }

}
