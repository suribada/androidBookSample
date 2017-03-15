package com.suribada.androidbook.chap4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.suribada.androidbook.R;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class ContextActivity extends Activity {

	private static final String TAG = "ContextActivity";

	private TextView statusView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_and_button);
		statusView = (TextView) findViewById(R.id.status_view);
		Log.d(TAG, "first=" + (statusView.getContext() == this));
		Log.d(TAG, "second=" + (statusView.getContext() == getBaseContext()));
		Log.d(TAG, "third=" + (statusView.getContext() == getApplicationContext()));
		Log.d(TAG, "4th=" +(statusView.getContext() == getApplication()));

		Log.d(TAG, "5th=" + (getBaseContext() instanceof Activity)); // false가 나온다. 캐스팅하면 안 된다.
		Log.d(TAG, "6th" + (getApplicationContext() == getApplication()));
	}

}
