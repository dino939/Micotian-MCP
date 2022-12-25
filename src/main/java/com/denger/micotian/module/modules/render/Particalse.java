package com.denger.micotian.module.modules.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.ColorUtils;
import com.denger.micotian.utils.MathUtils;
import com.denger.micotian.utils.RenderUtil;
import com.denger.micotian.utils.RenderUtils;

import java.awt.*;
import java.util.ArrayList;

public class Particalse extends Module {
    ArrayList<partical> particals = new ArrayList<>();
    public Particalse() {
        super("Particalse", Category.Render, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        for(Entity entity : mc.world.loadedEntityList ){
            if(entity instanceof  EntityPlayer){
                EntityPlayer player = (EntityPlayer) entity;
                if(player.hurtTime>0){
                    for (int i = 0; i < 2; i++) {
                        particals.add(new partical(player.posX, MathUtils.getRandomInRange(player.posY + player.height, player.posY ) , player.posZ));

                    }
                }
            }
        }

        for (int i = 0; i < particals.size(); i++) {
            if(System.currentTimeMillis() - particals.get(i).getTime() >= 1000 * 10){
                particals.remove(i);
            }
        }
        for(partical partical : particals){
            partical.update();
        }
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        for(partical partical : particals){
            partical.render();
        }
    }

    public static class partical{
        double x;
        double y;
        double z;
        float motionX;
        float motionY;
        float motionZ;
        long time;
        public partical(double x, double y, double z){
            this.x = x;
            this.y = y;
            this.z = z;
            motionX = MathUtils.getRandomInRange(-0.1f, 0.1f);
            motionY = -0.01f;
            motionZ = MathUtils.getRandomInRange(-0.1f, 0.1f);
            time = System.currentTimeMillis();
        }


        public long getTime() {
            return time;
        }

        public void update(){
            x+=motionX;
            if(Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y-0.01f, z)).getBlock() != Blocks.AIR){
                if(Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y+0.1f, z)).getBlock() != Blocks.AIR){
                    motionX = -motionX;
                    motionZ = -motionZ;
                }

            }else {
                y+=motionY;
                if(Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y, z)).getBlock() != Blocks.AIR){
                    motionX = -motionX;
                    motionZ = -motionZ;
                }

            }
            z+=motionZ;

            if(System.currentTimeMillis() - time >= 8500){
                motionZ=MathUtils.lerp(motionZ, 0, 0.001f);
                motionY=MathUtils.lerp(motionY, 0, 0.001f);
                motionX=MathUtils.lerp(motionX, 0, 0.001f);
            }
        }

        public void render(){
            GL11.glPushMatrix();
            RenderUtil.enableGL2D();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            final double posX = RenderUtil.getRenderPos(x, y, z).xCoord;
            final double posY = RenderUtil.getRenderPos(x, y, z).yCoord;
            final double posZ = RenderUtil.getRenderPos(x, y, z).zCoord;
            final double tau = 6.283185307179586;
            final double fans = 45.0;
            int color = ColorUtils.TwoColoreffect(255,48,0,246,186,55, (double)Math.abs(System.currentTimeMillis() / 4) / (100.0D + 0.1275D)*0.5f);
            float alpha = 130;
            GL11.glPointSize(7);
            GL11.glEnable(GL11.GL_POINT_SMOOTH);
            GL11.glBegin(GL11.GL_POINTS);
            RenderUtils.setupColor(new Color(color), (int) alpha);
            GL11.glVertex3d(posX, posY, posZ);
            GL11.glEnd();
            GL11.glPointSize(18);
            GL11.glEnable(GL11.GL_POINT_SMOOTH);
            GL11.glBegin(GL11.GL_POINTS);
            RenderUtils.setupColor(new Color(color), (int) (alpha/4));
            GL11.glVertex3d(posX, posY, posZ);
            GL11.glEnd();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            RenderUtil.disableGL2D();
            GL11.glEnable(2848);
            GL11.glDisable(2929);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDepthMask(false);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glShadeModel(7425);
            Minecraft.getMinecraft().entityRenderer.disableLightmap();
            GlStateManager.disableDepth();
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBegin(8);
            GL11.glDisable(GL11.GL_CULL_FACE);
            RenderUtils.setupColor(new Color(color), (int) alpha);
            GL11.glVertex3d(posX, posY + motionY + 0.01, posZ);
            GL11.glVertex3d(posX, posY - 0.01, posZ);
            RenderUtils.setupColor(new Color(color), 0);
            GL11.glVertex3d(posX - motionX*20,posY + motionY*2, posZ - motionZ*20);
            GL11.glVertex3d(posX - motionX*20,posY - motionY*18, posZ - motionZ*20);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnd();
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glShadeModel(7424);
            GL11.glDisable(3042);
            GL11.glDepthMask(true);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDisable(2848);;
            GlStateManager.resetColor();
            GL11.glPopMatrix();
        }



    }
}
