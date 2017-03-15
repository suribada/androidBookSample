package com.suribada.androidbook.chap5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.suribada.androidbook.R;

/**
 * Created by Noh.Jaechun on 2016. 10. 10..
 */
public class SchemeTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.four_buttons);
	}

	public void onClickButton1(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClass(this, FrontControllerActivity.class);
		startActivity(intent);
	}

	public void onClickButton2(View view) {
		Uri uri = Uri.parse("doc://microsoft/0000");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	public void onClickButton3(View view) {
		Uri uri = Uri.parse("xls://microsoft/0001");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	public void onClickButton4(View view) {
		Uri uri = Uri.parse("ppt://microsoft/0002");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

}
