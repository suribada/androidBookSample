package com.suribada.androidbook.chap2;

import java.util.Date;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;

/**
 * Created by Noh.Jaechun on 2017. 2. 24..
 */
public class HandlerRepeatActivity extends Activity {

	private static final String TAG = "HandlerRepeatActivity";

	private Handler handler = new Handler();

	private static final int DELAY_TIME = 2000;
	private Runnable updateTimeTask = new Runnable() {

		@Override
		public void run() {
			Log.d(TAG, "currentDate=" + new Date()); // 현재 시간을 로그에 출력한다.
			handler.postDelayed(this, DELAY_TIME); // Runnable 자기 자신을 MesssageQueue에 넣는다.
		}

	};

	public void onClickButton(View view) {
		handler.post(updateTimeTask);
	}
}
