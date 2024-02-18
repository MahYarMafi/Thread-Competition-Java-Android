package com.example.threadcompetition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                Log.i("test",Thread.currentThread().getName() + " Is running");
            }
        };
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(task);
            thread.setName("Thread #" + i);
            thread.start();
        }
    }
}