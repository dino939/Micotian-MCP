package com.denger.micotian.module.modules.misc;

import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.Rotation;
import com.denger.micotian.utils.TimerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

public class AutoMine extends Module {
    private TimerUtils timer;
    public AutoMine() {
        super("AutoMine", Category.Misc, 0);
        timer = new TimerUtils();
    }



    public Entity getMetka(){
        for(Entity entity : mc.world.loadedEntityList){
            if(entity instanceof EntityArmorStand && mc.player.getDistanceToEntity(entity) <= 3.3){
                return entity;
            }
        }
        return null;
    }

    public static float[] getNeededRotations(final Entity entityLivingBase) {
        final double d = entityLivingBase.posX - Minecraft.getMinecraft().player.posX;
        final double d2 = entityLivingBase.posZ - Minecraft.getMinecraft().player.posZ;
        final double d3 = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().player.getEntityBoundingBox().minY + (Minecraft.getMinecraft().player.getEntityBoundingBox().maxY - Minecraft.getMinecraft().player.getEntityBoundingBox().minY));
        final double d4 = MathHelper.sqrt(d * d + d2 * d2);
        final float f = (float)(MathHelper.atan2(d2, d) * 180.0 / 3.141592653589793) - 90.0f;
        final float f2 = (float)(-(MathHelper.atan2(d3, d4) * 180.0 / 3.141592653589793));
        return new float[] { f, f2 };
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Entity entity = getMetka();
        if(entity == null){
            return;
        }
        if(timer.hasReached(1000 / 2)){
            float[] root = getNeededRotations(entity);
            Rotation.setYaw(root[0]);
            Rotation.setPitch(root[1]);
            mc.playerController.attackEntity(mc.player, entity);
            mc.player.swingArm(EnumHand.MAIN_HAND);
            timer.reset();
        }

    }
}
