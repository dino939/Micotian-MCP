package com.denger.micotian.ClickGui;

import com.denger.micotian.ClickGui.comp.*;
import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.CFont.FontUtils;
import com.denger.micotian.utils.Referents;
import com.denger.micotian.utils.RenderUtils;
import com.denger.micotian.utils.setting.Setting;
import com.denger.micotian.utils.setting.settings.BooleanSetting;
import com.denger.micotian.utils.setting.settings.ColorSetting;
import com.denger.micotian.utils.setting.settings.FloatSetting;
import com.denger.micotian.utils.setting.settings.ModeSetting;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;


public class ClickGui extends GuiScreen {
    public static Category currentcat;
    public static Module currentmod;
    private long lastClick;
    private double holdLength;
    private long hold;
    private double speed;
    public static int x;
    public static int y;
    public static int x1;
    public static int y1;
    public static boolean click;
    public static int but;
    public static int Xpos = 25;
    public static int Ypos = 25;
    public ClickGui(){}
    @Override
    public void initGui(){
        super.initGui();
        x = Xpos;
        y = Ypos;
        x1 = Xpos + 310;
        y1 = Ypos + 250;
        mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));

    }
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        if (mc.entityRenderer.isShaderActive()) {
            mc.entityRenderer.stopUseShader();
        }
    }
    int offset = 0;
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        offset = 0;
        super.drawScreen(mouseX, mouseY, partialTicks);
        if(ishover(x+1, y - 8, x+1+((x1-x)/3), y + 15, mouseX, mouseY)){
            if(Mouse.isButtonDown(0)){
                Xpos = mouseX - (((x1 - x) / 2 - (x1 - x) / 3));
                Ypos = mouseY - 4;
                x = Xpos;
                y = Ypos;
                x1 = Xpos + 310;
                y1 = Ypos + 250;
            }
        }
        RenderUtils.drawCastomLitium(x, y - 10, x1, y1, 1,-1);
        RenderUtils.drawCastomLitium(x+1, y - 8, x+1+((x1-x)/3), y + 15, 1,-1);
        FontUtils.fr.drawString(Referents.NAME+ " Client ",x+10, y - 6,-1);
        FontUtils.fr.drawString(Referents.VERSION,x+35, y + 4,-1);

        offset = 0;
        for (Category category : Category.values()) {
            this.Categ(category,x,y+offset,mouseX,mouseY);
            offset += 33;
        }
        offset = 0;
        for (Module module : Micotian.moduleManager.getModulesInCategory(currentcat)){
            Mod(module,x+110,y+offset,mouseX,mouseY);
            offset += 20;
        }
        if (currentmod != null){
            int yposition;
            yposition = Ypos + 48;
            for(Setting setting : Micotian.settingManager.getSettings(currentmod)){

                int xposition = Xpos+170;

                if(setting instanceof BooleanSetting){
                    BooleanS.drawCheck((BooleanSetting) setting,xposition,yposition,mouseX,mouseY);
                    yposition+=20;
                }
                if(setting instanceof ColorSetting){

                }
                if(setting instanceof FloatSetting){
                    FloatS.drawSlider((FloatSetting)setting,xposition+50,yposition,mouseX,mouseY );
                    yposition+=25;
                }
                if(setting instanceof ModeSetting){
                    ModeS.drawMode((ModeSetting)setting,xposition+2,yposition,mouseX,mouseY);
                    yposition+=30;
                    }
            }
        }

    }
    public static void Categ(Category category, int x,int y,int mouseX, int mouseY){
            if (ishover(x +10,y +48,x+1+((x1-x)/3),y + 72,mouseX, mouseY)) {
                if(Mouse.isButtonDown(0)){
                currentcat = category;} }
                RenderUtils.drawCastomLitium(x +10,y +48,x+1+((x1-x)/3),y + 72,category.equals(currentcat) ? 2 : 1,category.equals(currentcat) ? -2 : 0);
                FontUtils.fr.drawString(category.name(),x+15,y +56,category.equals(currentcat) ? -1 : new Color(170,170,170).getRGB());

    }
    public void Mod(Module module, int x, int y, int mouseX, int mouseY){
        if (ishover(x +10,y + 48,x+1+((x1-x)/3)+19,y + 63,mouseX, mouseY)) {
            if (Mouse.isButtonDown(0)) {
                if (System.currentTimeMillis() - this.lastClick > this.speed * 1000.0) {
                    this.lastClick = System.currentTimeMillis();
                    if (this.hold < this.lastClick) {
                        this.hold = this.lastClick;
                    }
                    module.toggle();
                    this.updateVals();
                }
                else if (System.currentTimeMillis() - this.hold > this.holdLength * 1000.0) {

                    this.updateVals();
                }
            } if(Mouse.isButtonDown(1)){
                currentmod = module;
            }
               }
        double mx = ((x+5)+85/2-(FontUtils.fr.getStringWidth(module.getName())/2));
        RenderUtils.drawCastomLitium(x +10,y + 48,x+1+((x1-x)/3)+19,y + 63,module.isEnable() ? 2 : 1,module.isEnable()   ? -2 : 0);
        FontUtils.fr.drawString(module.getName(),mx,y +52,module.isEnable() ? -1 : new Color(170,170,170).getRGB());

    }


    public static boolean ishover(int xx, int yy, int xxx, int yyy, int mouseX, int mouseY){
        if(mouseX > xx && mouseX < xxx && mouseY > yy && mouseY < yyy){
            return true;
        }
        return false;
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        click = true;
        but = mouseButton;
    }
    private void updateVals() {
        this.speed = 1.0 / 4;
        this.holdLength = this.speed / 4;
    }

}
