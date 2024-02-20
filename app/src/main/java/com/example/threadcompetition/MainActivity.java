package com.example.threadcompetition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar prgThread1, prgThread2, prgThread3, prgThread4;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prgThread1 = findViewById(R.id.prgThread1);
        prgThread2 = findViewById(R.id.prgThread2);
        prgThread3 = findViewById(R.id.prgThread3);
        prgThread4 = findViewById(R.id.prgThread4);

        ProgressBar[] progressArrays = new ProgressBar[4];
        progressArrays[0] = prgThread1;
        progressArrays[1] = prgThread2;
        progressArrays[2] = prgThread3;
        progressArrays[3] = prgThread4;

        handler = new Handler();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                int threadIndex = Integer.parseInt(thread.getName());
                for (int i = 0; i < 10000; i++) {
                    int percent = i / 1000;
                    int currentPercent = progressArrays[threadIndex].getProgress();
                    if (percent != currentPercent) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressArrays[threadIndex].setProgress(percent);

                            }
                        });
                    }
                }
            }
        };
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(task);
            thread.setName("" + i);
            thread.start();
        }
    }
}