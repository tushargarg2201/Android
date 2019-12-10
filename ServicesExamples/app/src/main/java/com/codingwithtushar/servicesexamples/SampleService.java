package com.codingwithtushar.servicesexamples;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.Nullable;


public class SampleService extends Service {

    ServiceHandler serviceHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("ServiceStartArguments", Thread.NORM_PRIORITY);
        handlerThread.start();

        serviceHandler = new ServiceHandler(handlerThread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ResultReceiver receiver = intent.getParcelableExtra(Constants.RECEIVER_ACTION);

        Message message = Message.obtain();
        message.arg1 = startId;
        message.obj = receiver;
        serviceHandler.sendMessage(message);
        return START_NOT_STICKY;
    }

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            int startId = message.arg1;
            ResultReceiver receiver = (ResultReceiver) message.obj;

            try {
                for (int i = 1; i < 20 ; i++) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.data, i);
                    Thread.sleep(1000);
                    Log.d(Constants.TAG, "service started in seperate thread " + i + " " + startId);
                    receiver.send(0, bundle);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
