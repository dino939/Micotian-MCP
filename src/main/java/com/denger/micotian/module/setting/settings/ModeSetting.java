package com.denger.micotian.module.setting.settings;

import com.denger.micotian.module.Module;
import com.denger.micotian.module.setting.Setting;

public class ModeSetting extends Setting {
    private String val;
    private String[] strings;
    public ModeSetting(String name, Module module, String val,  String... strings) {
        super(name, module);
        this.strings = strings;
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }
}
