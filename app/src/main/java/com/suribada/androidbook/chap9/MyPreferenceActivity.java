package com.suribada.androidbook.chap9;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.naver.android.sample.R;

/**
 *  Created by Noh.Jaechun on 2017. 3. 2..
 */
public class MyPreferenceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.four_buttons);
	}
	
	public void onClickButton1(View view) {
		/* 
		 * getPreferences는 Activity에만 있는 메소드로, 내부적으로 또 다시 getSharedPreferences를 호출하고 파일명에는 Activity의 클래스명이 전달된다. 
		 * 결과 파일명은 MyPreferenceActivity.xml이 된다. 
		 */
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);  // Context.MODE_PRIVATE 자리에 실제 값인 0을 넣기도 한다.
		SharedPreferences.Editor editor = sharedPreferences.edit(); // 쓰기 할 때는 Editor 객체를 구한다.
		editor.putBoolean("autoSync", true);
		/* 
		 * commit 메소드를 쓰기도 하지만, commit 메소드는 파일에 쓸 때까지 대기하는 메소드이다.(synchronous)
		 * 반면에 apply는 메모리에만 먼저 반영하고, asynchronous로 동작한다.
		 */
		editor.apply(); 
		
		Toast.makeText(this, "autoSync=" + sharedPreferences.getBoolean("autoSync", false), Toast.LENGTH_LONG).show();
	}
	
	/**
	 * 디폴트 값 가져오는지 확인
	 */
	public void onClickButton2(View view) {
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		String address = sharedPreferences.getString("address", "종로구 종로 1가");
		Toast.makeText(this, "address=" + address, Toast.LENGTH_LONG).show();
	}
	
	public void onClickButton3(View view) {
		SharedPreferences sharedPreferences = getSharedPreferences("MyPrefernce", 0); 
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("address", "성남시 분당구 정자동 그린팩토리");
		editor.apply();
	}
	
	/**
	 * 위에서 저장한 값을 제대로 가져오는지 확
	 */
	public void onClickButton4(View view) {
		SharedPreferences sharedPreferences = getSharedPreferences("MyPrefernce", 0);
		String address = sharedPreferences.getString("address", "종로구 종로 1가");
		Toast.makeText(this, "address=" + address, Toast.LENGTH_LONG).show();
	}

}
