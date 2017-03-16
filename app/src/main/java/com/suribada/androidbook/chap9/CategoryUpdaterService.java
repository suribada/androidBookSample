package com.suribada.androidbook.chap9;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CategoryUpdaterService extends Service {
	private static final String TAG = "CategoryUpdaterService";

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "CategoryUpdaterService onStartCommand");
		return START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
