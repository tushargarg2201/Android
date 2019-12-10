package com.codingwithtushar.servicesexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyReceiver.Receiver{

    TextView textview;
    Button startService;
    Button stopService;

    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.text);
        startService = findViewById(R.id.start_service);
        stopService = findViewById(R.id.stop_service);
        myReceiver = new MyReceiver(new Handler());
        myReceiver.setReceiver(this);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SampleService.class);
                intent.putExtra(Constants.RECEIVER_ACTION, myReceiver);
                startService(intent);

            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SampleService.class);
                stopService(intent);
            }
        });

    }

    @Override
    public void updateUI(int resultCode, Bundle resultData) {
        int data = resultData.getInt(Constants.data);
        String finalData = String.valueOf(data);
        Log.d(Constants.TAG, "FinalData is --->" + finalData);
        textview.setText(finalData);
    }
}
