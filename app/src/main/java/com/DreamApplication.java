package com;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import com.pilisiya.dreamtools.tts.SpeakUtil;
import com.pilisiya.dreamtools.tts.TTsConstant;

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
        SpeakUtil speakUtil = SpeakUtil.getInstance();
        speakUtil.initialTts(this, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        }, TTsConstant.VOICE_NORMAL_WOMAN);
    }
}
