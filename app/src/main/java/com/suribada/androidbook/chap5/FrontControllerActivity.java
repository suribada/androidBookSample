package com.suribada.androidbook.chap5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Noh.Jaechun on 2016. 10. 10..
 */
public class FrontControllerActivity extends Activity {

	private static final String WORD_SCHEME = "doc";
	private static final String EXCEL_SCHEME = "xls";
	private static final String POWERPOINT_SCHEME = "ppt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Uri uri = getIntent().getData();
		if (uri == null) {
			Toast.makeText(this, "Uri does not exist!!", Toast.LENGTH_LONG).show();
		} else {
			switch (uri.getScheme()) {
				case WORD_SCHEME:
					startActivity(new Intent(this, WordActivity.class));
					break;
				case EXCEL_SCHEME:
					startActivity(new Intent(this, ExcelActivity.class));
					break;
				case POWERPOINT_SCHEME:
					startActivity(new Intent(this, PowerPointActivity.class));
					break;
			}
		}
		finish();
	}

}
