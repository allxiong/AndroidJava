package com.example.servicetest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{

	private final IBinder myBinder = new MyLocalBinder();
	@Override
	public IBinder onBind(Intent intent) {
		return myBinder;
	}

	public class MyLocalBinder extends Binder{
		MyService getService(){
			// Return this instance of MyService so clients can call public methods
			return MyService.this;
		}
	}
	
	public String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.US);
		Log.i("DEMO", "Service time: "+df.format(new Date()));
		return df.format(new Date());
	}

}
