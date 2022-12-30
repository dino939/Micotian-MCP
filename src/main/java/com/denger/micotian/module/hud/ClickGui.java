package com.denger.micotian.module.hud;

import org.lwjgl.input.Keyboard;
import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.setting.settings.BooleanSetting;

public class ClickGui extends Module {

    public ClickGui() {
        super("ClickGui", Category.Hud, Keyboard.KEY_RSHIFT);


    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(Micotian.ClickGui);
        this.toggle();
    }
}
