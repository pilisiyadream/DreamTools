package com.pilisiya.dreamtools.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewDebug;

/**
 * Created by xujiang on  2020/10/12 16:01.
 * add：
 * android:ellipsize="marquee"
 * android:focusable="true"
 * android:focusableInTouchMode="true"
 * android:marqueeRepeatLimit="marquee_forever"
 * android:scrollHorizontally="true"
 * android:singleLine="true"
 * next:
 */
public class DreamScrollForeverTextView extends AppCompatTextView {
    public DreamScrollForeverTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public DreamScrollForeverTextView(Context context, AttributeSet attrs,
                                      int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public DreamScrollForeverTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    @ViewDebug.ExportedProperty(category = "focus")
    public boolean isFocused() {
        // TODO Auto-generated method stub
        // 重点
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        // TODO Auto-generated method stub
        super.onFocusChanged(true, direction, previouslyFocusedRect);// 重点
    }

}
