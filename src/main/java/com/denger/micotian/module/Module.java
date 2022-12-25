package com.denger.micotian.module;

import com.denger.micotian.utils.font.CustomFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import com.denger.micotian.utils.font.FontUtils;
import com.denger.micotian.utils.notifications.NotificationManager;

import java.awt.*;

public class Module {
    private boolean enable;

    public float anim;
    private String name;
    private Category category;
    private int key;
    private String display;
    protected Minecraft mc = Minecraft.getMinecraft();
    protected CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/main.ttf"), 19, Font.PLAIN), true, true);
    protected ScaledResolution sr = new ScaledResolution(mc);

    public Module(String name, Category category, int key){
        this.name = name;
        this.category = category;
        this.key = key;
        this.display = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void onEnable(){
        NotificationManager.add(this.name, "Enable");
    }
    public void onDisable(){
        NotificationManager.add(this.name, "Disable");
    }

    public void onRender2D(){

    }

    public void onRender3D(){

    }

    public void onUpdate(){

    }


    public void toggle(){
        enable = !enable;
        if(enable){
            onEnable();
        }else {
            onDisable();
        }
    }
}
