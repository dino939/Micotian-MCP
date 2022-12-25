package com.denger.micotian.module.modules.render;

import net.minecraft.entity.player.EntityPlayer;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;

public class Glowing extends Module {
    public Glowing() {
        super("Glowing", Category.Render, 0);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        for(EntityPlayer player : mc.world.playerEntities){
            if(player.isGlowing()){
                player.setGlowing(false);
            }
        }
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        for(EntityPlayer player : mc.world.playerEntities){
            if(!player.isGlowing()){
                player.setGlowing(true);
            }
        }
    }
}
