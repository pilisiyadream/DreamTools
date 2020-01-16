package com;

import android.app.Application;
import android.support.annotation.Nullable;

import com.eas.utils.log.Log;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * 说明： Copyright 2019 福建联迪商用设备有限公司. All rights reserved.
 * 备注：
 *
 * @author created by xujiang on 2019/8/12
 * 包名：com
 * 电话: 17752885689
 */
public class DreamApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //显示清晰的log日志，调试的时候使用
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
        //保存日志文件
        Log.setCanPrintDebugMsg(true);
        // 正式发布时，可以打开
        Log.setLogWritingSwitch(true, "/sdcard/DreamLog");
    }
}
