package com.codingwithtusharjobintentservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ExampleJobIntentService extends JobIntentService {


    private static final String handler_action = "handler_action";

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, ExampleJobIntentService.class, 1000, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        String inputString = intent.getStringExtra("inputstring");
        ResultReceiver receiver = intent.getParcelableExtra("myreceiver");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                Bundle bundle = new Bundle();

                String string = inputString + " " + i;
                bundle.putString("data", string);
                receiver.send(100, bundle);
                Log.d("Tushar", "Input String " + inputString + " " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
