package com.pilisiya.dream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pilisiya.dreamtools.listener.IBackListener;
import com.pilisiya.dreamtools.view.DreamDialogFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn_ceshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ceshi = findViewById(R.id.btn_cceshi);
        btn_ceshi.setOnClickListener(v -> {
            DreamDialogFactory.showTipsMessage(MainActivity.this, "测试提示消息长度，测试提示消息长度", 10000,false, new IBackListener() {
                @Override
                public void onBack() {

                }
            });
        });
    }
}
