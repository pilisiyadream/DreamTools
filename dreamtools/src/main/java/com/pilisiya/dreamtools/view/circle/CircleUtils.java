package com.pilisiya.dreamtools.view.circle;

import android.content.Context;

/**
 * Created by xujiang on  2021/4/8 20:26.
 * add：
 * next:
 */
public class CircleUtils {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
