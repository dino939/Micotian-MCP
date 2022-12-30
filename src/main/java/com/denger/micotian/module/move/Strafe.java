package com.denger.micotian.module.move;

import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.MoveUtils;
import com.denger.micotian.utils.setting.settings.FloatSetting;


public class Strafe extends Module {
    public Strafe(){
        super("Strafe", Category.Move,0);
        Micotian.settingManager.add(speed = new FloatSetting("Slider", this, 1.0f, 35.0f, 23.0f));
    }
    FloatSetting speed;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mc.player.isSneaking()) {
            return;
        }
        if (this.mc.player.onGround) {
            MoveUtils.setSpeed(this.speed.getVal() / 100.0f - 0.05);
        }
        else {
            MoveUtils.setSpeed(this.speed.getVal() / 100.0f);
        }
    }

}
