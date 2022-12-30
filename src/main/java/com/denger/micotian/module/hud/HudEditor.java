package com.denger.micotian.module.hud;

import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;

public class HudEditor extends Module {
    public HudEditor(){
        super("HudEditor", Category.Hud,0);
    }
    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(Micotian.HudEditor);
        this.toggle();
    }
}
