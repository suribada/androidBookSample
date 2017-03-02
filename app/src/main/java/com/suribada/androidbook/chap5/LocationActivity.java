package com.suribada.androidbook.chap5;

import android.location.Location;
import android.os.Bundle;

import com.naver.android.sample.R;

/**
 * Created by nhn on 15. 12. 23..
 */
public class LocationActivity extends BaseActivity {

	private LocationRepository locationRepository;
	private Location lastLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_button);
		locationRepository = new LocationRepository();
	}

	@Override
	protected void onResume() {
		lastLocation = locationRepository.retrieveLocation();
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationRepository.saveLocation(myLocationManager.getLastLocation());
	}

}
