package com.denger.micotian.module.modules.render;

import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.notifications.NotificationManager;

public class Notifications extends Module {
    public Notifications() {
        super("Notifications", Category.Render, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        NotificationManager.Render();
    }
}
