package com.denger.micotian.utils;

import net.minecraft.client.Minecraft;

public class Rotation {
    private static float Yaw;
    private static float Pitch;
    private static boolean settingYaw;
    private static boolean settingPitch;

    public static float getPitch() {
        float p = Pitch;
        Pitch = Minecraft.getMinecraft().player.rotationPitch;
        return p;
    }

    public static float getYaw() {
        float p = Yaw;
        Yaw = Minecraft.getMinecraft().player.rotationYaw;
        return p;
    }

    public static void setPitch(float pitch) {
        settingPitch = true;
        Pitch = pitch;
    }



    public static void setYaw(float yaw) {
        settingPitch = true;
        Yaw = yaw;
    }
}
