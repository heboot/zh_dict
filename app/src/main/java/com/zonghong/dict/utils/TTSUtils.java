package com.zonghong.dict.utils;

import com.alibaba.fastjson.JSON;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.waw.hr.mutils.LogUtil;
import com.zonghong.dict.MAPP;

import java.util.Random;

public class TTSUtils {

    private static String AppId = "17081686";
    private static String AppKey = "iRqFPVdzQxjaHsNy0GOCL1K3";
    private static String AppSecret = "0IGNbmqMoatMbPXymc6VpUplzY3vfnT5";

    private static String TAG = "ttsutils";

    private static SpeechSynthesizer mSpeechSynthesizer;


    //106男 111女
    private static void init() {
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setAppId(AppId/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/);
        mSpeechSynthesizer.setApiKey(AppKey, AppSecret/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/);

        // 不使用压缩传输
//        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_AUDIO_ENCODE, SpeechSynthesizer.AUDIO_ENCODE_PCM);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "111");
//        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_AUDIO_RATE, SpeechSynthesizer.AUDIO_BITRATE_PCM);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "4");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "5");
        mSpeechSynthesizer.setContext(MAPP.mapp.getCurrentActivity()); // this 是Context的之类，如Activity
        mSpeechSynthesizer.setStereoVolume(1.0f, 1.0f);
        mSpeechSynthesizer.setSpeechSynthesizerListener(new SpeechSynthesizerListener() {
            @Override
            public void onSynthesizeStart(String s) {
                LogUtil.e(TAG, "onSynthesizeStart》》》" + s);
            }

            @Override
            public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {
                LogUtil.e(TAG, "onSynthesizeDataArrived》》》" + s);
            }

            @Override
            public void onSynthesizeFinish(String s) {
                LogUtil.e(TAG, "onSynthesizeFinish》》》" + s);
            }

            @Override
            public void onSpeechStart(String s) {
                LogUtil.e(TAG, "onSpeechStart》》》" + s);
            }

            @Override
            public void onSpeechProgressChanged(String s, int i) {
                LogUtil.e(TAG, "onSpeechProgressChanged》》》" + s);
            }

            @Override
            public void onSpeechFinish(String s) {
                LogUtil.e(TAG, "onSpeechFinish》》》" + s);
            }

            @Override
            public void onError(String s, SpeechError speechError) {
                LogUtil.e(TAG, "onError》》》" + s + ">>>" + JSON.toJSONString(speechError));
            }
        });

        mSpeechSynthesizer.initTts(TtsMode.ONLINE);
    }


    public static void speak(String str, int sex) {
        if (mSpeechSynthesizer == null) {
            init();
        }

        if (sex ==2) {
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        } else {
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "1");
        }


        mSpeechSynthesizer.speak(str);
    }

    public static void release() {
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.release();
            mSpeechSynthesizer = null;
        }
    }


}
