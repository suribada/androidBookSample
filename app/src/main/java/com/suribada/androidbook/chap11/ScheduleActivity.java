package com.suribada.androidbook.chap11;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ScheduleActivity extends Activity {

	private static final String TAG = "ScheduleActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final TextView textView = new TextView(this);
		textView.setText("first run");
		setContentView(textView);
		CalendarManager manager = CalendarManager.getInstance(this);
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "CalenderRelatedActivity onDestroy");
		super.onDestroy();
	}
	
	@Override
	protected void finalize() throws Throwable {
		Log.d(TAG, "CalenderRelatedActivity finalinze");
		super.finalize();
	}
	
}
