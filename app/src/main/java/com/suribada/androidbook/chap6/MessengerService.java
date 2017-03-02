package com.suribada.androidbook.chap6;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by nhn on 15. 12. 22..
 */
public class MessengerService extends Service {

	/* input message */
	public static final int MSG_REGISTER_CLIENT = 1;
	public static final int MSG_UNREGISTER_CLIENT = 2;
	public static final int MSG_REFRESH = 3;

	/* output message */
	public static final int MSG_WEATHER = 11;

	public static final String WEATHER_TEXT = "weatherText";
	public static final String TEMPERATURE = "temperature";

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private ArrayList<Messenger> clients = new ArrayList<>();

	private Bundle lastData;

	private class IncomingHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case MSG_REGISTER_CLIENT:
					clients.add(msg.replyTo);
					if (lastData != null) {
						Message message = Message.obtain(null, MSG_WEATHER);
						message.setData(lastData);
						try {
							msg.replyTo.send(message);
						} catch (RemoteException e) {
						}
					}
					break;
				case MSG_UNREGISTER_CLIENT:
					clients.remove(msg.replyTo);
					break;
				case MSG_REFRESH:
					scheduledFuture.cancel(true);
					fetchWeather();
					break;
				default:
					super.handleMessage(msg);
			}
		}

	}

	private Messenger messenger = new Messenger(new IncomingHandler());
	private ScheduledFuture scheduledFuture;

	@Override
	public void onCreate() {
		super.onCreate();
		fetchWeather();
	}

	private static final String[] WEATHER_LIST = {"맑음", "흐림", "흐리고 비", "비온 후 맑음", "안개"};

	private Random random = new Random();

	private void fetchWeather() {
		scheduledFuture = scheduler.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					SystemClock.sleep(2000);
					Message message = Message.obtain(null, MSG_WEATHER);
					Bundle bundle = message.getData();
					bundle.putString(WEATHER_TEXT, WEATHER_LIST[random.nextInt(5)]);
					bundle.putInt(TEMPERATURE, random.nextInt(35));

					lastData = new Bundle(bundle);
					for (int i = clients.size() - 1; i >= 0; i--) {
						try {
							clients.get(i).send(message);
						} catch (RemoteException e) {
							clients.remove(i);
						}
					}
				}

			}, 0, 1, TimeUnit.MINUTES);
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return messenger.getBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		//return super.onUnbind(intent);
		Log.d("suribada", "onUnbind");
		return true;
	}

	@Override
	public void onRebind(Intent intent) {
		Log.d("suribada", "onRebind");
		//super.onRebind(intent);
	}
}
