package com.example.demouser.voicefind;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demouser.voicefind.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String SAMPLE = "Setting Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        TextView resultText = (TextView) findViewById(R.id.inputName);
        promptSpeechInput();
    }

    public void promptSpeechInput() {
        Intent data = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        data.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your name!");
        data.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000000);

        try {
            startActivityForResult(data,100);
        }
        catch(ActivityNotFoundException a) {
            Toast.makeText(MainActivity.this, "Sorry, your device doesn't support speech-text recognition", Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int request_code, int result_code, Intent data) {

        super.onActivityResult(request_code, result_code, data);

        switch(request_code) {
            case 100 : if (result_code == RESULT_OK && data != null) {
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                TextView resultText = (TextView) findViewById(R.id.inputName);
                resultText.setText(results.get(0));
            }
                break;
        }
    }


}
