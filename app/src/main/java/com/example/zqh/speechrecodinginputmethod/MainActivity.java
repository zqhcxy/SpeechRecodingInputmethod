package com.example.zqh.speechrecodinginputmethod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 语音转文字测试。
 *
 * @author zqh
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button speechRecording_1;
    private Button speechRecording_2;
    private Button speechRecording_3;
    private Button speechRecording_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        speechRecording_1 = (Button) findViewById(R.id.speechRecording_1);
        speechRecording_2 = (Button) findViewById(R.id.speechRecording_2);
        speechRecording_3 = (Button) findViewById(R.id.speechRecording_3);
        speechRecording_4 = (Button) findViewById(R.id.speechRecording_4);

        speechRecording_1.setOnClickListener(this);
        speechRecording_2.setOnClickListener(this);
        speechRecording_3.setOnClickListener(this);
        speechRecording_4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.speechRecording_1://Simple
                Intent intent = new Intent(MainActivity.this, SubjectSpeechRecording.class);
                startActivity(intent);
                break;
            case R.id.speechRecording_2://more flag
                Intent intent1 = new Intent(MainActivity.this, SpeechRecordingActivity2.class);
                startActivity(intent1);
                break;
            case R.id.speechRecording_3://searcheview
                Intent intent2 = new Intent(MainActivity.this, SpeechRecordingActivity3.class);
                startActivity(intent2);
                break;
            case R.id.speechRecording_4://background
                Intent intent3 = new Intent(MainActivity.this, SpeechRecordingTest5Activity.class);
                startActivity(intent3);
                break;

        }

    }
}
