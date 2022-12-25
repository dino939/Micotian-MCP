package com.denger.micotian.module.modules.misc;

import com.denger.micotian.Micotian;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.module.setting.settings.FloatSetting;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class FreeCam extends Module {
    private float yaw;
    private float pitch;
    private float yawHead;
    private float gamma;
    private EntityOtherPlayerMP other;
    private float old;
    private EntityOtherPlayerMP fakePlayer = null;
    private double oldX;
    private double oldY;
    private double oldZ;

    private FloatSetting speed;


    public FreeCam() {
        super("FreeCam", Category.Misc, 0);
        Micotian.settingManager.add(speed = new FloatSetting("Speed", this,0, 0.5f, 0.01f));

    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.oldX = mc.player.posX;
        this.oldY = mc.player.posY;
        this.oldZ = mc.player.posZ;
        mc.player.noClip = true;
        EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
        fakePlayer.copyLocationAndAnglesFrom(mc.player);
        fakePlayer.posY -= 0.0;
        fakePlayer.rotationYawHead = mc.player.rotationYawHead;
        mc.world.addEntityToWorld(-69, fakePlayer);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        mc.player.noClip = true;
        mc.player.onGround = false;
        mc.player.capabilities.setFlySpeed((float)this.speed.getVal());
        mc.player.capabilities.isFlying = true;
        if (!mc.player.isInsideOfMaterial(Material.AIR)) {
            if (!mc.player.isInsideOfMaterial(Material.LAVA)) {
                if (!mc.player.isInsideOfMaterial(Material.WATER)) {
                    if (!(mc.gameSettings.gammaSetting < 100.0f)) return;
                    mc.gameSettings.gammaSetting += 0.08f;
                    return;
                }
            }
        }
        mc.gameSettings.gammaSetting = this.gamma;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.player.capabilities.isFlying = false;
        mc.player.capabilities.setFlySpeed(this.old);
        mc.player.rotationPitch = this.pitch;
        mc.player.rotationYaw = this.yaw;
        mc.world.removeEntityFromWorld(-1);
        mc.player.noClip = false;
        mc.renderGlobal.loadRenderers();
        mc.player.noClip = false;
        mc.player.setPositionAndRotation(this.oldX, this.oldY, this.oldZ, mc.player.rotationYaw, mc.player.rotationPitch);
        mc.world.removeEntityFromWorld(-69);
        this.fakePlayer = null;

    }
}
