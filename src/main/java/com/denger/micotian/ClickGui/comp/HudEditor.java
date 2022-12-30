package com.denger.micotian.ClickGui.comp;

import com.denger.micotian.module.hud.Coords;
import com.denger.micotian.module.hud.TargetHud;
import com.denger.micotian.module.hud.WaterMark;
import com.denger.micotian.utils.RenderUtils;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class HudEditor extends GuiScreen
{
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {   //WaterMark
        if (ishover(WaterMark.Xpos - 4, WaterMark.Ypos - 4, WaterMark.Xpos + WaterMark.textwidth + 4, WaterMark.Ypos + 12,mouseX,mouseY)){
            if(Mouse.isButtonDown(0))
            {
                WaterMark.Ypos = mouseY - 5;
                WaterMark.Xpos = mouseX - WaterMark.textwidth/2+1;
            }
        }
        RenderUtils.drawRect(WaterMark.Xpos - 4, WaterMark.Ypos - 3, WaterMark.Xpos + WaterMark.textwidth + 2, WaterMark.Ypos + 12,new Color(225,225,225, 75).hashCode());
        //Coords
        if (ishover(Coords.Xpos, Coords.Ypos - 6, Coords.Xpos + Coords.textwidth + 2, Coords.Ypos + 8,mouseX,mouseY))
        {
            if(Mouse.isButtonDown(0))
            {
                Coords.Ypos = mouseY;
                Coords.Xpos = mouseX - Coords.textwidth/2+1;
            }
        }
        RenderUtils.drawRect(Coords.Xpos, Coords.Ypos - 4, Coords.Xpos + Coords.textwidth + 2, Coords.Ypos + 6,new Color(225,225,225, 75).hashCode());
        //TargetHud
        if (ishover(TargetHud.Xpos-6, TargetHud.Ypos , TargetHud.Xpos+145,TargetHud.Ypos+45, mouseX,mouseY))
        {
            if(Mouse.isButtonDown(0))
            {
                TargetHud.Ypos = mouseY - 23;
                TargetHud.Xpos = mouseX - 73;
            }
        }
        RenderUtils.drawRect(TargetHud.Xpos-6, TargetHud.Ypos , TargetHud.Xpos+145,TargetHud.Ypos+45,new Color(225,225,225, 75).hashCode());

    }


    public static boolean ishover(int xx, int yy, int xxx, int yyy, int mouseX, int mouseY){
        if(mouseX > xx && mouseX < xxx && mouseY > yy && mouseY < yyy){
            return true;
        }
        return false;
    }

}
