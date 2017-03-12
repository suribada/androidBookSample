package com.suribada.androidbook.chap8;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.naver.android.sample.R;

/**
 * 디바이스의 Volume 버튼에 따라 SeekBar를 이동해준다.
 *
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class VolumeChangedActivity extends Activity {

	private SeekBar volumeSeekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_seekbar);
		volumeSeekBar = (SeekBar)findViewById(R.id.volume_seekbar);
		volumeSeekBar.setThumbOffset(12);
		
		final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);		
		volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_RING)); // 현재 Volume 세팅
		volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)); // 최대 Volume 세팅
		volumeSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				/*
				 * 사용자가 drag해서 움직이는 경우 Volume 크기도 늘려준다.
				 * 만일 if 문이 없다면 무한 루프에 빠지게 된다.
				 */
				if (fromUser) { 
					audioManager.setStreamVolume(AudioManager.STREAM_RING, progress, 0);
				}	
			}
			
		});
	}
	
	/*
	 * 보내는 쪽에서는 아래와 같은 형태를 띤다.
	 * 
	 * Intent intent = new Intent("android.media.VOLUME_CHANGED_ACTION");
	 * intent.putExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 3);
	 * sendBroadcast(intent);
	 */
	private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(VOLUME_CHANGED_ACTION)) {
				
				int value = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1);
				if (value > -1) {
					volumeSeekBar.setProgress(value);
				}
			}
		}
	};
	

	@Override
	protected void onResume() {
		super.onResume();
		/* 
		 * Foreground 상태에만 Broadcast를 받도록 하기 위해서는,
		 * onResume에서 등록하고 onPause에서 해제하도록 한다.
		 * 
		 * 등록할 때는, 받고자 하는 Intent에 대해서 IntentFilter를 receiver에 함께 전달하게 된다. 
		 */
		IntentFilter filter = new IntentFilter();
		filter.addAction(VOLUME_CHANGED_ACTION);
		registerReceiver(receiver, filter);
	}
	
	@Override
	protected void onPause() {
		unregisterReceiver(receiver);
		super.onPause();
	}	
	
}
