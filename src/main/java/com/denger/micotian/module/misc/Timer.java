package com.denger.micotian.module.misc;

import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.setting.settings.FloatSetting;

public class Timer extends Module {
    FloatSetting speed;
    public Timer() {
        super("Timer", Category.Misc, 0);
        Micotian.settingManager.add(speed = new FloatSetting("Speed", this, 0, 5, 1.5f));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        mc.timer.field_194147_b = speed.getVal();
    }
}
