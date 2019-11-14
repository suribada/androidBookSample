package com.suribada.androidbook.chap5;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.suribada.androidbook.R;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_and_three_buttons);
    }

    public void onClickButton1(View view) {
        new AlertDialog.Builder(this)
                .setTitle("타이틀")
                .setMessage("메시지")
                .show();

    }

    public void onClickButton2(View view) {
        new AlertDialog.Builder(getApplicationContext())
                .setTitle("타이틀2")
                .setMessage("메시지2")
                .show();

    }

    public void onClickButton3(View view) {
        new AlertDialog.Builder(getBaseContext())
                .setTitle("타이틀3")
                .setMessage("메시지3")
                .show();

    }
}
