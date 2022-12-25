package com.denger.micotian;

import com.denger.micotian.module.Module;
import com.denger.micotian.module.ModuleManager;
import com.denger.micotian.module.setting.SettingManager;


public class Micotian {

    public static ModuleManager moduleManager;
    public static SettingManager settingManager;
    public static boolean enable = true;


    public static void onRender2D(){
        if(!enable){
            return;
        }
        for(Module module : moduleManager.getEnabledModules()){
            module.onRender2D();
        }
    }

    public static void onRender3D(){
        if(!enable){
            return;
        }
        for(Module module : moduleManager.getEnabledModules()){
            module.onRender3D();
        }
    }

    public static void onUpdate(){
        if(!enable){
            return;
        }
        for(Module module : moduleManager.getEnabledModules()){
            module.onUpdate();
        }
    }

    public static void init(){
        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        moduleManager.init();

    }


    public static void onKeyPressed(int key) {
        if(!enable){
            return;
        }
        for(Module module : moduleManager.getModules()){
            if(module.getKey() == key){
                module.toggle();
            }
        }
    }
}
