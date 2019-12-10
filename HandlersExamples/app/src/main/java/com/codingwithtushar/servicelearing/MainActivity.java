package com.codingwithtushar.servicelearing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  WorkerThread workerThread;
    private  SampleHandlerClass sampleHandlerClass;
    private TextView textView1;
    private TextView textView2;

    private Handler handler1 = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String string = (String) message.obj;
            textView1.setText(string);
        }
    };

    private Handler handler2 = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String string = (String) message.obj;
            textView2.setText(string);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        workerThread = new WorkerThread();
        sampleHandlerClass = new SampleHandlerClass();

        startTask();
        startHandlerTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        workerThread.stopThread();
    }

    private void startHandlerTask() {
        sampleHandlerClass.addTask(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message message = Message.obtain();
                    message.obj = "Task1 completed";
                    handler2.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        sampleHandlerClass.addTask(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    Message message = Message.obtain();
                    message.obj = "Task2 completed";
                    handler2.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        sampleHandlerClass.addTask(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message message = Message.obtain();
                    message.obj = "Task3 completed";
                    handler2.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void startTask() {
        workerThread.addTask(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message message = Message.obtain();
                    message.obj = "Task1 completed";
                    handler1.sendMessage(message);
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
                    handler1.sendMessage(message);
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
                    handler1.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
