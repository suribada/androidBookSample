package com.suribada.androidbook.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.os.AsyncTaskCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class CountDownLatchActivity extends Activity {

	private static final String TAG = "CountDownLatchActivity";

	private ArrayList<String> composedList = new ArrayList<>();

	private CountDownLatch latch = new CountDownLatch(1);

	private class AsyncTaskA extends AsyncTask<Void, Void, List<String>> {

		@Override
		protected List<String> doInBackground(Void... params) {
			SystemClock.sleep(3000);
			return Arrays.asList("spring", "summer", "fall", "winter");
		}

		@Override
		protected void onPostExecute(List<String> result) {
			try {
				composedList.addAll(result);
				title.setText(TextUtils.join(", ", composedList));
			} catch (Exception e) {
				Toast.makeText(CountDownLatchActivity.this, "Error=" + e.getMessage(), Toast.LENGTH_LONG).show();
			} finally {
				latch.countDown();
			}
		}
	}

	private class AsyncTaskB extends AsyncTask<Void, Void, List<String>> {

		@Override
		protected List<String> doInBackground(Void... params) {
			try {
				SystemClock.sleep(1000);
				//SystemClock.sleep(5000);
				return Arrays.asList("east", "south", "west", "north");
			} catch (Exception e) {
				Log.d(TAG, "exception = " + e.getMessage());
				return null;
			} finally {
				try {
					latch.await();
				} catch (InterruptedException e) {
				}
			}
		}

		@Override
		protected void onPostExecute(List<String> result) {
			if (result != null) {
				composedList.addAll(result);
				title.setText(TextUtils.join(", ", composedList));
			}
		}

	}

	public void onClickButton2(View view) {
		composedList.clear();
		title.setText(null);
		AsyncTaskCompat.executeParallel(new AsyncTaskA());
		AsyncTaskCompat.executeParallel(new AsyncTaskB());
	}
}
