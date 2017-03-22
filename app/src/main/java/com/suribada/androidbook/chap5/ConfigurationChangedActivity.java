package com.suribada.androidbook.chap5;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.suribada.androidbook.R;

/**
 * Created by Noh.Jaechun on 16. 1. 18..
 */
public class ConfigurationChangedActivity extends Activity {

	private static final String TAG = "ConfigurationChanged";

	private View left;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_list);
		left = findViewById(R.id.left);
		Log.d(TAG, "orientaton=" + getResources().getConfiguration().orientation + ", "+ getRequestedOrientation());
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Log.d(TAG, "onConfigurationChanged=" + newConfig.orientation);
		super.onConfigurationChanged(newConfig);
		ViewGroup.LayoutParams lp = left.getLayoutParams();
		lp.width = getResources().getDimensionPixelSize(R.dimen.left_width);
		left.setLayoutParams(lp);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Log.d(TAG, "orientaton1=" + getResources().getConfiguration().orientation);
		if (hasFocus) {
			//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(TAG, "onRestoreInstanceState");
	}
}
