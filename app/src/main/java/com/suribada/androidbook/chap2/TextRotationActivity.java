package com.suribada.androidbook.chap2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.naver.android.sample.R;

public class TextRotationActivity extends Activity {

	private TextView title;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.three_buttons);
		title = (TextView) findViewById(R.id.title);
		imageView = (ImageView) findViewById(R.id.image);
	}
	
	/** 
	 * 기대 결과는?
	 */
	public void onClick(View view) {
		for (int i = 0; i < 5; i++) {
			currentValue.setText("Current Value=" + i);
			SystemClock.sleep(1000);
		}
	}
	
}
