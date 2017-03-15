package com.suribada.androidbook.chap2;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.suribada.androidbook.R;

/**
 * Created by Noh.Jaechun on 2017. 2. 24..
 */
public class TextRotationActivity extends Activity {

	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_and_button);
		text = (TextView) findViewById(R.id.text);
	}
	
	/** 
	 * 기대 결과는?
	 */
	public void onClick(View view) {
		for (int i = 0; i < 5; i++) {
			text.setText("Current Value=" + i);
			SystemClock.sleep(1000);
		}
	}
	
}
