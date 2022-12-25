package com.denger.micotian.utils.CFont;

import net.minecraft.util.*;
import java.awt.*;
import net.minecraft.client.*;

public final class FontUtils
{
    public static CustomFontRenderer fr;
    
    public static Font getFontFromTTF(final ResourceLocation loc, final float fontSize, final int fontType) {
        try {
            Font output = Font.createFont(fontType, Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream());
            output = output.deriveFont(fontSize);
            return output;
        }
        catch (Exception var5) {
            return null;
        }
    }
    
    static {
        FontUtils.fr = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("fonts/aboba.ttf"), 20.0f, 0), true, true);
    }
}
