package com.pilisiya.dream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pilisiya.dreamtools.listener.IBackListener;
import com.pilisiya.dreamtools.view.DreamDialogFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn_1,btn_2,btn_3,btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(v -> {
            DreamDialogFactory.showTipsMessage(MainActivity.this, "提示", "测试提示消息长度，测试提示消息长度，测试提示消息长度", 10000, false);
        });
        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(v -> DreamDialogFactory.showConfirmMessage(MainActivity.this, "提示", "只含有确认键盘的弹窗", "确认", v1 -> {
        }));
        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(v -> DreamDialogFactory.showMainComfirmMessage(MainActivity.this, "提示", "只含有确认键盘的弹窗，屏蔽HOME鍵和返回鍵", "确认", v12 -> {
        }));
        btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(v -> DreamDialogFactory.showCustomMessage(MainActivity.this, "提示", "普通弹窗", "确认","取消", v13 -> {
        }, v14 -> {
        }));
    }
}
