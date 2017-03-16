package com.suribada.androidbook.chap11;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *   setter를 통해서 값을 전달하는 방법
 *
 *   Created by Noh.Jaechun on 2017. 3. 2..
 */
public class Content2Fragment extends Fragment {
    
    private static final String TAG = "Content2Fragment";

    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private int left;
    private int right;

    public void set(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_result, container, false);
        TextView result = (TextView) view.findViewById(R.id.result);
        if (savedInstanceState != null) {
            left = savedInstanceState.getInt(LEFT);
            right = savedInstanceState.getInt(RIGHT);
        }
        int sum = left + right;
        result.setText("결과2=" + sum);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(LEFT, left);
        outState.putInt(RIGHT, right);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

}
