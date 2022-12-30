package com.denger.micotian.ClickGui.comp;

import com.denger.micotian.utils.CFont.FontUtils;
import com.denger.micotian.utils.RenderUtils;
import com.denger.micotian.utils.setting.settings.FloatSetting;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.text.DecimalFormat;

import static com.denger.micotian.ClickGui.ClickGui.ishover;

public class FloatS {

    public static void drawSlider(FloatSetting setting, int Xpos, int Ypos , int mouseX, int mouseY){
        RenderUtils.drawCastomLitium(Xpos, Ypos, Xpos + 75, Ypos + 20, 1,-2);
        FontUtils.fr.drawString(setting.getName(), Xpos + 10, Ypos + 2, Color.white.getRGB());
        if(ishover(Xpos, Ypos + FontUtils.fr.getHeight(), Xpos + 75, Ypos + 20, mouseX, mouseY)){
            if(Mouse.isButtonDown(0)){
                setting.setVal((mouseX - Xpos) / (75 / (setting.getMax())));
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
        FontUtils.fr.drawCenteredString(decimalFormat.format(setting.getVal()) + "", Xpos + 60, Ypos + 2, Color.white.getRGB());
        RenderUtils.drawShadowRect(Xpos + 1, Ypos + FontUtils.fr.getHeight() + 7, Xpos + (75 / (setting.getMax())) * setting.getVal(), Ypos + FontUtils.fr.getHeight() + 9, 1,-1);


    }
}
