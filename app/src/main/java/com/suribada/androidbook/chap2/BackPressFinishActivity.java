package com.suribada.androidbook.chap2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.suribada.androidbook.R;

/**
 * Created by Noh.Jaechun on 2017. 2. 24..
 */
public class BackPressFinishActivity extends Activity {

	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_buttons);
	}

	/**
	 * 한번 Back Key를 누르면 정말 종료하겠느냐는 Toast를 띄우고,
	 * 5초 내에 다시 Back Key를 누르면 그때 종료한다.
	 * 5초가 지나면 다시 없었던 일로 한다.
	 *
	 * 이때 5초 내에 한번 눌렀는지 여부가 필요하기 때문에 boolean 필드값을 둔다.
	 */
	private boolean isBackPressedOnce = false;

	@Override
	public void onBackPressed() {
		if (isBackPressedOnce) {
			super.onBackPressed(); // 실제로 finish 호출
		} else {
			Toast.makeText(this, R.string.backpressed_message, Toast.LENGTH_SHORT).show();
			isBackPressedOnce = true; // 5초 내에 다시 Back Key 누른다면 유
			handler.removeCallbacks(timerTask); // 새로 5초를 다시 시작한다.
			handler.postDelayed(timerTask, 5000); // 5초 후에 reset하는 task를 실행한다.
		}
	}

	private final Runnable timerTask = new Runnable() {

		@Override
		public void run() {
			isBackPressedOnce = false; // 5초 내에 Back Key가 반복되지 않으면 변수를 reset한다.
		}

	};
}
