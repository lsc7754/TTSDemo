# TTSDemo
TTS.APK Demo

一个可以被系统设置识别的TTS至少需要包含一个Activity和一个Service。

Activity（TTSActivity）  
需要同时响应两条Action  
１．android.speech.tts.engine.CHECK_TTS_DATA（添加可用的tts语音数据）  
２．android.speech.tts.engine.GET_SAMPLE_TEXT（检查是否有样例文本）  
具体返回数据形式见demo的TTSActivity（参考Google原生TTS和小爱.apk）  

Service（TTSService）
TTS的核心Service继承自TextToSpeechService，
对比了Google原生TTS、小爱语音引擎、讯飞和其他一些开源的tts，onSynthesizeText以外的方法处理都较少，仅仅做一些验证和配置。
作为语音接口的方法为onSynthesizeText，它接收一个SynthesisRequest，在处理完后，根据处理结果手动调用SynthesisCallback进行结果回调。
这个简单的demo可以配合talkback进行语音播放，但没有详细的播放控制逻辑。
实际调用中发现onSynthesizeText会被多个线程调用，存在同步问题。


（可选）可以创建一个Activity（demo中的MainActivity），作为TTSService的设置页面，也可以在其中提供一些介绍信息。
