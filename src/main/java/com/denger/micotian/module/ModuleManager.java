package com.denger.micotian.module;

import com.denger.micotian.module.modules.misc.*;
import com.denger.micotian.module.modules.render.*;
import com.denger.micotian.module.modules.Combat.*;
import com.denger.micotian.module.modules.move.*;

import java.util.ArrayList;

public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<Module>();

    public void init(){
        modules.add(new test());
        modules.add(new ClickGui());
        modules.add(new ESP());
        modules.add(new AutoSprint());
        modules.add(new AntiBot());
        modules.add(new GuiMove());
        modules.add(new Tracers());
        modules.add(new ModuleList());
        modules.add(new WaterMark());
        modules.add(new ArrmorHud());
        modules.add(new Notifications());
        modules.add(new MCF());
        modules.add(new Bright());
        modules.add(new WallHack());
        modules.add(new Panic());
        modules.add(new VClip());
        modules.add(new Timer());

    }

    public ArrayList<Module> getModules() {
        return modules;
    }


    public ArrayList<Module> getModulesInCategory( Category category) {
        ArrayList<Module> reurns = new ArrayList<Module>();
        for(Module module : modules){
            if(module.getCategory() == category){
                reurns.add(module);
            }
        }
        return reurns;
    }
    public Module getModule(String name){
        for(Module module : modules){
            if(module.getName() == name){
                return module;
            }
        }
        return null;
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> reurns = new ArrayList<Module>();
        for(Module module : modules){
            if(module.isEnable()){
                reurns.add(module);
            }
        }
        return reurns;
    }
}
