package com.suribada.androidbook.chap9;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CategoryUpdaterService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
