package com.denger.micotian.utils;


import net.minecraft.client.*;
import net.minecraft.util.*;

public class MoveUtils
{
    public static Minecraft mc;
    static double speedchlen;

    public static void setSpeed(final double n) {
        double n2 = mc.player.movementInput.field_192832_b;
        double n3 = mc.player.movementInput.moveStrafe;
        float rotationYaw = MoveUtils.mc.player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            MoveUtils.mc.player.motionX = 0.0;
            MoveUtils.mc.player.motionZ = 0.0;
        }
        else {
            if (n2 != 0.0) {
                if (n3 > 0.0) {
                    rotationYaw += ((n2 > 0.0) ? -45 : 45);
                }
                else if (n3 < 0.0) {
                    rotationYaw += ((n2 > 0.0) ? 45 : -45);
                }
                n3 = 0.0;
                if (n2 > 0.0) {
                    n2 = 1.0;
                }
                else if (n2 < 0.0) {
                    n2 = -1.0;
                }
            }
            MoveUtils.mc.player.motionX = n2 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + n3 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f));
            MoveUtils.mc.player.motionZ = n2 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n3 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f));
        }
    }

    public static double getSpeed() {
        return MoveUtils.speedchlen = Math.sqrt(MoveUtils.mc.player.motionX * MoveUtils.mc.player.motionX + MoveUtils.mc.player.motionZ * MoveUtils.mc.player.motionZ + MoveUtils.mc.player.motionY * MoveUtils.mc.player.motionY) * 10.0;
    }

    static {
        MoveUtils.mc = Minecraft.getMinecraft();
    }
}
