package com.denger.micotian.module.modules.render;

import com.denger.micotian.Micotian;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.RenderUtils;

public class ArrmorHud extends Module {
    public ArrmorHud() {
        super("ArrmorHud", Category.Render, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();

        int Xpos = 7;
        if(Micotian.moduleManager.getModule("Coords").isEnable()){
            Xpos = 120;
        }
        int Ypos = GuiScreen.height - 21;
        if(mc.player.getTotalArmorValue() == 0){
            return;
        }
        int count = 0;
        for(ItemStack itemStack : mc.player.getArmorInventoryList()){
            if(itemStack.getItem() != Items.field_190931_a){
                count++;
            }
        }
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + (count * 16), Ypos + 17, 6);
        int pos = Xpos;
        for(ItemStack itemStack : mc.player.getArmorInventoryList()){
            if(itemStack.getItem() != Items.field_190931_a){
                RenderUtils.renderItem(itemStack, pos, Ypos);
                pos += 16;
            }

        }

    }
}
