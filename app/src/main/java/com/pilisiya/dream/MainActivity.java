package com.pilisiya.dream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.ColorTextView;
import com.orhanobut.logger.Logger;
import com.pilisiya.dreamtools.listener.DreamIBackListener;
import com.pilisiya.dreamtools.listener.DreamIOkListener;
import com.pilisiya.dreamtools.listener.DreamIdListener;
import com.pilisiya.dreamtools.util.DreamLogUtil;
import com.pilisiya.dreamtools.util.DropDownUtil;
import com.pilisiya.dreamtools.view.DreamDialogFactory;
import com.pilisiya.dreamtools.view.loading.ShapeLoadingDrawable;
import com.pilisiya.dreamtools.view.popupwindow.DreamPopupWindowFactory;
import com.pilisiya.dreamtools.view.popupwindow.onButtonClickListener;
import com.pilisiya.dreamtools.view.yearmonnthday.DreamDialogWheelDateTime;
import com.smartpos.offlineinstall.OfflineInstallApi;
import com.smartpos.offlineinstall.ServiceDeathException;
import com.smartpos.offlineinstall.aidl.OSTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class MainActivity extends Activity {

    private static final int TIME_OUT = 10011;
    private static final int AGAIN = 10012;
    private static final int QUERY = 10013;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10, btn_11, btn_12, btn_13, btn_14, btn_15, btn_16, btn_17, btn_18, btn_19, btn_20;
    private Handler handler;
    private int queryTime = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.d("onCreate");
        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == TIME_OUT) {
                    DreamDialogFactory.dismissAlert(MainActivity.this);
                } else if (msg.what == AGAIN) {
                    DreamDialogFactory.showWebLoading(MainActivity.this, "提示", "支付结果查询中,请稍后...", queryTime + "");
                    handler.sendEmptyMessageDelayed(QUERY, 1000);
                } else {
                    queryTime--;
                    if (queryTime == 0) {
                        DreamDialogFactory.dismissAlert(MainActivity.this);
                    } else {
                        handler.sendEmptyMessage(AGAIN);
                    }
                }
                super.handleMessage(msg);
            }
        };

        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(v -> {
            DreamDialogFactory.showTipsMessage(MainActivity.this, "提示", "测试提示消息长度，测试提示消息长度，测试提示消息长度", 10 * 1000, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DreamLogUtil.e("点击了取消");
                }
            });
        });
        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(v -> DreamDialogFactory.showConfirmMessage(MainActivity.this, "提示", "只含有确认键盘的弹窗", "确认", v1 -> {
        }));
        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(v -> DreamDialogFactory.showMainComfirmMessage(MainActivity.this, "提示", "只含有确认键盘的弹窗，屏蔽HOME鍵和返回鍵", "确认", v12 -> {
        }));
        btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(v -> DreamDialogFactory.showCustomMessage(MainActivity.this, "提示", "进行扫码预授权完成撤销，操作后资金将全额退款且不可找回，请谨慎操作。如需重新收款请联系消费者。", "确认", "取消", v13 -> {
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
            queryTime = 10;
            //DreamDialogFactory.showWebLoading(MainActivity.this, "提示", "加载中,请稍后......");
            handler.sendEmptyMessage(AGAIN);
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
            DreamDialogWheelDateTime time = new DreamDialogWheelDateTime(this);
            time.getSureView().setOnClickListener(view -> {
                int year = time.getSelectorYear();
                String month = time.getSelectorMonth();
                String day = time.getSelectorDay();
                String hour = time.getSelectorHour();
                String minute = time.getSelectorMinute();
                String second = time.getSelectorSecond();
                Log.e("time", year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);
                time.dismiss();
            });
            time.getCancleView().setOnClickListener(view -> time.dismiss());
            time.setCanceledOnTouchOutside(false);
            time.show();
        });
        btn_11 = findViewById(R.id.btn_11);
        btn_11.setOnClickListener(v -> {
            List<String> dadaList = new ArrayList<>();
            dadaList.add("中国电信");
            dadaList.add("我是一只酸菜鱼，我可以唱歌跳舞,我是一只酸菜鱼");
            dadaList.add("中国移动");
            dadaList.add("测试测试");
            DreamPopupWindowFactory.showPopupWindow(this, dadaList, new onButtonClickListener() {
                @Override
                public void onComfirm(String selectItem) {
                    Log.e("xj", "selectItem:" + selectItem);
                }

                @Override
                public void onCancel() {
                    Log.e("xj", "onCancel");
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

        ((ColorTextView) findViewById(R.id.tv_color)).setText("我是一条鱼");
        btn_13 = findViewById(R.id.btn_13);
        btn_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        ImageView imageView = findViewById(R.id.iv_shape_loading);

        ShapeLoadingDrawable shapeLoadingDrawable = new ShapeLoadingDrawable();
        imageView.setImageDrawable(shapeLoadingDrawable);
        shapeLoadingDrawable.start();

//        ThreeBallsLoadingDrawable threeBallsLoadingDrawable = new ThreeBallsLoadingDrawable();
//        imageView.setImageDrawable(threeBallsLoadingDrawable);
//        threeBallsLoadingDrawable.start();

//        DouYinLoadingDrawable douYinLoadingDrawable = new DouYinLoadingDrawable();
//        imageView.setImageDrawable(douYinLoadingDrawable);
//        douYinLoadingDrawable.start();


        btn_14 = findViewById(R.id.btn_14);
        btn_14.setOnClickListener(view -> DreamDialogFactory.showWebLoadingThreeBall(MainActivity.this, "提示", "网络加载中，请稍后..."));

        btn_15 = findViewById(R.id.btn_15);
        btn_15.setOnClickListener(view -> DreamDialogFactory.showWebLoadingShape(MainActivity.this, "提示", "网络加载中，请稍后..."));


        btn_16 = findViewById(R.id.btn_16);
        btn_16.setOnClickListener(view -> {
            DreamDialogFactory.choosePayType(this, "22.99", payType -> Log.e("ssss", "ssss" + payType));
        });


        btn_17 = findViewById(R.id.btn_17);
        btn_17.setOnClickListener(view -> {
            // 广播方式屏蔽home键 此方式是全局的，在不需要的时候应用需要主动取消禁用，
            // 否则将影响到其他应用。终端重启后将自动恢复成可用状态
            Intent it = new Intent();
            it.setAction("android.intent.action.HOME_KEY_DISABLE");
            sendBroadcast(it);
        });

        btn_18 = findViewById(R.id.btn_18);
        btn_18.setOnClickListener(view -> {
            // 广播方式取消屏蔽home键
            Intent it = new Intent();
            it.setAction("android.intent.action.HOME_KEY_ENABLE");
            sendBroadcast(it);
        });

        btn_19 = findViewById(R.id.btn_19);
        btn_19.setOnClickListener(view -> {
            getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        });

        btn_20 = findViewById(R.id.btn_20);
        btn_20.setOnClickListener(view -> {
            DreamDialogFactory.showInputId(this, "请输入缴款人身份信息", (name, id) -> DreamLogUtil.e("XU", "name:" + name + "id:" + id), new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        });


    }


}
