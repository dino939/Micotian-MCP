package com.denger.micotian.module.misc;

import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;

public class Panic extends Module {
    public Panic() {
        super("SelfDestruct", Category.Misc, 0);
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        for(Module module : Micotian.moduleManager.getEnabledModules()){
            module.toggle();
        }
        Micotian.enable = false;
    }
}
