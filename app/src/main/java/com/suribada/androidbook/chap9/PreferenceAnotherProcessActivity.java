package com.suribada.androidbook.chap9;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.naver.android.sample.R;

/**
 * Created by Noh.Jaechun on 15. 8. 26..
 */
public class PreferenceAnotherProcessActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_button);
    }

    public void onClickButton1(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefernce", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int value = new Random().nextInt(1000);
        editor.putInt("randomValue", value);
        editor.apply();
        Toast.makeText(this, "write value=" + value, Toast.LENGTH_LONG).show();
    }

}
