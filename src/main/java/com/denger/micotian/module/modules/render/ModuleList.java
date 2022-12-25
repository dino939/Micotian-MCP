package com.denger.micotian.module.modules.render;

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
        int pos = 7;

        ArrayList<Module> sort = new ArrayList<>(Micotian.moduleManager.getEnabledModules());
        sort.sort((module1, module2) -> {
            if (fr.getStringWidth(module1.getDisplay()) < fr.getStringWidth(module2.getDisplay())) {
                return 1;
            } else if (fr.getStringWidth(module1.getDisplay()) > fr.getStringWidth(module2.getDisplay())) {
                return -1;
            }
            return 0;
        });

        for(Module module : sort){
            int pos2 = GuiScreen.width - 7;
            int pos3 = pos2 - fr.getStringWidth(module.getDisplay());
            RenderUtil.drawRect(pos3 - 1, pos, pos2, pos + fr.getHeight() + 3, new Color(30, 30, 30, 150).getRGB());
            RenderUtils.drawShadowRect(pos3 - 1, pos, pos2, pos + fr.getHeight() + 3, 7);

            fr.drawString(module.getDisplay(), pos3, pos + 1.5, Color.white.getRGB());
            pos += fr.getHeight() + 4;
        }
    }

}
