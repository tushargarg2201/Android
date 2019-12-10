package com.codingwithtusharjobintentservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.JobIntentService;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyReceiver.Receiver{

    TextView textView;
    EditText editText;
    Button button;
    public MyReceiver myReceiver;
    Handler handler;
    MyThreadHandler myThreadHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.intent_service_button);

        myReceiver = new MyReceiver(new Handler());
        myReceiver.setReceiver(MainActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = editText.getText().toString();

                Intent intent = new Intent(MainActivity.this, ExampleJobIntentService.class);
                intent.putExtra("inputstring", inputString);
                intent.putExtra("myreceiver", myReceiver);

                ExampleJobIntentService.enqueueWork(MainActivity.this, intent);
            }
        });

    }

    @Override
    public void updateUI(int resultCode, Bundle resultData) {
        Log.d("Tushar", "updateUI: called");
        String data = resultData.getString("data");
        textView.setText(data);
    }
}
