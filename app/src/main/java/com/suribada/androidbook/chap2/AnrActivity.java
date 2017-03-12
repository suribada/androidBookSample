package com.suribada.androidbook.chap2;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class AnrActivity extends Activity {

	final static String TAG = "AnrActivity";

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) { // (1)
			Log.d(TAG, "handleMessage");
			SystemClock.sleep(2000);
		};

	};

	public void onClickSendMessages(View v) { // (2)
		for (int i = 0; i < 5; i++) {
			handler.sendEmptyMessage(0);
		}
	}

}
