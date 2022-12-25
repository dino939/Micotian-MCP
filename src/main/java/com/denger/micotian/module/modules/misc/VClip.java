package com.denger.micotian.module.modules.misc;

import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.setting.settings.FloatSetting;

public class VClip extends Module {
    private FloatSetting blocks;
    public VClip() {
        super("VClip", Category.Misc, 0);
        Micotian.settingManager.add(blocks = new FloatSetting("Blocks", this, -10, 10, 1));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if(mc.player.isRidingHorse()){
            mc.player.setPosition(mc.player.posX, mc.player.posY + blocks.getVal(), mc.player.posZ);
        }
        this.toggle();
    }
}
