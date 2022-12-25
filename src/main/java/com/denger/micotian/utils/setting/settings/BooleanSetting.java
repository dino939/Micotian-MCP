package com.denger.micotian.utils.setting.settings;

import com.denger.micotian.module.Module;
import com.denger.micotian.utils.setting.Setting;

public class BooleanSetting extends Setting {
    private boolean val;
    public BooleanSetting(String name, Module module, boolean val) {
        super(name, module);
        this.val = val;
    }

    public Boolean getVal(){
        return val;
    }

    public void setVal(boolean val) {
        this.val = val;
    }
}
