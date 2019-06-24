package com.example.myttsservice;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

public class TTSActivity extends AppCompatActivity {
    private static final String TAG = "TTSActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if ("android.speech.tts.engine.GET_SAMPLE_TEXT".equals(intent.getAction())) {
            String language = intent.getStringExtra("language");
            String country = intent.getStringExtra("country");
            String variant = intent.getStringExtra("variant");
            if (language == null || !isSimpleChinese(language, country, variant)) {
                Intent intent2 = new Intent();
                intent2.putExtra("sampleText", "This is an example of speech synthesis");
                setResult(0, intent2);
            } else {
                Intent intent3 = new Intent();
                intent3.putExtra("sampleText", "这是语音合成示例");
                setResult(0, intent3);
            }
        } else if ("android.speech.tts.engine.CHECK_TTS_DATA".equals(intent.getAction())) {
            Intent intent4 = new Intent();
            ArrayList<String>arrayList = new ArrayList<>();
            arrayList.add(Locale.CHINESE.getISO3Language() + "-" + Locale.CHINESE.getISO3Country());
            arrayList.add(Locale.US.getISO3Language() + "-" + Locale.US.getISO3Country());
            arrayList.add(Locale.UK.getISO3Language() + "-" + Locale.UK.getISO3Country());
            arrayList.add(Locale.ENGLISH.getISO3Language() + "-" + Locale.ENGLISH.getISO3Country());
            arrayList.add(Locale.SIMPLIFIED_CHINESE.getISO3Language() + "-" + Locale.SIMPLIFIED_CHINESE.getISO3Country());
            intent4.putExtra("availableVoices", arrayList);
            setResult(1, intent4);
        }
        finish();
    }


    public static boolean isSimpleChinese(String language, String country, String variant) {
        if (!Locale.CHINESE.getISO3Country().equals(country)
                || !Locale.CHINESE.getISO3Language().equals(language)
                || !Locale.CHINESE.getVariant().equals(variant)) {
            return Locale.SIMPLIFIED_CHINESE.getISO3Country().equals(country)
                    && Locale.SIMPLIFIED_CHINESE.getISO3Language().equals(language)
                    && Locale.SIMPLIFIED_CHINESE.getVariant().equals(variant);
        }
        return true;
    }



}
