package com.example.localizationtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tvGreeting;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvGreeting = (TextView)findViewById(R.id.textView2);
	}
	
	public void onBtnClicked(View v){
		tvGreeting.setVisibility(View.VISIBLE);
		
	}
}

