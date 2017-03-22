package com.suribada.androidbook.chap11;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.suribada.androidbook.R;

/**
 *  디바이스 회전하면서 테스트 필요하다.
 *
 *  Created by Noh.Jaechun on 2017. 3. 2..
 */
public class FragmentReplaceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_replace);
    }

    public void onClickButton1(View view) {
        ContentFragment content = ContentFragment.newInstance(7, 110);
        getFragmentManager().beginTransaction().replace(R.id.content, content).commit();
    }

    public void onClickButton2(View view) {
        Content2Fragment content = new Content2Fragment();
        content.set(8, 115);
        getFragmentManager().beginTransaction().replace(R.id.content, content).commit();
    }

}
