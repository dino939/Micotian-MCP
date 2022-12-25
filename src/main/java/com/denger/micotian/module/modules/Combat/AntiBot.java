package com.denger.micotian.module.modules.Combat;

import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AntiBot extends Module {
    public AntiBot() {
        super("AntiBot", Category.Combat, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        for(Entity entity : mc.world.loadedEntityList){
            if(entity instanceof EntityPlayer && entity.isInvisible() && entity != mc.player){
                mc.world.removeEntity(entity);
            }
        }
    }
}
