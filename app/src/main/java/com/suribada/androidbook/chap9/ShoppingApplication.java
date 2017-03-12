package com.suribada.androidbook.chap9;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

public class ShoppingApplication extends Application {

	private static final String TAG = "ShoppingApplication";

	private static ShoppingApplication application;
	
	private ArrayList<CartProduct> cart = new ArrayList<>();
	
	private ProductRepository productRepository;
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "Application onCreate");
		application = this;
		/* load application properties */
		AppConfig.initialize(this);
		productRepository = new ProductRepository(this);
		if (isDefaultProcess()) {
			startService(new Intent(this, Service1.class));
		}
		startService(new Intent(this, CategoryUpdaterService.class));
		registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
	}
	
	public ArrayList<CartProduct> getCart() {
		return cart;
	}
	
	public void addCart(CartProduct product) {
		if (!cart.contains(product)) {
			cart.add(product);
		}
	}
	
	public void clearCart() {
		cart.clear();
	}
	
	public ProductRepository getProductRepository() {
		return productRepository;
	}
	
	public static ShoppingApplication getApplication() {
		return application;
	}
	
	
	private boolean isDefaultProcess() {
		int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();

        for (RunningAppProcessInfo each : processInfos) {
            if (each.processName.equals(getPackageName())) {
                return true;
            }                         
        }
        return false;
    }
	
	private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
		
		@Override
		public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
			logMemInfo(activity, "create");
		}
		
		@Override
		public void onActivityStarted(Activity activity) {
			logMemInfo(activity, "start");
		}
		
		@Override
		public void onActivityResumed(Activity activity) {
			logMemInfo(activity, "resume");
		}
		
		@Override
		public void onActivityPaused(Activity activity) {
			logMemInfo(activity, "pause");
		}

		
		@Override
		public void onActivityStopped(Activity activity) {
			logMemInfo(activity, "stop");
		}
		
		@Override
		public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
			logMemInfo(activity, "saveInstanceState");
		}
		
		@Override
		public void onActivityDestroyed(Activity activity) {
			logMemInfo(activity, "destroy");
		}
		
		private void logMemInfo(Activity activity, String method) {
			Debug.MemoryInfo mi = new Debug.MemoryInfo();
			Debug.getMemoryInfo(mi);
			
			Log.d(TAG, activity.getClass().getSimpleName()  + " " + method
					+ " phase MemoryInfo(total) pss=" + mi.getTotalPss() 
					+ ", sharedDirty=" + mi.getTotalSharedDirty() 
					+ ", privateDirty=" + mi.getTotalPrivateDirty() + "."); 
		}
	
	};

}
