package com.denger.micotian.module.hud;

import com.denger.micotian.managers.FriendManager;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.CFont.FontUtils;
import com.denger.micotian.utils.ColorUtils;
import com.denger.micotian.utils.RenderUtil;
import com.denger.micotian.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Objects;

public class TargetHud extends Module {
    public TargetHud(){
        super("TargetHud", Category.Hud,0);
    }
    public static int Xpos = -10;
    public static int Ypos = -10;
    @Override
    public void onRender2D() {
        super.onRender2D();
        if (Ypos< 1|Xpos< -5|Ypos > GuiScreen.height|Xpos > GuiScreen.width){
            Xpos = 12;
            Ypos = 12;
        }
        try {
            for (EntityPlayer entity : mc.world.playerEntities){
                if (entity ==  mc.objectMouseOver.entityHit)
                {
                    this.targethud(entity);
                }
            }
        }catch (Exception exception){}

    }

    public void targethud(final EntityPlayer entity) {
        final ScaledResolution scaledResolution = new ScaledResolution(mc);
        final float n = Xpos ;
        final float n2 = Ypos ;
        GL11.glPushMatrix();
        GL11.glTranslated( n,  n2, (scaledResolution.getScaledWidth() / 2) + 10);
        RenderUtils.drawCastomLitium(-6, 0, 145, 45, 2,-2);
        RenderUtils.drawSmoothGradientRect( 35, 28, (int) (((entity).getHealth() * 5) + 35) , 38,ColorUtils.getColor(),ColorUtils.getColor2());

        if (FriendManager.isFriend(entity.getName())) {
            RenderUtils.drawShadowRect(28,8,28+FontUtils.fr.getStringWidth(entity.getName()),9, 10, ColorUtils.getColor2());

            FontUtils.fr.drawString(entity.getName(), 28.0f, 3.0f, Color.white.getRGB());
        }
        else {
            FontUtils.fr.drawString(entity.getName(), 28.0f, 3.0f, Color.white.getRGB());
        }
        try {
            mc.getTextureManager().bindTexture(Objects.requireNonNull(mc.getConnection()).getPlayerInfo(entity.getUniqueID()).getLocationSkin());
            Gui.drawScaledCustomSizeModalRect(2, 2, 8.0f, 8.0f, 8, 8, 22, 22, 64.0f, 64.0f);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        GL11.glPopMatrix();
    }
}
