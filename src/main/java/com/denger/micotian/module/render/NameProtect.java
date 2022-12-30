package com.denger.micotian.module.render;

import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.setting.settings.BooleanSetting;

public class NameProtect extends Module {
    private BooleanSetting friends;


    public NameProtect() {
        super("NameProtect", Category.Render, 0);

    }

}
