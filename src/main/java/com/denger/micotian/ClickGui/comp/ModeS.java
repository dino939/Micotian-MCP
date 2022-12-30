package com.denger.micotian.ClickGui.comp;

import com.denger.micotian.utils.CFont.FontUtils;
import com.denger.micotian.utils.RenderUtils;
import com.denger.micotian.utils.setting.settings.ModeSetting;
import org.lwjgl.input.Mouse;

import java.awt.*;

import static com.denger.micotian.ClickGui.ClickGui.ishover;

public class ModeS
{

    public static void drawMode(ModeSetting setting, int Xpos, int Ypos , int mouseX, int mouseY){
        RenderUtils.drawCastomLitium(Xpos + 50, Ypos, Xpos + 120, Ypos + 5 + FontUtils.fr.getHeight() + (FontUtils.fr.getHeight() + 1) * setting.getStrings().length, 1,-1);
        RenderUtils.drawShadowRect(Xpos + 86 -(FontUtils.fr.getStringWidth(setting.getName())/3),Ypos+6,Xpos + 86 +(FontUtils.fr.getStringWidth(setting.getName())/3),Ypos+6,10,new Color(170,170,170, 179).getRGB());

        FontUtils.fr.drawCenteredString(setting.getName(), Xpos + 86, Ypos + 2, -1);

        int pos = Ypos + 5 + FontUtils.fr.getHeight();
        for(String string : setting.getStrings()){
            FontUtils.fr.drawCenteredString(string, Xpos + 1 + 85, pos, string == setting.getVal() ? -1 : new Color(170,170,170).getRGB());
            if(ishover(Xpos + 1, pos, Xpos + 150, pos + 1 + FontUtils.fr.getHeight(), mouseX, mouseY)){
                if(Mouse.isButtonDown(0)){
                    setting.setVal(string);
                }
            }
            pos += 1 + FontUtils.fr.getHeight();
        }



    }
}
