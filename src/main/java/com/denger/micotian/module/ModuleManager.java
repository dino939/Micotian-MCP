package com.denger.micotian.module;


import com.denger.micotian.module.hud.*;
import com.denger.micotian.module.misc.*;
import com.denger.micotian.module.render.*;
import com.denger.micotian.module.Combat.*;
import com.denger.micotian.module.move.*;


import java.util.ArrayList;

public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<Module>();

    public void init(){
        modules.add(new test());
        modules.add(new ClickGui());
        modules.add(new AutoSprint());
        modules.add(new AntiBot());
        modules.add(new GuiMove());
        modules.add(new Tracers());
        modules.add(new ModuleList());
        modules.add(new WaterMark());
        modules.add(new Coords());
        //modules.add(new ArrmorHud());
        modules.add(new Notifications());
        modules.add(new FreeCam());
        modules.add(new MCF());
        modules.add(new ChinaHat());
        modules.add(new Bright());
        modules.add(new NoHurtCam());
        modules.add(new NameProtect());
        modules.add(new Panic());
        modules.add(new VClip());
        modules.add(new Timer());
        modules.add(new Strafe());
        modules.add(new TargetHud());
        modules.add(new HudEditor());
        modules.add(new NameTags());

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
