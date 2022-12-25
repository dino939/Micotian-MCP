package com.denger.micotian.module.modules.render;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumHand;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class ESP extends Module {
    public ESP() {
        super("ESP", Category.Render, 0);
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        for(Entity e : mc.world.playerEntities){
            if(e != mc.player){
                final double x = (e.lastTickPosX + (e.posX - e.lastTickPosX) * mc.getRenderPartialTicks()) - mc.getRenderManager().viewerPosX;
                final double y = (e.lastTickPosY + (e.posY - e.lastTickPosY) * mc.getRenderPartialTicks()) - mc.getRenderManager().viewerPosY;
                final double z = (e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * mc.getRenderPartialTicks()) - mc.getRenderManager().viewerPosZ;
                glPushMatrix();
                glLineWidth(1.3f);
                glTranslated(x, y, z);
                glDisable(GL_TEXTURE_2D);
                glDisable(GL_DEPTH_TEST);
                glRotated(-mc.getRenderManager().playerViewY, 0, 1, 0);
                //glRotated(mc.getRenderManager().playerViewX, 1, 0, 0);

                glBegin(GL_LINE_STRIP);
                glVertex3d(0.55, -0.2, 0);
                glVertex3d(0.55, e.height + 0.2, 0);
                glVertex3d(e.width - 1.15, e.height + 0.2, 0);
                glVertex3d(e.width - 1.15, -0.2, 0);
                glVertex3d(0.55, -0.2, 0);
                glEnd();


                Color health = Color.GREEN.darker();
                if(((EntityPlayer)e).getHealth() >= 16){
                    health = Color.GREEN.darker();
                }else if(((EntityPlayer)e).getHealth() >= 8 && ((EntityPlayer)e).getHealth() <= 16){
                    health = Color.YELLOW;
                }else if(((EntityPlayer)e).getHealth() > 0 && ((EntityPlayer)e).getHealth() <= 8){
                    health = Color.RED;
                }
                glBegin(GL_LINE_STRIP);
                glColor4d(1, 1, 1, 1);
                glVertex3d(0.6, -0.2, 0);
                glVertex3d(0.6, e.height + 0.2, 0);
                glEnd();
                glBegin(GL_LINE_STRIP);
                glColor4d(health.getRed() / 255f, health.getGreen() / 255f, health.getBlue() / 255f, health.getAlpha() / 255f);
                glVertex3d(0.6, -0.2, 0);
                glVertex3d(0.6, (((EntityLivingBase) e).getHealth() / ((EntityLivingBase) e).getMaxHealth()) * (e.height + 0.2), 0);
                glVertex3d(0.6, -0.2, 0);


                glEnd();

                final float size = 0.013f;
                glScaled(-size, -size, -size);

                glEnable(GL_TEXTURE_2D);
                mc.fontRendererObj.drawStringWithShadow(String.valueOf((int)((((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth()) * 100)), -50 - mc.fontRendererObj.getStringWidth(String.valueOf((int)((((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth()) * 100))), (int)((((EntityLivingBase) e).getHealth() / ((EntityLivingBase) e).getMaxHealth()) * (e.height + 0.2)), -1);
                glDisable(GL_TEXTURE_2D);
                if(!(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBlock) && !(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)){
                    glEnable(GL_TEXTURE_2D);
                    mc.fontRendererObj.drawStringWithShadow(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND).getDisplayName(),
                            1 - (mc.fontRendererObj.getStringWidth(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND).getDisplayName()) / 2), 20, -1);
                    glDisable(GL_TEXTURE_2D);
                }

                glEnable(GL_DEPTH_TEST);
                glEnable(GL_TEXTURE_2D);
                glPopMatrix();
            }

        }

    }
}
