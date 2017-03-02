package com.suribada.androidbook.chap5;

import android.app.Activity;

/**
 * Created by nhn on 15. 12. 23..
 */
public class BaseActivity extends Activity {

	protected MyLocationManager myLocationManager;

	@Override
	protected void onResume() {
		super.onResume();
		myLocationManager = new MyLocationManager();
		myLocationManager.requestUpdate();
	}

	@Override
	protected void onPause() {
		myLocationManager = null;
		super.onPause();
	}
}
