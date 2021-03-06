package com.suribada.androidbook.chap11;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suribada.androidbook.R;

/**
 *  Developer 사이트나 샘플에서 일반적으로 사용하는 방식
 *
 *   Created by Noh.Jaechun on 2017. 3. 2..
 */
public class ContentFragment extends Fragment {

    private static final String TAG = "ContentFragment";

    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    public static ContentFragment newInstance(int left, int right) {
        ContentFragment fragment = new ContentFragment();

        Bundle args = new Bundle();
        args.putInt(LEFT, left);
        args.putInt(RIGHT, right);
        fragment.setArguments(args);

        return fragment;
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
        View view = inflater.inflate(R.layout.simple_text, container, false);
        TextView result = (TextView) view.findViewById(R.id.status);
        int sum = getArguments().getInt(LEFT) + getArguments().getInt(RIGHT);
        result.setText("결과1=" + sum);
        return view;
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
