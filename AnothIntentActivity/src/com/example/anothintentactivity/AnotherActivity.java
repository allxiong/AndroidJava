package com.example.anothintentactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AnotherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void openEdit(View v){
		Intent editIntent = new Intent("com.example.myintentactivity.EDITACTIVITY");
		startActivity(editIntent);
	}
	
	public void openSearch(View v){
		Intent myIntent = new Intent();
		myIntent.setAction(Intent.ACTION_VIEW);
		myIntent.setData(Uri.parse("http://www.google.com"));
		startActivity(myIntent);
		
	}
	
	public void openCalculator(View v){
		Intent calculatorIntent = new Intent("com.example.calculator.CALCULATOR");
		startActivity(calculatorIntent);
		
	}
	
	public void openAmazon(View v){
		//Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
		//searchIntent.setData(Uri.parse("https://www.amazon.com"));
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com"));
		startActivity(myIntent);
	}
}
