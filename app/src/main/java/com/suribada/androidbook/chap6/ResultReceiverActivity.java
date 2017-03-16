package com.suribada.androidbook.chap6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Noh.Jaechun on 15. 12. 22..
 */
public class ResultReceiverActivity extends Activity {
	
	private View syncLayout, progressBar;
	private TextView syncMessage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_sync);
		syncLayout = findViewById(R.id.sync_layout); // Visibility만 변경되므로, LinearLayout으로 Casting 필요하지 않다.
		progressBar = findViewById(R.id.progress_bar);
		syncMessage = (TextView) findViewById(R.id.sync_message);
	}
	
	public void onClickSync(View view) {
		syncLayout.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.VISIBLE); // Progress Bar를 보인다.
		syncMessage.setText(R.string.sync_progress); // 동기화 진행중이라는 메시지를 보인다.

		//Intent intent = new Intent(this, SyncService.class);
		Intent intent = new Intent("com.naver.android.sample.SYNC_SERVICE");
		intent.putExtra(Constant.EXTRA_RECEIVER, resultReceiver);
		startService(intent);
	}

	private Handler handler = new Handler();

	private ResultReceiver resultReceiver = new ResultReceiver(handler) {

		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			if (resultCode == Constant.SYNC_COMPLETED) {
				progressBar.setVisibility(View.GONE); // Progress Bar를 보이지 않게 한다.
				syncMessage.setText(R.string.sync_ended); // 동기화 완료됐다고 메시지를 보인다.
			}
		}

	};

}
