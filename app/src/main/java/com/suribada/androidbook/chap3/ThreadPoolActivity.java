package com.suribada.androidbook.chap3;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.suribada.androidbook.R;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Noh.Jaechun on 2017. 9. 26..
 */

public class ThreadPoolActivity extends Activity {

    final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 50, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(15));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_buttons);
    }

    final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);

    public void onClickButton1(View view) {
        for (int i = 0; i < 20; i++) {
            Log.d("ThreadPool", "current=" + executor.toString());
            executor.submit(new TaskRunnable(i));
        }
    }

    private class TaskRunnable implements Runnable {

        private int value;

        TaskRunnable(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            Log.d("ThreadPool", Thread.currentThread().getName() + " executed " + value
                    + ":" + executor.toString());
            SystemClock.sleep(10000);
        }

    }

    public void onClickButton2(View view) {
        for (int i = 0; i < 30; i++) {
            scheduledThreadPoolExecutor.submit(new TaskScheduledRunnable(i));
        }
    }

    private class TaskScheduledRunnable implements Runnable {

        private int value;

        TaskScheduledRunnable(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            Log.d("ThreadPool", Thread.currentThread().getName() + " executed " + value
                    + ":" + scheduledThreadPoolExecutor.toString());
            SystemClock.sleep(10000);
        }

    }

    @Override
    protected void onDestroy() {
        Log.d("ThreadPool", "onDestroy=" + executor.toString());
        super.onDestroy();
    }
}
