package com.denger.micotian.module.Combat;


import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.setting.settings.BooleanSetting;
import com.denger.micotian.utils.setting.settings.ColorSetting;
import com.denger.micotian.utils.setting.settings.FloatSetting;
import com.denger.micotian.utils.setting.settings.ModeSetting;

import java.awt.*;

public class test extends Module {
    BooleanSetting check;
    FloatSetting slider;
    ModeSetting mode;
    ColorSetting color;
    public test() {
        super("TestModule", Category.Combat, 0);
        Micotian.settingManager.add(check = new BooleanSetting("Check", this, true));
        Micotian.settingManager.add(slider = new FloatSetting("Slider", this, 0, 10, 5));
        Micotian.settingManager.add(mode = new ModeSetting("Mode", this, "Client", new String[] {"Client", "Silent"}));
        Micotian.settingManager.add(color = new ColorSetting("Color", this, new Color(65, 30, 96, 150)));
    }


}
