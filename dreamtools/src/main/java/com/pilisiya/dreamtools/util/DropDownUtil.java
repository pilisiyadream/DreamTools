package com.pilisiya.dreamtools.util;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * 说明： Copyright 2019 福建联迪商用设备有限公司. All rights reserved.
 * 备注：系统菜单栏打开或者关闭
 *
 * @author created by xujiang on 2019/7/10
 * 包名：com.pilisiya.dreamtools.util
 * 电话: 17752885689
 */
public class DropDownUtil {
    private static WindowManager manager;
    private static CustomViewGroup view;

    public static void forbidDropDown(Context context) {
        manager = ((WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE));
        WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        localLayoutParams.gravity = Gravity.TOP;
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                // this is to enable the notification to recieve touch events
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                // Draws over status bar
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        localLayoutParams.height = (int) (50 * context.getResources()
                .getDisplayMetrics().scaledDensity);
        localLayoutParams.format = PixelFormat.TRANSPARENT;
        view = new CustomViewGroup(context);
        manager.addView(view, localLayoutParams);
    }


    public static void allowDropDown(Context context) {
        if (manager == null) {
            manager = ((WindowManager) context.getApplicationContext()
                    .getSystemService(Context.WINDOW_SERVICE));
        }
        if (view == null) {
            view = new CustomViewGroup(context);
        }
        manager.removeView(view);
    }


    public static void forbidStatusBar(Context context) {
        Settings.System.putInt(context.getContentResolver(), "status_bar_disabled", 0);
    }


}
