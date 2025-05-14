package com.pilisiya.dream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Base64;
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
import com.pilisiya.dreamtools.view.yearmonnthday.MProgressDialog;
import com.pilisiya.dreamtools.wifiprinter.WifiPrinter;
import com.pilisiya.dreamtools.wifiprinter.WifiPrinterUtil;
import com.smartpos.offlineinstall.OfflineInstallApi;
import com.smartpos.offlineinstall.ServiceDeathException;
import com.smartpos.offlineinstall.aidl.OSTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class MainActivity extends Activity {

    private static final int TIME_OUT = 10011;
    private static final int AGAIN = 10012;
    private static final int QUERY = 10013;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10,
            btn_11, btn_12, btn_13, btn_14, btn_15, btn_16, btn_17, btn_18, btn_19, btn_20,
            btn_21;
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
            DreamDialogFactory.chooseSocialCardType3(this,  payType -> Log.e("ssss", "ssss" + payType));
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

        btn_21 = findViewById(R.id.btn_21);
        btn_21.setOnClickListener(view -> {



//            WifiPrinter.CrazyThreadPool.THREAD_POOL_EXECUTOR.execute(() -> {
//                WifiPrinter wifiPrinter = new WifiPrinter("192.168.2.4", 9100);
//                String base64 = "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";
//                // wifiPrinter.printPDF(Environment.getExternalStorageDirectory() + File.separator + "工商银行.pdf");
//                byte[] data = Base64.decode(base64, Base64.DEFAULT);
//                Bitmap loadBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                WifiPrinterUtil.convertBitmapToPdf(loadBitmap);
//                wifiPrinter.printPDF(Environment.getExternalStorageDirectory() + "/img.pdf");
//                wifiPrinter.closeIOAndSocket();
//            });
        });


    }


}
