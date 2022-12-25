package com.denger.micotian.module.setting;

import com.denger.micotian.module.Module;

public class Setting {
    private String name;
    private Module module;
    public Setting(String name, Module module){
        this.name = name;
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public Module getModule() {
        return module;
    }
}
