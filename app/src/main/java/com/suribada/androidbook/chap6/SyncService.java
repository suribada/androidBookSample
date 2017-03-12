package com.suribada.androidbook.chap6;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Noh.Jaechun on 15. 12. 22..
 */
public class SyncService extends Service {

	private static final String TAG = "SyncService";
	
	/**
	 * Service가 살아있는 동안에서 startService할 때, onCreate가 한번만 호출된다. 
	 * 따라서 Service를 위한 초기화 작업을 진행한다.
	 */
	@Override
	public void onCreate() {
		Log.d(TAG, "SyncService onCreate");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Log.d(TAG, "SyncService started");
				SystemClock.sleep(10000); // 10 초간 동작 emulate
				final ResultReceiver receiver= intent.getParcelableExtra(Constant.EXTRA_RECEIVER);
				receiver.send(Constant.SYNC_COMPLETED, null);
				stopSelf(); // 작업이 종료된 이후에 stopSelf를 하는게 일반적인 형태
			}

		}).start();
		
		return START_NOT_STICKY;
	}
	
	/**
	 * Service의 유일한 abstract 메소드지만, bound Service에서만 사용하므로 보통 null 리턴하도록 만든다.
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	/**
	 * Service가 제거되기 전에 실행
	 */
	@Override
	public void onDestroy() {
		Log.d(TAG, "SyncService onDestroy");
	}

}
