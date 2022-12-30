package com.denger.micotian.module.misc;

import com.denger.micotian.managers.FriendManager;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Mouse;

public class MCF extends Module {
    public boolean onFriend = true;
    public MCF() {
        super("MCF", Category.Misc, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (Mouse.isButtonDown(2) && mc.pointedEntity != null && mc.pointedEntity instanceof EntityLivingBase && this.onFriend) {
            this.onFriend = false;

            if(FriendManager.friends.contains(mc.objectMouseOver.entityHit.getName())){
                FriendManager.remove(mc.objectMouseOver.entityHit.getName());
            }else {
                FriendManager.add(mc.objectMouseOver.entityHit.getName());
            }

        }

        if (!Mouse.isButtonDown(2)) {
            this.onFriend = true;
        }
    }
}
