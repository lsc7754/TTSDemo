package com.example.myttsservice;

import android.content.Context;
import android.speech.tts.SynthesisCallback;
import android.speech.tts.SynthesisRequest;

public interface TTSController {
    boolean speak(String extra);

    boolean stop();

    void updateSpeed(int speed);
}
