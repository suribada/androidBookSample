package com.suribada.androidbook.chap3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.naver.android.sample.R;

/**
 * Created by Noh.Jaechun on 15. 9. 4..
 */
public class HandlerThreadActivity extends Activity {

    private static final String TAG = "HandlerThreadActivty";

    private Handler favoriteHandler;
    private Looper favoriteLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_button);
        HandlerThread handlerThread = new HandlerThread("Favorite Processing Thread") {

            @Override
            protected void onLooperPrepared() {
                getLooper().myQueue().addIdleHandler(new MessageQueue.IdleHandler() {

                    @Override
                    public boolean queueIdle() {
                        if (isFinishing()) {
                            getLooper().quit();
                            return false;
                        }
                        return true;
                    }

                });
            }

        };
        handlerThread.start();
        favoriteLooper = handlerThread.getLooper();
        favoriteHandler = new Handler(favoriteLooper) {

            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG, "msg what=" + msg.what);
                SystemClock.sleep(2000);
            }

        };

    }

    private int i;

    public void onClickButton1(View view) {
        Log.d(TAG, "i=" + (++i));
        favoriteHandler.sendEmptyMessage(i);
    }

    @Override
    protected void finalize() throws Throwable {
        Log.d(TAG, "finalize");
        super.finalize();
    }
}
