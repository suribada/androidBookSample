package com.suribada.androidbook.chap8;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

import com.naver.android.sample.R;

/**
 * Created by nhn on 16. 3. 2..
 */
public class BatteryCheckActivity extends Activity {

	private final static String TAG = "BatteryCheckActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_buttons);
	}

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		filter.addAction(Intent.ACTION_DEVICE_STORAGE_LOW);
		filter.addAction(Intent.ACTION_DOCK_EVENT);
		registerReceiver(receiver, filter);
		Log.d(TAG, "onResume" + System.currentTimeMillis());
	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d(TAG, "onReceive" + System.currentTimeMillis());
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
				Bundle bundle = intent.getExtras();
				int level = bundle.getInt(BatteryManager.EXTRA_LEVEL);
				String temparature = bundle.getString(BatteryManager.EXTRA_TEMPERATURE);
				Log.d(TAG, "battery changed=" + intent.toString() + ", " + intent.getExtras() + ", " + level + ", " + temparature);
			} else if (action.equals(Intent.ACTION_DEVICE_STORAGE_LOW)) {
				Log.d(TAG, "storage low=" + intent.toString());
			} else if (action.equals(Intent.ACTION_DOCK_EVENT)) {
				Log.d(TAG, "dock event=" + intent.toString());
			}
		}

	};

	@Override
	protected void onPause() {
		unregisterReceiver(receiver);
		super.onPause();
	}
}
