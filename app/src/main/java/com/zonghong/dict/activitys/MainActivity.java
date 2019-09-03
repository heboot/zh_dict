package com.zonghong.dict.activitys;


import com.alibaba.fastjson.JSON;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.waw.hr.mutils.LogUtil;
import com.zonghong.dict.R;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityMainBinding;
import com.zonghong.dict.utils.IntentUtils;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        binding.qlytTeaching.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y15), 0.30f);
        binding.tvDetection.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y15), 0.30f);
    }

    @Override
    public void initData() {
        SpeechSynthesizer mSpeechSynthesizer = SpeechSynthesizer.getInstance();

        String AppId = "17081686";
        String AppKey = "iRqFPVdzQxjaHsNy0GOCL1K3";
        String AppSecret = "0IGNbmqMoatMbPXymc6VpUplzY3vfnT5";

        mSpeechSynthesizer.setAppId(AppId/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/);
        mSpeechSynthesizer.setApiKey(AppKey, AppSecret/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/);

        // 不使用压缩传输
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_AUDIO_ENCODE, SpeechSynthesizer.AUDIO_ENCODE_PCM);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_AUDIO_RATE, SpeechSynthesizer.AUDIO_BITRATE_PCM);
        mSpeechSynthesizer.setContext(this); // this 是Context的之类，如Activity

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

        mSpeechSynthesizer.initTts(TtsMode.MIX);

//        mSpeechSynthesizer.speak("我太难了");
    }

    @Override
    public void initListener() {
        binding.qlytTeaching.setOnClickListener((v) -> {
            IntentUtils.doIntent(TeachIndexActivity.class);
        });
        binding.tvDetection.setOnClickListener((v) -> {
            IntentUtils.doIntent(CheckWordActivity.class);
        });
    }
}
