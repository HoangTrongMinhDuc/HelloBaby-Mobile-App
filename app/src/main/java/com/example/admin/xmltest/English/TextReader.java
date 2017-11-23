package com.example.admin.xmltest.English;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by USER on 11/23/2017.
 */

class TextReader {
    public static TextReader sInstance = null;

    private TextToSpeech mTextToSpeech;
    private static Context mContext;

    private TextReader(){
        if(mContext == null){
            throw new RuntimeException("Please call init() method first");
        }
        mTextToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    mTextToSpeech.setLanguage(Locale.US);
                }
            }
        });
    }

    public static void init(Context context){
        mContext = context;
    }

    public static TextReader getInstance(){
        if(sInstance == null){
            sInstance = new TextReader();
        }
        return sInstance;
    }

    public void speak(String str){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(str);
        } else {
            ttsUnder20(str);
        }
    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String id =this.hashCode() + "";
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, id);
    }
}
