package com.gun21.speechtotext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText txtSpeech;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSpeech = (EditText) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
//        promptSpeechInput();
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });
    }

    private void promptSpeechInput() {
//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, "2000");
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "id");
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id");
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));

//        speechRecognizer.setRecognitionListener(new RecognitionListener(){
//            @Override
//            public void onReadyForSpeech(Bundle bundle) {
//
//            }
//
//            @Override
//            public void onBeginningOfSpeech() {
//                txtSpeech.setText("");
//                txtSpeech.setHint("Mendengarkan....");
//            }
//
//            @Override
//            public void onRmsChanged(float v) {
//
//            }
//
//            @Override
//            public void onBufferReceived(byte[] bytes) {
//
//            }
//
//            @Override
//            public void onEndOfSpeech() {
//
//            }
//
//            @Override
//            public void onError(int i) {
//
//            }
//
//            @Override
//            public void onResults(Bundle bundle) {
//                btnSpeak.setImageResource(R.drawable.ic_baseline_mic_off);
//                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                if("hapus".equals(data.get(0))){
//                    txtSpeech.setText("");
//                } else {
//                    txtSpeech.setText(data.get(0));
//                }
//            }
//
//            @Override
//            public void onPartialResults(Bundle bundle) {
//
//            }
//
//            @Override
//            public void onEvent(int i, Bundle bundle) {
//
//            }
//        });
//
//        btnSpeak.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    speechRecognizer.stopListening();
//                }
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
//                    btnSpeak.setImageResource(R.drawable.ic_baseline_mic_24);
//                    speechRecognizer.startListening(speechRecognizerIntent);
//                }
//                return false;
//            }
//        });

        try {
            startActivityForResult(speechRecognizerIntent, REQ_CODE_SPEECH_INPUT);
//            startActivityForResult(speechRecognizerIntent, RecordAudioRequestCode);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
    }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT: {
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> result =
                            data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if("hapus".equals(result.get(0))){
                        txtSpeech.setText("");
                    } else if ("satu".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("satu", "1")
                                .trim().replaceAll(" ", ""));
                    } else if ("dua".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("dua", "2")
                                .trim().replaceAll(" ", ""));
                    } else if ("tiga".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("tiga", "3")
                                .trim().replaceAll(" ", ""));
                    } else if ("empat".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("empat", "4")
                                .trim().replaceAll(" ", ""));
                    } else if ("lima".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("lima", "5")
                                .trim().replaceAll(" ", ""));
                    } else if ("enam".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("enam", "6")
                                .trim().replaceAll(" ", ""));
                    } else if ("tujuh".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("tujuh", "7")
                                .trim().replaceAll(" ", ""));
                    } else if ("delapan".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("delapan", "8")
                                .trim().replaceAll(" ", ""));
                    } else if ("sembilan".contains(result.get(0))){
                        txtSpeech.setText(result.get(0).replace("sembilan", "9")
                                .trim().replaceAll(" ", ""));
                    }else{
                        txtSpeech.setText(result.get(0).trim().replaceAll(" ", ""));
                    }
                }
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
    super.onDestroy();
    speechRecognizer.destroy();
}


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
        if (requestCode == REQ_CODE_SPEECH_INPUT && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }
}