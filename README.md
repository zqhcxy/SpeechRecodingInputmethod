# SpeechRecodingInputmethod
语音转文字的简单调用
=====
## 简单的实现调用系统的语音转文字，但是有的手机上没有google的语音框架，国内的定制系统一般都没有。
##  这里分为三种实现：
### 第一种最简单的调用不附带任何参数的调用：
```java
            Intent intent = new Intent(
            RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
            startActivityForResult(intent, REQUEST_CODE);
```
### 附带参数的调用：
参数内容可参考[RecognizerIntent](https://developer.android.com/reference/android/speech/RecognizerIntent.html)
```java
                Intent mVoiceAppSearchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                Intent voiceIntent = new Intent(mVoiceAppSearchIntent);
                voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your quick reply");//一些提示语
                voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");//语言：这里设置为英语
                voiceIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);//返回的内容最大个数，一般取第一个最为准确
                voiceIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());

                // Add the values that configure forwarding the results
//                voiceIntent.putExtra(RecognizerIntent.EXTRA_RESULTS_PENDINGINTENT, pending);
//                voiceIntent.putExtra(RecognizerIntent.EXTRA_RESULTS_PENDINGINTENT_BUNDLE, queryExtras);
                startActivityForResult(mVoiceAppSearchIntent,REQUEST_CODE);
```
### 没有界面的语音转文字：
 通过[SpeechRcognizer](https://developer.android.com/reference/android/speech/SpeechRecognizer.html)来实现的
```java
       private void initSpeech() {
           speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
           speechRecognizer.setRecognitionListener(mRecognitionListener);

        }
    ...
        Intent recognitionIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognitionIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        recognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        speechRecognizer.startListening(recognitionIntent);
```
