package com.example.myintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class myService extends IntentService {

	public myService() {
		super("myService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service done running and will be destroyed",
				Toast.LENGTH_LONG).show();
	}

}
