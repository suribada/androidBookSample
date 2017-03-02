package com.suribada.androidbook.chap6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class FlagSkipService extends Service {

	private static final String TAG = "FlagSkipService";

	private static final long SLEEP_TIME = 10000;

	private ExecutorService exec = Executors.newSingleThreadExecutor();

	@Override
	public void onCreate() {
		Log.d(TAG, "Service onCreate");
	}

	private boolean isRunning = false;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (isRunning) {
			Log.d(TAG, "skip");
			return START_NOT_STICKY;
		}
		isRunning = true;
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
	public void onDestroy() {
		isRunning = false;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}