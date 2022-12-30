package com.denger.micotian.module.hud;

import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.notifications.NotificationManager;

public class Notifications extends Module {
    public Notifications() {
        super("Notifications",Category.Hud, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        NotificationManager.Render();
    }
}
