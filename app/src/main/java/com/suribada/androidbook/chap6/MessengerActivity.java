package com.suribada.androidbook.chap6;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.suribada.androidbook.R;

/**
 * Created by Noh.Jaechun on 15. 12. 22..
 */
public class MessengerActivity extends Activity {

	private class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case MessengerService.MSG_WEATHER:
					Bundle bundle = msg.getData();
					weather.setText(bundle.getString(MessengerService.WEATHER_TEXT) + ", 온도: " + bundle.getInt(MessengerService.TEMPERATURE) + "도");
					break;
				default:
					super.handleMessage(msg);
			}
		}
	}

	private Messenger outMessenger;

	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			outMessenger = new Messenger(service);
			try {
				Message msg = Message.obtain(null, MessengerService.MSG_REGISTER_CLIENT);
				msg.replyTo = inMessenger;
				outMessenger.send(msg);
			} catch (RemoteException e) {
			}
			Toast.makeText(MessengerActivity.this, "연결되었습니다.", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onServiceDisconnected(ComponentName className) {
			outMessenger = null;
			Toast.makeText(MessengerActivity.this, "연결이 끊겼습니다.", Toast.LENGTH_SHORT).show();
		}

	};

	private Messenger inMessenger = new Messenger(new IncomingHandler());

	private TextView weather;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_and_button);
		weather = (TextView) findViewById(R.id.text);
	}

	@Override
	protected void onStart() {
		super.onStart();
		bindService(new Intent(this, MessengerService.class), serviceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (outMessenger != null) {
			try {
				Message msg = Message.obtain(null, MessengerService.MSG_UNREGISTER_CLIENT);
				msg.replyTo = inMessenger;
				outMessenger.send(msg);
			} catch (RemoteException e) {
			}
		}
		unbindService(serviceConnection);
	}

	public void onClickButton1(View view) {
		Toast.makeText(MessengerActivity.this, "다시 읽어들입니다.", Toast.LENGTH_SHORT).show();
		if (outMessenger != null) {
			try {
				Message msg = Message.obtain(null, MessengerService.MSG_REFRESH);
				msg.replyTo = inMessenger;
				outMessenger.send(msg);
			} catch (RemoteException e) {
			}
		}
	}

}
