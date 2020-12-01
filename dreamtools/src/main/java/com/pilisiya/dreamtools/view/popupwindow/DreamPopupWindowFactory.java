package com.pilisiya.dreamtools.view.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pilisiya.dreamtools.R;

import java.util.List;

/**
 * Created by xujiang on  2020/7/14 17:29.
 * add：
 * next:
 */
public class DreamPopupWindowFactory {
    public static String selectItem;

    public static PopupWindow showPopupWindow(final Context context, List<String> dataList, final onButtonClickListener listener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dream_popwindow_layout, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置位置
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);

        PickerView pickerView = inflate.findViewById(R.id.pv_list);
        pickerView.setData(dataList);
        TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
        TextView tv_comfirm = inflate.findViewById(R.id.tv_comfirm);
        if (dataList.size() > 1) {
            selectItem = dataList.get(1);
            pickerView.setSelected(1);
        } else {
            selectItem = dataList.get(0);
            pickerView.setSelected(0);
        }
        pickerView.setOnSelectListener(text -> selectItem = text);
        tv_cancel.setOnClickListener(view -> {
            listener.onCancel();
            popupWindow.dismiss();
        });
        tv_comfirm.setOnClickListener(view -> {
            listener.onComfirm(selectItem);
            popupWindow.dismiss();
        });

        popupWindow.setOnDismissListener(() -> darkenBackground((Activity) context, 1f));

        darkenBackground((Activity) context, 0.2f);
        return popupWindow;
    }

    private static void darkenBackground(Activity context, Float bgcolor) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgcolor;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

}
