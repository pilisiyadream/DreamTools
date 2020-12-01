package com.pilisiya.dream;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.pilisiya.dreamtools.util.DropDownUtil;
import com.pilisiya.dreamtools.view.DreamDialogFactory;
import com.pilisiya.dreamtools.view.popupwindow.DreamPopupWindowFactory;
import com.pilisiya.dreamtools.view.popupwindow.onButtonClickListener;
import com.smartpos.offlineinstall.OfflineInstallApi;
import com.smartpos.offlineinstall.ServiceDeathException;
import com.smartpos.offlineinstall.aidl.OSTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class MainActivity extends AppCompatActivity {

    private static final int TIME_OUT = 10011;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10, btn_11, btn_12;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.d("onCreate");
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == TIME_OUT) {
                    DreamDialogFactory.dismissAlert(MainActivity.this);
                }
                super.handleMessage(msg);
            }
        };

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
        btn_4.setOnClickListener(v -> DreamDialogFactory.showCustomMessage(MainActivity.this, "提示", "普通弹窗", "确认", "取消", v13 -> {
        }, v14 -> {
        }));
        btn_5 = findViewById(R.id.btn_5);
        btn_5.setOnClickListener(v -> DropDownUtil.forbidDropDown(MainActivity.this));
        btn_6 = findViewById(R.id.btn_6);
        btn_6.setOnClickListener(v -> DropDownUtil.forbidStatusBar(MainActivity.this));
        btn_7 = findViewById(R.id.btn_7);
        btn_7.setOnClickListener(v -> {
            OfflineInstallApi install = new OfflineInstallApi();
            try {
                String version = install.getVersion();
                Log.i("MainActivity", "lib version :" + version);
                int phase = install.getPhase();
                Log.i("MainActivity", "phase :" + phase);
                List<OSTask> tasks = new ArrayList<>();
                OSTask task = new OSTask("11111", "/sdcard/APOS_NFC_switch.zip");
                tasks.add(task);
                new Thread(() -> {
                    try {
                        for (int i = 0; i < 100; i++) {
                            Log.i("MainActivity", "phase :" + install.getPhase());
                            Log.i("MainActivity", "process:" + install.getProgress());
                            Thread.sleep(5000);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (ServiceDeathException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }).start();
                String[] info = install.upgrade(tasks);
                Log.i("MainActivity", "info[0]=" + info[0] + ", info[1]=" + info[1]);

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("MainActivity", e.getMessage());
            }
        });
        btn_8 = findViewById(R.id.btn_8);
        btn_8.setOnClickListener(v -> {
            Logger.d("按钮8被点击了");
            DreamDialogFactory.showWebLoading(MainActivity.this, "提示", "加载中,请稍后......");
            handler.sendEmptyMessageDelayed(TIME_OUT, 40000);
        });
        btn_9 = findViewById(R.id.btn_9);
        btn_9.setOnClickListener(v -> DreamDialogFactory.dismissAlert(MainActivity.this));
        Handler mainHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e("xj", "信息：" + msg.arg1);
            }
        };
        btn_10 = findViewById(R.id.btn_10);
        btn_10.setOnClickListener(v -> {

        });
        btn_11 = findViewById(R.id.btn_11);
        btn_11.setOnClickListener(v -> {
            List<String> dadaList = new ArrayList<>();
            dadaList.add("中国电信");
            dadaList.add("中国联通");
            dadaList.add("中国移动");
            DreamPopupWindowFactory.showPopupWindow(this, dadaList, new onButtonClickListener() {
                @Override
                public void onComfirm(String selectItem) {
                    Log.e("xj", "selectItem:" + selectItem);
                }

                @Override
                public void onCancel() {
                    Log.e("xj", "onCancel" );
                }
            });
        });
        btn_12 = findViewById(R.id.btn_12);
        btn_12.setOnClickListener(v -> {
            DreamDialogFactory.showCustomMessage(this, 6000, "提示", "测试倒计时自动按确认", "确认", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("xj", "按了确认");
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("xj", "按了取消");
                }
            });
        });


    }


}
