package com.denger.micotian.module.modules.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.ColorUtils;
import com.denger.micotian.utils.RenderUtil;
import com.denger.micotian.utils.font.CustomFontRenderer;
import com.denger.micotian.utils.font.FontUtils;

import java.awt.*;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public class NameTags extends Module {
    public static CustomFontRenderer icon = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/stylesicons.ttf"), 25, Font.PLAIN), true, true);

    public NameTags() {
        super("NameTags", Category.Render, 0);
    }


    @Override
    public void onRender3D() {
        super.onRender3D();
        for(Entity e : mc.world.loadedEntityList){
            if(e instanceof EntityPlayer && e != mc.player){
                double x = (e.lastTickPosX + (e.posX - e.lastTickPosX) * mc.getRenderPartialTicks()) - mc.getRenderManager().viewerPosX;
                double y = (e.lastTickPosY + (e.posY - e.lastTickPosY) * mc.getRenderPartialTicks()) - mc.getRenderManager().viewerPosY;
                double z = (e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * mc.getRenderPartialTicks()) - mc.getRenderManager().viewerPosZ;
                glPushMatrix();
                glDisable(GL_DEPTH_TEST);
                glDisable(GL_TEXTURE_2D);
                glNormal3f(0, 1, 0);
                GlStateManager.disableLighting();
                GlStateManager.enableBlend();
                float size = Math.min(Math.max(1.2f * (mc.player.getDistanceToEntity(e) * 0.15f), 1.25f), 6f) * (0.015f);
                glTranslatef((float)(x), (float)(y) + e.height + 0.6f, (float)(z));
                GlStateManager.glNormal3f(0, 1, 0);
                GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0, 1, 0);
                GlStateManager.rotate(mc.getRenderManager().playerViewX, 1, 0, 0);
                glScalef(-size, -size, -size);
                int health = (int)((((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth()) * 100);
                int hel = (int) ((((mc.fontRendererObj.getStringWidth(e.getName()) / 2) + 16) - ((-mc.fontRendererObj.getStringWidth(e.getName() + " " + health + "%") / 2) - 2)) / ((EntityPlayer) e).getMaxHealth());
                RenderUtil.drawSmoothRect((-fr.getStringWidth(e.getName() + " " + health + "%") / 2) - 2, -2, (fr.getStringWidth(e.getName()) / 2) + 16, 10, Integer.MIN_VALUE);
                RenderUtil.drawSmoothRect((-fr.getStringWidth(e.getName() + " " + health + "%") / 2) - 2, 10, (-fr.getStringWidth(e.getName() + " " + health + "%") / 2)  + hel * ((EntityPlayer) e).getHealth(), 12, ColorUtils.getHealthColor((EntityLivingBase) e).getRGB());
                fr.drawString(e.getName() + " " + TextFormatting.GREEN + health + "%",  0 - getcenter(e.getName() + " " + TextFormatting.GREEN + health + "%") , 1, -1);

                int posX = (-mc.fontRendererObj.getStringWidth(e.getName()) / 2) - 8;
                if(Item.getIdFromItem(((EntityPlayer) e).inventory.getCurrentItem().getItem()) != 0){
                    mc.getRenderItem().zLevel = -100.0f;
                    mc.getRenderItem().renderItemIntoGUI(((EntityPlayer) e).getHeldItem(EnumHand.MAIN_HAND), posX - 2, -20);
                    mc.getRenderItem().zLevel = 0.0f;
                    int posY = -30;
                    final Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(((EntityPlayer) e).inventory.getCurrentItem());
                    for(final Enchantment enchantment : enchantments.keySet()){
                        final int level = EnchantmentHelper.getEnchantmentLevel(enchantment, ((EntityPlayer) e).inventory.getCurrentItem());
                        mc.fontRendererObj.drawStringWithShadow(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level, posX + 6 - getcenter(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level), posY, -1);
                        posY -= 12;
                    }
                    posX += 15;
                }
                for(final ItemStack item : e.getArmorInventoryList()){

                    mc.getRenderItem().zLevel = -100.0f;
                    mc.getRenderItem().renderItemIntoGUI(item, posX, -20);
                    mc.getRenderItem().zLevel = 0.0f;
                    int posY = -30;
                    final Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(item);
                    for(final Enchantment enchantment : enchantments.keySet()){
                        final int level = EnchantmentHelper.getEnchantmentLevel(enchantment, item);
                        mc.fontRendererObj.drawStringWithShadow(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level, posX + 9 - getcenter((enchantment.getName().substring(12).charAt(0)) + level), posY, -1);
                        posY -= 12;
                    }
                    posX += 17;
                }
                int gapples = 0;
                //322
                if(Item.getIdFromItem(((EntityPlayer) e).inventory.getCurrentItem().getItem()) == 322){
                    gapples = ((EntityPlayer) e).inventory.getCurrentItem().func_190916_E();
                }else if(Item.getIdFromItem(((EntityPlayer) e).getHeldItemOffhand().getItem()) == 322){
                    gapples = ((EntityPlayer) e).getHeldItemOffhand().func_190916_E();
                }
                if(gapples > 0){
                    mc.getRenderItem().zLevel = -100.0f;
                    mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.GOLDEN_APPLE), posX, -20);
                    mc.getRenderItem().zLevel = 0.0f;
                    mc.fontRendererObj.drawStringWithShadow(String.valueOf(gapples), posX + 9 - getcenter(String.valueOf(gapples)), -30, -1);
                }
                glEnable(GL_DEPTH_TEST);
                glPopMatrix();
            }
        }
    }

    public int getcenter(String text){
        return mc.fontRendererObj.getStringWidth(text) / 2;
    }

    public int getcenter(int text){
        return mc.fontRendererObj.getStringWidth(String.valueOf(text)) / 2;
    }
}
