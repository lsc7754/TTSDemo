package com.example.myttsservice;

import android.media.AudioFormat;
import android.speech.tts.SynthesisCallback;
import android.speech.tts.SynthesisRequest;
import android.speech.tts.TextToSpeechService;
import android.util.Log;

public class TTSService extends TextToSpeechService {
    private static final String TAG = "TTSService";
    private TTSController mController;

    @Override
    public void onCreate() {
        super.onCreate();
        mController = TTSControllerImpl.getInstance(this);
    }

    /**
     * 检查引擎是否支持该语言，会被多个线程调用
     */
    @Override
    protected int onIsLanguageAvailable(String lang, String country, String variant) {
        return 0;
    }

    /**
     * 返回tts引擎使用的语言、国家、变体。
     */
    @Override
    protected String[] onGetLanguage() {
        Log.d(TAG, "onGetLanguage");
        return new String[0];
    }


    /**
     * 检查语言是否支持
     */
    @Override
    protected int onLoadLanguage(String lang, String country, String variant) {
        return 0;
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
    }


    @Override
    protected void onSynthesizeText(SynthesisRequest request, SynthesisCallback callback) {
        String text = request.getCharSequenceText().toString();
        mController.stop();

        // start参数值来自小爱语音引擎
        callback.start(16000 , AudioFormat.ENCODING_PCM_16BIT, 1);
        mController.speak(text);
        callback.done();

    }


}
