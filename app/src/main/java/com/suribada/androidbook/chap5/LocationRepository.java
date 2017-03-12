package com.suribada.androidbook.chap5;

import android.location.Location;

/**
 * Created by Noh.Jaechun on 15. 12. 23..
 */
public class LocationRepository {

	private Location location;

	public void saveLocation(Location location) {
		this.location = location;
	}

	public Location retrieveLocation() {
		return location;
	}
}
