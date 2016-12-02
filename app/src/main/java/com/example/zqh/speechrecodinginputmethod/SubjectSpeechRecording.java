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
 * 最简单的语言转文字跳转，不附带任何内容。
 */
public class SubjectSpeechRecording extends AppCompatActivity {

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
                speechRecordingToText();
            }
        });
    }

    private void speechRecordingToText() {
        {

            Intent intent = new Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
//                    "Speak your quick reply");
            startActivityForResult(intent, REQUEST_CODE);
        }
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
