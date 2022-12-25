package com.denger.micotian.module.setting;


import com.denger.micotian.module.Module;

import java.util.ArrayList;

public class SettingManager {
    private ArrayList<Setting> settings = new ArrayList<Setting>();


    public void add(Setting setting){
        settings.add(setting);
    }

    public ArrayList<Setting> getSettings(Module module){
        ArrayList<Setting> returns = new ArrayList<Setting>();
        for(Setting setting : settings){
            if(module == setting.getModule()){
                returns.add(setting);
            }
        }
        return returns;
    }

    public Setting getSetting(Module module, String name){
        for(Setting setting : settings){
            if(module == setting.getModule() && setting.getName() == name){
                return setting;
            }
        }
        return null;
    }


}
