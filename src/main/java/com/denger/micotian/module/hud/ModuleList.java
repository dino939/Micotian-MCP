package com.denger.micotian.module.hud;

import com.denger.micotian.utils.CFont.FontUtils;
import com.denger.micotian.utils.MathUtils;
import net.minecraft.client.gui.GuiScreen;
import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.RenderUtil;
import com.denger.micotian.utils.RenderUtils;

import java.awt.*;
import java.util.ArrayList;

public class ModuleList extends Module {
    public ModuleList() {
        super("ModuleList", Category.Hud, 0);

    }
    public void onDisable(){
        for (Module mods : Micotian.moduleManager.getModules()){
            mods.anim = 0;
        }
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int posY = 25;
        java.util.ArrayList<Module> enabledMods = new java.util.ArrayList<>();

        for (Module module : Micotian.moduleManager.getModules()) {
            if (module.isEnable()) {
                module.anim = MathUtils.lerp(module.anim, 0.99f, 0.3f);
                enabledMods.add(module);
            } else {
                module.anim = MathUtils.lerp(module.anim, 10, 0.3f);
            }
        }


        enabledMods.sort((module1, module2) -> FontUtils.fr.getStringWidth(module2.getName()) - FontUtils.fr.getStringWidth(module1.getName()));



        for (Module module : enabledMods) {
            int pos2 = GuiScreen.width - 7;
            int pos3 = pos2 - FontUtils.fr.getStringWidth(module.getDisplay());
                RenderUtils.drawCastomLitium((int) ((pos3 - 1)*module.anim),  posY, (int) ((pos2+2)*module.anim),  posY + FontUtils.fr.getStringHeight(module.getDisplay()) + 3, 1);
            FontUtils.fr.drawString(module.getDisplay(), pos3*module.anim,  posY + 1.5, Color.white.getRGB());
            posY += fr.getHeight() + 10;

        }
    }



}