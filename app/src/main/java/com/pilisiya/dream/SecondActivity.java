package com.pilisiya.dream;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_cancel, tv_ok;
                InterceptHomeKeyDialog dialog = new InterceptHomeKeyDialog(SecondActivity.this, com.pilisiya.dreamtools.R.style.dream_dialog_basic, true);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(com.pilisiya.dreamtools.R.layout.dream_dialog_show_tip);
                dialog.setCancelable(false);
                dialog.setOnKeyListener((dialog1, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                        return true;
                    } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog1.dismiss();
                        return false;
                    } else {
                        return false;
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                tv_cancel = dialog.findViewById(R.id.dialog_tip_cancel_btn);
                tv_cancel.setOnClickListener(view1 -> dialog.dismiss());
                tv_ok = dialog.findViewById(R.id.dialog_tip_confirm_btn);
                tv_ok.setOnClickListener(view12 -> dialog.dismiss());
                dialog.show();
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("xxxxxxxxxxxxxxx", "dispatchKeyEvent:" + event.getKeyCode());
        if (event.getKeyCode() == KeyEvent.KEYCODE_HOME || event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onAttachedToWindow() {
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }
}