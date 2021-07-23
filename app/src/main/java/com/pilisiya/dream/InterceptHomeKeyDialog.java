package com.pilisiya.dream;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.WindowManager;

/**
 * Created by xujiang on  2021/6/10 10:19.
 * add：
 * next:
 */
public class InterceptHomeKeyDialog extends Dialog {
    private boolean forbidHomeKey = false;

    public InterceptHomeKeyDialog(@NonNull Context context) {
        super(context);
    }

    public InterceptHomeKeyDialog(@NonNull Context context, boolean forbidHomeKey) {
        super(context);
        this.forbidHomeKey = forbidHomeKey;
    }

    public InterceptHomeKeyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public InterceptHomeKeyDialog(@NonNull Context context, int themeResId, boolean forbidHomeKey) {
        super(context, themeResId);
        this.forbidHomeKey = forbidHomeKey;
    }

    protected InterceptHomeKeyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (Build.VERSION.SDK_INT < 24) {
                getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_HOME || event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
