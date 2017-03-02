package com.suribada.androidbook.chap2;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class InexactDelayActivity extends Activity {

	private static final String TAG = "InexactDelayActivity";

	public void onClick(View view) {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				Log.d("suribada", "200 delay");
			}

		}, 200);

		handler.post(new Runnable() {

			@Override
			public void run() {
				Log.d("suribada", "just");
				SystemClock.sleep(500);
			}

		});
	}

}
