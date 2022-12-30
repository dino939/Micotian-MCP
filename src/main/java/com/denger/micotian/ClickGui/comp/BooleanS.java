package com.denger.micotian.ClickGui.comp;

import com.denger.micotian.utils.CFont.FontUtils;
import com.denger.micotian.utils.RenderUtil;
import com.denger.micotian.utils.RenderUtils;
import com.denger.micotian.utils.setting.settings.BooleanSetting;
import org.lwjgl.input.Mouse;

import java.awt.*;

import static com.denger.micotian.ClickGui.ClickGui.but;
import static com.denger.micotian.ClickGui.ClickGui.click;
import static com.denger.micotian.ClickGui.comp.HudEditor.ishover;


public class BooleanS {
    private static long lastClick;
    private static double holdLength;
    private static long hold;
    private static double speed;

    public static void drawCheck(BooleanSetting setting, int Xpos, int Ypos , int mouseX, int mouseY){


        if (setting.getVal()) {
            String text = setting.getName()+" ON";
            double mx = ((Xpos+5)+220/2-(FontUtils.fr.getStringWidth(text)/2));
            RenderUtils.drawCastomLitium((int) (mx-(FontUtils.fr.getStringWidth(text)/2))-2,Ypos, (int) (mx+(FontUtils.fr.getStringWidth(text)/2))+2,Ypos+FontUtils.fr.getHeight()+4,1,-2);
            FontUtils.fr.drawCenteredString(text, mx, Ypos + 7 - (FontUtils.fr.getHeight() / 2), -1);
        }else {
            String text = setting.getName()+" OFF";
            double mx = ((Xpos+5)+220/2-(FontUtils.fr.getStringWidth(text)/2));
            RenderUtils.drawCastomLitium((int) (mx-(FontUtils.fr.getStringWidth(text)/2))-2,Ypos, (int) (mx+(FontUtils.fr.getStringWidth(text)/2))+2,Ypos+FontUtils.fr.getHeight()+4,1,-2);
            FontUtils.fr.drawCenteredString(text, mx, Ypos + 7 - (FontUtils.fr.getHeight() / 2), new Color(170,170,170).getRGB());

        }
        if(ishover(Xpos, Ypos, Xpos + 150, Ypos + 10, mouseX, mouseY)){
            if(Mouse.isButtonDown(0)){
                if (System.currentTimeMillis() - lastClick > speed * 1000.0) {
                    lastClick = System.currentTimeMillis();
                    if (hold < lastClick) {
                        hold = lastClick;
                    }
                    setting.setVal(!setting.getVal());
                    updateVals();
                }
                else if (System.currentTimeMillis() - hold > holdLength * 1000.0) {

                    updateVals();
                }
            }
        }
    }

    private static void updateVals() {
        speed = 1.0 / 4;
        holdLength = speed / 4;
    }
}
