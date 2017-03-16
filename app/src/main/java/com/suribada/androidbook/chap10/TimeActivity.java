package com.suribada.androidbook.chap10;

import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.suribada.androidbook.R;

/**
 *  Created by Noh.Jaechun on 2017. 3. 2..
 */
public class TimeActivity extends Activity {

	private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_and_three_buttons);
		title = (TextView) findViewById(R.id.title);
		registerReceiver(receiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
    }

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	public void onClickButton1(View view) {
        long elapsedTime = SystemClock.elapsedRealtime();
        long uptime = SystemClock.uptimeMillis();

        long diff = elapsedTime - uptime;

        String result = "elapsedTime=" + (elapsedTime / 1000)
                + ", uptime=" + (uptime / 1000)
                + ", diff=" + (diff / 1000);

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

	private Handler handler = new Handler();

	private static final int DELAY_TIME = 2 * 60 * 1000;
	private Runnable updateTimeTask = new Runnable() {

		@Override
		public void run() {
			title.setText(new Date().toString());
			handler.postDelayed(this, DELAY_TIME);
		}

	};

	public void onClickButton2(View view) {
		handler.post(updateTimeTask);
	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			long diff = SystemClock.elapsedRealtime() - executedRealtime;
			handler.removeCallbacks(updateTimeTask2);
			Toast.makeText(TimeActivity.this, "diff=" + diff + ", " + DELAY_TIME, Toast.LENGTH_LONG).show();
			handler.postDelayed(updateTimeTask2, diff >= DELAY_TIME ? 0 : DELAY_TIME - diff);
		}

	};

	private long executedRealtime;

	private Runnable updateTimeTask2 = new Runnable() {

		@Override
		public void run() {
			executedRealtime = SystemClock.elapsedRealtime();
			title.setText(new Date().toString());
			handler.postDelayed(this, DELAY_TIME);
		}

	};

	public void onClickButton3(View view) {
		handler.post(updateTimeTask2);
	}

}
