package com.denger.micotian.module.render;


import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.CFont.FontUtils;
import com.denger.micotian.utils.ColorUtils;
import com.denger.micotian.utils.RenderUtils;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.text.*;
import java.awt.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.client.renderer.*;

public class NameTags extends Module
{
    public NameTags() {
        super("NameTags", Category.Render, 0);
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        for (final Entity e : this.mc.world.loadedEntityList) {
            if (e instanceof EntityPlayer && e != this.mc.player) {
                final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * this.mc.getRenderPartialTicks() - this.mc.getRenderManager().viewerPosX;
                final double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * this.mc.getRenderPartialTicks() - this.mc.getRenderManager().viewerPosY;
                final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * this.mc.getRenderPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                GL11.glPushMatrix();
                GL11.glDisable(2929);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                final float size = Math.min(Math.max(1.2f * (this.mc.player.getDistanceToEntity(e) * 0.15f), 1.25f), 6.0f) * 0.015f;
                GL11.glTranslatef((float)x, (float)y + e.height + 0.6f, (float)z);
                GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                GL11.glScalef(-size, -size, -size);
                final int health = (int)(((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth() * 100.0f);
                final String name = e.getName() /*+ TextFormatting.GREEN */+ " HP: " + health;
                final float wight = (float)(FontUtils.fr.getStringWidth(name) + 10);
                RenderUtils.drawShadowRect(-(wight / 2.0f), 0.0, wight / 2.0f, 15.0, 3);
                RenderUtils.drawCastomLitium((int) -(wight / 2.0f), (int) 0.0f, (int) (wight / 2.0f), (int) 15.0f,2,-2);
                final int b = (int)(wight - 4.0f);
                RenderUtils.drawShadowRect(-(wight / 2.0f) + 2.0f, 11.0, -(wight / 2.0f) + 2.0f + b / ((EntityPlayer)e).getMaxHealth() * ((EntityPlayer)e).getHealth(), 13.0, 1);
                double mx = ((FontUtils.fr.getStringWidth(name)/2));
                FontUtils.fr.drawString(name, -mx, (float)(7 - FontUtils.fr.getHeight() / 2), -1);
                final ArrayList<ItemStack> items = new ArrayList<ItemStack>();
                if (!(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                    items.add(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND));
                }
                for (final ItemStack itemStack : e.getArmorInventoryList()) {
                    if (!(itemStack.getItem() instanceof ItemAir)) {
                        items.add(itemStack);
                    }
                }
                if (!(((EntityPlayer)e).getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                    items.add(((EntityPlayer)e).getHeldItem(EnumHand.OFF_HAND));
                }
                int i = -(items.size() * 16 / 2);
                for (final ItemStack itemStack2 : items) {
                    final RenderItem renderItem = this.mc.getRenderItem();
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    RenderHelper.enableStandardItemLighting();
                    renderItem.zLevel = -100.0f;
                    renderItem.renderItemIntoGUI(itemStack2, i, -20);
                    renderItem.zLevel = 0.0f;
                    RenderHelper.disableStandardItemLighting();
                    GlStateManager.enableAlpha();
                    GlStateManager.disableBlend();
                    GlStateManager.disableLighting();
                    GlStateManager.popMatrix();
                    GlStateManager.pushMatrix();
                    GlStateManager.disableLighting();
                    GlStateManager.disableDepth();
                    GlStateManager.popMatrix();
                    i += 16;
                }
                GL11.glEnable(2929);
                GL11.glColor3f(255.0f, 255.0f, 255.0f);
                GL11.glEnable(2929);
                GL11.glPopMatrix();
            }
        }
    }
}
