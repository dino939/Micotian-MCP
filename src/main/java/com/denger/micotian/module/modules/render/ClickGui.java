package com.denger.micotian.module.modules.render;

import org.lwjgl.input.Keyboard;
import com.denger.micotian.Micotian;
import com.denger.micotian.gui.Gui_Main;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.setting.settings.BooleanSetting;

public class ClickGui extends Module {
    private BooleanSetting blur;
    private BooleanSetting snow;
    private BooleanSetting bind;
    public ClickGui() {
        super("ClickGui", Category.Render, Keyboard.KEY_RSHIFT);
        Micotian.settingManager.add(bind = new BooleanSetting("Show Binds", this, false));
        Micotian.settingManager.add(snow = new BooleanSetting("Snow Effect", this, false));
        Micotian.settingManager.add(blur = new BooleanSetting("Blur", this, true));

    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(new Gui_Main(bind, blur, snow));
        this.toggle();
    }
}
