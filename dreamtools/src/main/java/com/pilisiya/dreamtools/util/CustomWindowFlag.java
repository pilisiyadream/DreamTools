package com.pilisiya.dreamtools.util;

import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;


public class CustomWindowFlag {
    /**
     * If you set this flag to your activity's window, you will be able to receive
     * KEYCODE_HOME and KEYCODE_APP_SWITCH in your onKeyDown and onKeyUp method, and
     * press HOME or APP_SWITCH key will not make your app return to home or launch recently apps.
     */
    public static final int CUSTOM_FLAG_CAN_RECEIVE_HOME = 0x00000001;


    /**
     * Set the custom flags to window.
     *
     * @param win   The target window.
     * @param flags The flags to set. Now we just have the CUSTOM_FLAG_CAN_RECEIVE_HOME.
     * @return Return false if your android system do not support this flag.
     */
    public static boolean addCustomFlags(Window win, int flags) {
        try {
            Class<?> cls = win.getClass();
            final Class<?>[] PARAM_TYPES = new Class[]{int.class};
            Method method = cls.getMethod("addCustomFlags", PARAM_TYPES);
            method.setAccessible(true);
            method.invoke(win, new Object[]{flags});
            return true;
        } catch (Exception e) {
            Log.w("CustomWindowFlag", "Do not support custom window flags " + e);
        }
        return false;
    }

    public static boolean addCustomFlags(Window win) {
        return addCustomFlags(win, CUSTOM_FLAG_CAN_RECEIVE_HOME);
    }

    /**
     * Remove the custom flags to window.
     *
     * @param win   The target window.
     * @param flags The flags to remove. Now we just have the CUSTOM_FLAG_CAN_RECEIVE_HOME.
     * @return Return false if your android system do not support this flag.
     */
    public static boolean clearCustomFlags(Window win, int flags) {
        try {
            Class<?> cls = win.getClass();
            final Class<?>[] PARAM_TYPES = new Class[]{int.class};
            Method method = cls.getMethod("clearCustomFlags", PARAM_TYPES);
            method.setAccessible(true);
            method.invoke(win, new Object[]{flags});
            return true;
        } catch (Exception e) {
            Log.w("CustomWindowFlag", "Do not support custom window flags " + e);
        }
        return false;
    }

    /**
     * 适用于4.0以前版本；
     *
     * @param win
     */
    public static void setTypeKeyguardDialog(Window win) {
        try {
            win.setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 屏蔽HOME键
     *
     * @param win
     */
    public static void disableHomeKey(Window win) {
        addCustomFlags(win);
    }
}
