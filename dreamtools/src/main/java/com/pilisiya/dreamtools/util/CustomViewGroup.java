package com.pilisiya.dreamtools.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * 说明： Copyright 2019 福建联迪商用设备有限公司. All rights reserved.
 * 备注：
 *
 * @author created by xujiang on 2019/7/10
 * 包名：com.pilisiya.dreamtools.util
 * 电话: 17752885689
 */
public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return  true;
    }
}
