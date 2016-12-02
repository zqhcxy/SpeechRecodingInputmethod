package com.example.zqh.speechrecodinginputmethod;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 语音转文字，后台监听
 */
public class SpeechRecordingTest5Activity extends AppCompatActivity {

    private static final String TAG="speechActivity";
    public static final int REQUEST_CODE = 101;
    private Button sub_speech;
    private TextView speech_text;
    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_speech_recording);
        sub_speech = (Button) findViewById(R.id.sub_speech);
        speech_text = (TextView) findViewById(R.id.speech_text);

        sub_speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech_text.setText("");
                speechRecordingToText(savedInstanceState);
            }
        });

        initSpeech();
    }

    private void initSpeech() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(mRecognitionListener);

    }


    /**
     * 开始语音转文字
     *
     */
    private void speechRecordingToText(Bundle bundle) {


//            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
//
//            // Specify the recognition language if provided.
//            if (bundle != null) {
//                String languageLocale = bundle.getString(RecognizerIntent.EXTRA_LANGUAGE);
//                if (languageLocale != null) {
//                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageLocale);
//                }
//            }

        Intent recognitionIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognitionIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        recognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        speechRecognizer.startListening(recognitionIntent);
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

    /**
     * 语音结果
     *
     * @param results
     */
    private void speechResult(Bundle results) {
        ArrayList<String> result = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (result != null && result.size() > 0) {
            String bestResult = result.get(0);
            speech_text.append("you :[ "+bestResult+" ]"+"\n");
        }
    }

    /**
     * 语音监听
     */
    private RecognitionListener mRecognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.i(TAG,"onReadyForSpeech");
            speech_text.setText("Start to Recording:"+"\n");
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.i(TAG,"onBeginningOfSpeech");
            speech_text.append("Beginning Of Speech:..."+"\n");
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            Log.i(TAG,"onRmsChanged ："+rmsdB);
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            Log.i(TAG,"onBufferReceived");
        }

        @Override
        public void onEndOfSpeech() {
            Log.i(TAG,"onEndOfSpeech");
            speech_text.append("End Of Speech:..."+"\n");
        }

        @Override
        public void onError(int error) {
            Log.i(TAG,"onError :"+error);
            speech_text.setText("onError:"+error+"\n"+" Solution: May need to manually open permissions.");
            if(error==9){
                speech_text.setText("Solution: May need to manually open permissions.");
            }else if(error==7){
                speech_text.setText("Speack again.");
            }
        }

        @Override
        public void onResults(Bundle results) {
            Log.i(TAG,"onResults");
            speechResult(results);
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            Log.i(TAG,"onPartialResults");
//            speechResult(partialResults);
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            Log.i(TAG,"onEvent");
        }
    };


    @Override
    protected void onDestroy() {
        speechRecognizer.destroy();
        super.onDestroy();
    }
}
