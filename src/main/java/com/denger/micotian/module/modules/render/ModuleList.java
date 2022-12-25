package com.denger.micotian.module.modules.render;

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
        super("ModuleList", Category.Render, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int posX = 10;
        int posY = 25;
        java.util.ArrayList<Module> enabledMods = new java.util.ArrayList<>();

        for (Module module : Micotian.moduleManager.getModules()) {
            if (module.isEnable()) {
                module.anim = MathUtils.lerp(module.anim, 1, 0.3f);
                enabledMods.add(module);
            } else {
                module.anim = MathUtils.lerp(module.anim, 10, 0.3f);
            }
        }


        enabledMods.sort((module1, module2) -> fr.getStringWidth(module2.getName()) - fr.getStringWidth(module1.getName()));


        for(Module module : enabledMods){
            int pos2 = GuiScreen.width - 7;
            int pos3 = pos2 - fr.getStringWidth(module.getDisplay());
            RenderUtil.drawRect((pos3 - 1)*module.anim, posY,  pos2*module.anim,  posY + fr.getHeight() + 3, new Color(30, 30, 30, 150).getRGB());
            RenderUtils.drawShadowRect((pos3 - 1)*module.anim,  posY, pos2*module.anim,  posY + fr.getHeight() + 3, 7);
            fr.drawString(module.getDisplay(), pos3*module.anim,  posY + 1.5, Color.white.getRGB());
            posY += fr.getHeight() + 4;
        }
    }

}