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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		statusView = (TextView) findViewById(R.id.status_view);
		Log.d("suribada", (statusView.getContext() == this));
		Log.d("suribada", (statusView.getContext() == getBaseContext()));
		Log.d("suribada", (statusView.getContext() ==
			getApplicationContext()));
	}

}
