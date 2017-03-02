package com.suribada.androidbook.chap11;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.naver.android.sample.R;

/**
 * Created by nhn on 15. 9. 2..
 */
public class GcProblemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_buttons);
    }

    public void onClickButton1(View view) {
        startActivity(new Intent(this, CalendarRelatedActivity.class));
    }

	public void onClickButton2(View view) {
		Runtime.getRuntime().gc();
	}

	private Bitmap bitmap;

    private int width = 480, height = 720;

    public void onClickButton3(View view) {
        width *= 2;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

	public void onClickButton4(View view) {
		width *= 1.3f;
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Runtime runtime = Runtime.getRuntime();
		long dalvikMax = runtime.maxMemory();
		long dalvikUsed = runtime.totalMemory() - runtime.freeMemory();
		Log.d("suribada", "davik max=" + dalvikMax + ", used=" + dalvikUsed);
	}


}