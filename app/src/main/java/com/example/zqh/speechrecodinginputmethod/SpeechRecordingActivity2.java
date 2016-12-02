package com.example.zqh.speechrecodinginputmethod;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 添加Flag参数
 *
 * @author zqh
 */
public class SpeechRecordingActivity2 extends AppCompatActivity {
    public static final int REQUEST_CODE = 100;
    private Button sub_speech;
    private TextView speech_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_speech_recording);


        sub_speech = (Button) findViewById(R.id.sub_speech);
        speech_text = (TextView) findViewById(R.id.speech_text);

        sub_speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mVoiceAppSearchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//                mVoiceAppSearchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent voiceIntent = new Intent(mVoiceAppSearchIntent);
                voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your quick reply");
                voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                voiceIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
                voiceIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());

                // Add the values that configure forwarding the results
//                voiceIntent.putExtra(RecognizerIntent.EXTRA_RESULTS_PENDINGINTENT, pending);
//                voiceIntent.putExtra(RecognizerIntent.EXTRA_RESULTS_PENDINGINTENT_BUNDLE, queryExtras);
                startActivityForResult(mVoiceAppSearchIntent,REQUEST_CODE);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && data != null
                && data.hasExtra(RecognizerIntent.EXTRA_RESULTS)) {
            ArrayList<String> results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            speech_text.setText(results.get(0));
        }
    }
}
