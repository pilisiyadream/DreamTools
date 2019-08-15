package com.pilisiya.dreamtools.tts;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.pilisiya.dreamtools.tts.control.InitConfig;
import com.pilisiya.dreamtools.tts.control.MySyntherizer;
import com.pilisiya.dreamtools.tts.control.NonBlockSyntherizer;
import com.pilisiya.dreamtools.tts.listener.UiMessageListener;
import com.pilisiya.dreamtools.tts.util.OfflineResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明： Copyright 2019 福建联迪商用设备有限公司. All rights reserved.
 * 备注：使用百度语音合成
 *
 * @author created by xujiang on 2019/8/12
 * 包名：com.pilisiya.dreamtools.tts
 * 电话: 17752885689
 */
public class SpeakUtil {
    public static String appId = "16993406";
    public static String appKey = "h7fsXr1abaIhlzKn9FHAryAM";
    public static String secretKey = "7UGWU7KPYPy2R0ED5dHEBtaDwmP14ZnI";

    protected String offlineVoice = OfflineResource.VOICE_MALE;
    protected MySyntherizer synthesizer;
    /**
     * TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； 没有纯离线
     */
    protected TtsMode ttsMode = TtsMode.MIX;

    private Context context;

    private static SpeakUtil instance;

    public static SpeakUtil getInstance() {
        if (instance == null) {
            instance = new SpeakUtil();
        }
        return instance;
    }

    public void initialTts(Context context, Handler mainHandler, String voiceType) {
        this.context = context;
        // 设置初始化参数
        // 此处可以改为 含有您业务逻辑的SpeechSynthesizerListener的实现类
        SpeechSynthesizerListener listener = new UiMessageListener(mainHandler);
        Map<String, String> params = getParams(voiceType);

        // appId appKey secretKey 网站上您申请的应用获取。注意使用离线合成功能的话，需要应用中填写您app的包名。包名在build.gradle中获取。
        InitConfig initConfig = new InitConfig(appId, appKey, secretKey, ttsMode, params, listener);

        // 此处可以改为MySyntherizer 了解调用过程
        synthesizer = new NonBlockSyntherizer(context, initConfig, mainHandler);
    }

    /**
     * 合成的参数，可以初始化时填写，也可以在合成前设置。
     *
     * @return
     */
    protected Map<String, String> getParams(String personType) {
        Map<String, String> params = new HashMap<String, String>();
        // 以下参数均为选填
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        params.put(SpeechSynthesizer.PARAM_SPEAKER, personType);
        // 设置合成的音量，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_VOLUME, "9");
        // 设置合成的语速，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_SPEED, "5");
        // 设置合成的语调，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_PITCH, "5");

        params.put(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
        // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

        // 离线资源文件， 从assets目录中复制到临时目录，需要在initTTs方法前完成
        OfflineResource offlineResource = createOfflineResource(offlineVoice);
        // 声学模型文件路径 (离线引擎使用), 请确认下面两个文件存在
        params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, offlineResource.getTextFilename());
        params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE,
                offlineResource.getModelFilename());
        return params;
    }

    protected OfflineResource createOfflineResource(String voiceType) {
        OfflineResource offlineResource = null;
        try {
            offlineResource = new OfflineResource(context, voiceType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return offlineResource;
    }


    public void speak(String voice) {
        if (synthesizer == null) {
            Toast.makeText(context, "百度语音引擎未初始化，请先初始化再使用", Toast.LENGTH_LONG).show();
        } else {
            int result = synthesizer.speak(voice);
            checkResult(result, "speak");
        }

    }


    private void checkResult(int result, String method) {
        if (result != 0) {
            Log.e("xj", "error code :" + result + " method:" + method + ", 错误码文档:http://yuyin.baidu.com/docs/tts/122 ");
        }
    }

}
