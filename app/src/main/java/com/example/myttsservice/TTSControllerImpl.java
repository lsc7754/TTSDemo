package com.example.myttsservice;

import android.content.Intent;
import android.speech.tts.TextToSpeechService;

public class TTSControllerImpl implements TTSController {
    private final String ACTION_START_SPEAKING = "com.xiaomi.mitv.voice.STARTSPEAKING";
    private final String ACTION_STOP_SPEAKING = "com.xiaomi.mitv.voice.STOPSPEAKING";

    private final String VOICE_PACKAGE_NAME = "com.xiaomi.voicecontrol";
    private final String VOICE_CLASS_NAME = "com.xiaomi.voicecontrol.VoiceControlService";

    private static volatile TTSControllerImpl INSTANCE;
    private TextToSpeechService mService;

    public static TTSController getInstance(TextToSpeechService service) {
        if (INSTANCE == null) {
            synchronized (TTSControllerImpl.class) {
                if(INSTANCE == null) {
                    INSTANCE = new TTSControllerImpl(service);
                }
            }
        }
        return INSTANCE;
    }

    private TTSControllerImpl(TextToSpeechService service) {
        mService = service;
    }

    @Override
    public boolean speak(String extra) {
        try {
            Intent intent = new Intent(ACTION_START_SPEAKING);
            intent.setClassName(VOICE_PACKAGE_NAME, VOICE_CLASS_NAME);
            intent.putExtra("toSpeak",extra);
            mService.startService(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean stop() {
        try {
            Intent intent = new Intent(ACTION_STOP_SPEAKING);
            intent.setClassName(VOICE_PACKAGE_NAME, VOICE_CLASS_NAME);
            mService.startService(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateSpeed(int speed) {

    }
}
