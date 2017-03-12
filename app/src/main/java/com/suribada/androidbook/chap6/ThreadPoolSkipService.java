package com.suribada.androidbook.chap6;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class ThreadPoolSkipService extends Service {

	private static final String TAG = "SleepThreadService";

	private static final long SLEEP_TIME = 10000;

	private ThreadPoolExecutor exec = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
		new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.DiscardPolicy());

	@Override
	public void onCreate() {
		Log.d(TAG, "Service onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		exec.submit(new Runnable() {

			@Override
			public void run() {
				Log.d(TAG, "Thread start");
				SystemClock.sleep(SLEEP_TIME);
				Log.d(TAG, "10 seconds after");
				SystemClock.sleep(SLEEP_TIME);
				Log.d(TAG, "20 seconds after");
				SystemClock.sleep(SLEEP_TIME);
				Log.d(TAG, "30 seconds after");
				stopSelf();
			}
		});
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
