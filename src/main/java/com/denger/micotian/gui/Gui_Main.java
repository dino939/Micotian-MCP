package com.denger.micotian.gui;

import com.denger.micotian.Micotian;
import com.denger.micotian.module.setting.settings.BooleanSetting;
import com.denger.micotian.module.setting.settings.ColorSetting;
import com.denger.micotian.module.setting.settings.FloatSetting;
import com.denger.micotian.module.setting.settings.ModeSetting;
import com.denger.micotian.utils.Referents;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.module.setting.Setting;
import com.denger.micotian.utils.RenderUtil;
import com.denger.micotian.utils.RenderUtils;
import com.denger.micotian.utils.font.CustomFontRenderer;
import com.denger.micotian.utils.font.FontUtils;
import com.denger.micotian.utils.snow.SnowManager;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

public class Gui_Main extends GuiScreen {
    public static boolean click;
    public static int but;
    public static Module currentmod;
    public static Category currentcat = Category.Combat;
    public static int x;
    public static int y;
    public static int x1;
    public static int y1;
    public static boolean bind;
    public static Module modtobind;
    public static Color color = new Color(30, 30, 30, 150);
    public static Color color2 = new Color(71, 66, 66, 99);
    public static SnowManager snowManager = new SnowManager(500);
    public static CustomFontRenderer icons = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/stylesicons.ttf"), 19, Font.PLAIN), true, true);
    public static int Xpos = 25;
    public static int Ypos = 25;
    public static int takenX;
    public BooleanSetting showbinds;
    public BooleanSetting blur;
    public BooleanSetting snow;

    public static CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/main.ttf"), 19, Font.PLAIN), true, true);
    public Gui_Main(BooleanSetting showbinds, BooleanSetting blur, BooleanSetting snow){
        this.showbinds  = showbinds;
        this.blur = blur;
        this.snow = snow;
    }
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        if (mc.entityRenderer.isShaderActive()) {
            mc.entityRenderer.stopUseShader();
        }
    }
    @Override
    public void initGui() {
        super.initGui();
        fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/main.ttf"), 19, Font.PLAIN), true, true);
        x = Xpos;
        y = Ypos;
        x1 = Xpos + 310;
        y1 = Ypos + 250;
        if(blur.getVal()){
            mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
    }
    public static void drawCat(Category category, int Xpos, int Ypos , int mouseX, int mouseY){
        if(ishover(Xpos + 2, Ypos, (Xpos + (x1 - x) / Category.values().length) - 2, Ypos + 20, mouseX, mouseY)){
            if(click && but == 0){
                currentcat = category;
            }
            RenderUtils.drawShadowRect(Xpos + 2, Ypos, (Xpos + (x1 - x) / Category.values().length) - 2, Ypos + 20, 6);

        }else {
            RenderUtils.drawShadowRect(Xpos + 2, Ypos, (Xpos + (x1 - x) / Category.values().length) - 2, Ypos + 20, 5);

        }
        fr.drawString(category.name(), Xpos + ((x1 - x) / Category.values().length) / 2 - (fr.getStringWidth(category.name()) / 2), Ypos + 2, currentcat == category ? Color.MAGENTA.getRGB() : Color.white.getRGB());
    }
    public void drawMod(Module module, int Xpos, int Ypos , int mouseX, int mouseY){
        if(ishover(Xpos, Ypos, Xpos + 150, Ypos + 10, mouseX , mouseY)){
            RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + 150, Ypos + 10, 6);
            if(click && but == 0){
                module.toggle();
            }
            if(click && but == 2){
                bind = true;
                modtobind = module;
            }
            if(click && but == 1){
                currentmod = module;
            }
        }else {
            RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + 150, Ypos + 10, 5);
        }
        String text = "";
        if(module == modtobind && bind){
            if(module.getKey() == 0){
                text = "Bind...";
            }else {
                text = "Bind " + Keyboard.getKeyName(module.getKey());
            }
        }
        fr.drawString(module == modtobind && bind ? text : module.getName(), Xpos + 75 - (fr.getStringWidth(module.getName() ) / 2), Ypos + 2, Color.white.getRGB());
        RenderUtil.drawRect(Xpos + 150 - 20, Ypos, Xpos + 150 - 19, Ypos + 10, color2.getRGB());
        if (module.isEnable()) {
            fr.drawCenteredString("ON", Xpos + 140, Ypos + 5 - (fr.getHeight() / 2), Color.green.getRGB());
        }else {
            fr.drawCenteredString("OFF", Xpos + 141, Ypos + 5 - (fr.getHeight() / 2), Color.RED.getRGB());
        }
        if(showbinds.getVal()){
            RenderUtil.drawRect(Xpos + 20, Ypos, Xpos + 21, Ypos + 10, color2.getRGB());
            if(module.getKey() == 0){
                fr.drawCenteredString("?", Xpos + 10, Ypos + 2, Color.white.getRGB());
            }else {
                fr.drawCenteredString(Keyboard.getKeyName(module.getKey()), Xpos + 10, Ypos + 2, Color.white.getRGB());
            }
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if(snow.getVal()){
            snowManager.render();
        }
        if(ishover(x, y - 20, x1, y + 1, mouseX, mouseY)){
            if(Mouse.isButtonDown(0)){
                Xpos = mouseX - ((x1 - x) / 2);
                Ypos = mouseY + 5;
                x = Xpos;
                y = Ypos;
                x1 = Xpos + 310;
                y1 = Ypos + 250;

            }
        }
        RenderUtil.drawSmoothRect( x, y - 10, x1, y + 1, color.getRGB());
        fr.drawCenteredStringWithOutline(Referents.NAME + " Client" , x + 155, y - 7, Color.magenta.getRGB());


        if(bind){
            if(Keyboard.isKeyDown(Keyboard.getEventKey())){
                if(Keyboard.getEventKey() == Keyboard.KEY_DELETE){
                    modtobind.setKey(0);
                }else {
                    modtobind.setKey(Keyboard.getEventKey());
                }
                bind = false;
            }
        }
        RenderUtil.drawSmoothRect(x, y, x1, y1, color.getRGB());
        if(currentmod == null){
            int pos = x;
            for(Category category : Category.values()){
                drawCat(category, pos, y + 1, mouseX, mouseY);
                pos += (x1 - x) / Category.values().length;
            }
            int pos_1 = y + 30;
            int pos_2 = y + 30;
            boolean left = true;
            int pos1 = x + 3;
            int pos2 = x1 - 153;
            for(Module module : Micotian.moduleManager.getModulesInCategory(currentcat)){
                drawMod(module, left ? pos1 : pos2, left ? pos_1 : pos_2, mouseX, mouseY);
                if(left){
                    pos_1+=11;
                }else {
                    pos_2+=11;
                }
                left = !left;


            }
        }else {
            if(ishover(x + 2, y + 2, x + 32, y + 12, mouseX, mouseY)){
                if(click && but == 0){
                    currentmod = null;
                    return;
                }
                RenderUtils.drawShadowRect(x + 2, y + 2, x + 32, y + 12, 6);
            }else {
                RenderUtils.drawShadowRect(x + 2, y + 2, x + 32, y + 12, 5);
            }
            fr.drawString("Back", x + 16 - fr.getStringWidth("Back") / 2, y + 4, Color.white.getRGB());
            RenderUtils.drawShadowRect(x + 35, y + 2, x1 - 2, y + 12, 5);
            fr.drawString(currentmod.getName(), x + 16 + fr.getStringWidth("Back"), y + 3, Color.white.getRGB());
            int pos_1 = y + 20;
            int pos_2 = y + 20;
            int pos1 = x1 - 153;
            int pos2 = x + 3;

            boolean left = true;
            for(Setting setting : Micotian.settingManager.getSettings(currentmod)){
                if(setting instanceof BooleanSetting){
                    drawCheck((BooleanSetting) setting,left ? pos1 : pos2, left ? pos_1 : pos_2, mouseX, mouseY);
                    if(left){
                        pos_1+=13;
                    }else {
                        pos_2+=13;
                    }

                }
                if(setting instanceof ColorSetting){
                    drawColor((ColorSetting) setting, left ? pos1 : pos2, left ? pos_1 : pos_2, mouseX, mouseY);
                    if(left){
                        pos_1+=25;
                    }else
                        pos_2+=25;
                }
                if(setting instanceof FloatSetting){
                    drawSlider((FloatSetting) setting, left ? pos1 : pos2, left ? pos_1 : pos_2, mouseX, mouseY);
                    if(left){
                        pos_1+=25;
                    }else
                        pos_2+=25;
                }
                if(setting instanceof ModeSetting){
                    drawMode((ModeSetting) setting, left ? pos1 : pos2, left ? pos_1 : pos_2, mouseX, mouseY);
                    if(left){
                        pos_1+=11;
                    }else {
                        pos_2 += 11;
                    }
                }
                left = !left;
            }
        }
        click = false;
    }
    public static void drawCheck(BooleanSetting setting, int Xpos, int Ypos , int mouseX, int mouseY){
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + 150, Ypos + 10, 5);
        fr.drawCenteredString(setting.getName(), Xpos + 76, Ypos + 2, Color.white.getRGB());
        RenderUtil.drawRect(Xpos + 150 - 20, Ypos, Xpos + 150 - 19, Ypos + 10, color2.getRGB());
        if (setting.getVal()) {
            fr.drawCenteredString("ON", Xpos + 140, Ypos + 5 - (fr.getHeight() / 2), Color.green.getRGB());
        }else {
            fr.drawCenteredString("OFF", Xpos + 141, Ypos + 5 - (fr.getHeight() / 2), Color.RED.getRGB());

        }
        if(ishover(Xpos, Ypos, Xpos + 150, Ypos + 10, mouseX, mouseY)){
            if(click && but == 0){
                setting.setVal(!setting.getVal());
            }
        }
    }



    public static void drawColor(ColorSetting setting, int Xpos, int Ypos , int mouseX, int mouseY){
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + 150, Ypos + 20, 5);
        fr.drawCenteredString(setting.getName(), Xpos + 75, Ypos + 3, setting.getColor().getRGB());
        final int[] counter = {1};

        for(int i = 1; i < 149; i++){
            RenderUtil.drawRect(Xpos + i, Ypos + fr.getHeight() + 3, Xpos + i + 1, Ypos + fr.getHeight() + 6, raindbow(counter[0] * 75).getRGB());

            if(ishover(Xpos + i, Ypos + fr.getHeight() + 3, Xpos + i + 3, Ypos + fr.getHeight() + 6, mouseX, mouseY)){
                if(Mouse.isButtonDown(0)){
                    setting.setColor(RenderUtil.injectAlpha(raindbow(counter[0] * 300), setting.getColor().getAlpha()));
                }
            }
            counter[0]++;
        }

        RenderUtil.drawGradientSideways(Xpos + 1, Ypos + fr.getHeight() + 8, Xpos + 149, Ypos + fr.getHeight() + 10, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());

    }

    public static Color raindbow(int deley){
        double raindbowState = Math.ceil((System.currentTimeMillis() + deley) / 20);
        raindbowState %= 360;
        return Color.getHSBColor((float) (raindbowState / 360.0f), 0.5f, 1f);
    }

    public static void drawSlider(FloatSetting setting, int Xpos, int Ypos , int mouseX, int mouseY){
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + 150, Ypos + 20, 5);
        fr.drawCenteredString(setting.getName(), Xpos + 76, Ypos + 2, Color.white.getRGB());
        if(ishover(Xpos, Ypos + fr.getHeight(), Xpos + 150, Ypos + 20, mouseX, mouseY)){
            if(Mouse.isButtonDown(0)){
                setting.setVal((mouseX - Xpos) / (150 / (setting.getMax())));
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
        fr.drawCenteredString(decimalFormat.format(setting.getVal()) + "", Xpos + 140, Ypos + 2, Color.white.getRGB());
        RenderUtils.drawShadowRect(Xpos + 1, Ypos + fr.getHeight() + 7, Xpos + (150 / (setting.getMax())) * setting.getVal(), Ypos + fr.getHeight() + 9, 5);


    }
    public static void drawMode(ModeSetting setting, int Xpos, int Ypos , int mouseX, int mouseY){
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + 150, Ypos + 5 + fr.getHeight() + (fr.getHeight() + 1) * setting.getStrings().length, 5);
        fr.drawCenteredString(setting.getName(), Xpos + 1 + 75, Ypos + 2, Color.white.getRGB());
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + 150, Ypos + 2 + fr.getHeight(), 1);

        int pos = Ypos + 5 + fr.getHeight();
        for(String string : setting.getStrings()){
            fr.drawCenteredString(string, Xpos + 1 + 75, pos, string == setting.getVal() ? Color.MAGENTA.getRGB() : Color.white.getRGB());
            if(ishover(Xpos + 1, pos, Xpos + 150, pos + 1 + fr.getHeight(), mouseX, mouseY)){
                if(click && but == 0){
                    setting.setVal(string);
                }
            }
            pos += 1 + fr.getHeight();
        }



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
}
