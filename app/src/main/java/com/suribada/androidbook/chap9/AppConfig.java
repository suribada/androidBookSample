package com.suribada.androidbook.chap9;

import android.content.Context;

/**
 *  Created by Noh.Jaechun on 2017. 3. 2..
 */
public class AppConfig {
	
	public static void initialize(Context context) {
		
	}
	
	   // Stops scanning after 10 seconds.
	/*
    private static final long SCAN_PERIOD = 10000;
    ...
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        ...
    }
    */

}
