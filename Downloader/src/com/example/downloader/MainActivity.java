package com.example.downloader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				try {
					downloadFromInternet();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				return null;
			}
			
		}.execute();
		
	}
	
	private void downloadFromInternet() throws Exception{
		URL url = new URL("http://www.google.com");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String line="";
		while( (line=reader.readLine())!=null){
			Log.d("DEBUG", line);
		}
		
	}
}
