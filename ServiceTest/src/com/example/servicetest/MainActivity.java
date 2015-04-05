package com.example.servicetest;

import com.example.servicetest.MyService.MyLocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	MyService myservice;
	boolean mBound=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		//bind to MyService
		Intent iservice = new Intent(this, MyService.class);
		bindService(iservice, myConnection, Context.BIND_AUTO_CREATE);

	}

	@Override
	protected void onStop() {
		super.onStop();
		//unbind service
		if (mBound){
			unbindService(myConnection);
			mBound = false;
		}
	}



	public void onBtnGetClicked(View v){
		if (mBound){
			String currentTime = myservice.getCurrentTime();
			TextView tvDisplay = (TextView) findViewById(R.id.tvDisplay);
			tvDisplay.setText(currentTime);

		}
		
	}
	private ServiceConnection myConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// We've bound to MyService, cast the IBinder and get MyService instance
			MyLocalBinder myBinder = (MyLocalBinder)service; 
			myservice = myBinder.getService();
			mBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mBound = false;
		}
		
	};
	
}
