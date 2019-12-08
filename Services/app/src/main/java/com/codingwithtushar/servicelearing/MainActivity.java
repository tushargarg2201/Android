package com.codingwithtushar.servicelearing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  WorkerThread workerThread;
    private TextView textView;

    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String string = (String) message.obj;

            textView.setText(string);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        startTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        workerThread.stopThread();
    }

    private void startTask() {
        workerThread = new WorkerThread();
        workerThread.addTask(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message message = Message.obtain();
                    message.obj = "Task1 completed";
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        workerThread.addTask(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    Message message = Message.obtain();
                    message.obj = "Task2 completed";
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        workerThread.addTask(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message message = Message.obtain();
                    message.obj = "Task3 completed";
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
