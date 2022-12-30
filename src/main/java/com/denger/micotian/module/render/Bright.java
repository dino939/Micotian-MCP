package com.denger.micotian.module.render;

import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.setting.settings.FloatSetting;

public class Bright extends Module {
    private FloatSetting gamma;
    public Bright() {
        super("Bright", Category.Render, 0);
        Micotian.settingManager.add(gamma = new FloatSetting("Gamma", this, 0, 1000, 100));
    }


    @Override
    public void onEnable() {
        super.onEnable();
        mc.gameSettings.gammaSetting = gamma.getVal();
    }
}
