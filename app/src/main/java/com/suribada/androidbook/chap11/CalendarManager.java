package com.suribada.androidbook.chap11;

import android.content.Context;
import android.util.Log;

import com.suribada.androidbook.R;

/**
 *  Created by Noh.Jaechun on 2017. 3. 2..
 */
public class CalendarManager {

	private static final String TAG = "CalendarManager";

	private static final Object lock = new Object();
	private static CalendarManager instance;
	
	public static CalendarManager getInstance(Context context) {
		synchronized (lock) {
			if (instance == null) {
				Log.d(TAG, "instance is null");
				//instance = new CalendarManager(context);
				instance = new CalendarManager(context.getApplicationContext());
			}
			return instance;
		}
	}
	
	private Context context;
	
	private CalendarManager(Context context) {
		this.context = context;
	}
	
	public String getText() {
		return context.getString(R.string.hello_world);
	}
	
	@Override
	protected void finalize() throws Throwable {
		Log.d(TAG, "CalendarManager finalinze");
		super.finalize();
	}
}
