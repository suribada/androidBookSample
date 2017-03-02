package com.suribada.androidbook.chap9;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.naver.android.sample.R;

/**
 * Created by nhn on 15. 8. 26..
 */
public class PreferenceMultiProcessActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_buttons);
    }

    public void onClickButton1(View view) {
       startActivity(new Intent(this, PreferenceAnotherProcessActivity.class));
    }

    public void onClickButton2(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefernce", Context.MODE_MULTI_PROCESS);
        int value = sharedPreferences.getInt("randomValue", 0);
        Toast.makeText(this, "read value=" + value, Toast.LENGTH_LONG).show();
    }

}
