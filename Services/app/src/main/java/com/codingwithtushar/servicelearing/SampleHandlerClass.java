package com.codingwithtushar.servicelearing;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class SampleHandlerClass extends HandlerThread {

    private static final String TAG = "SampleHandlerClass";
    private Handler handler;
    private Looper looper;

    public SampleHandlerClass() {
        super(TAG);
        looper = Looper.myLooper();
        handler = new Handler(looper);
    }

    public void addTask(Runnable task) {
        handler.post(task);
    }
}
