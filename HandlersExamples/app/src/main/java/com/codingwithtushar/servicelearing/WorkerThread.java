package com.codingwithtushar.servicelearing;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;

public class WorkerThread extends Thread {
    private static final String TAG = "WorkerThread";

    boolean isThreadNeedToRun = true;
    ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

    WorkerThread() {
        start();
    }

    @Override
    public void run() {
        while (isThreadNeedToRun) {
            Runnable runnable = queue.poll();
            if (runnable != null) {
                Log.d(TAG, "Perform infinite operation in thread");
                runnable.run();
            }
        }
    }

    public void addTask(Runnable runnable) {
        queue.add(runnable);
    }

    public void stopThread() {
        isThreadNeedToRun = false;
    }
}
