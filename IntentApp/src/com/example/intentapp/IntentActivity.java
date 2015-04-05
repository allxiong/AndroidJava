package com.example.intentapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class IntentActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void openEdit(View v) {
    	String action = "com.example.intent.action.EditActivity";
    	Intent editIntent = new Intent(action);
    	startActivity(editIntent);
    }
    
    public void openGoogle(View v) {
    	Intent googleIntent = new Intent(Intent.ACTION_WEB_SEARCH);
    	googleIntent.setData(Uri.parse("http://www.google.com"));
    	startActivity(googleIntent);
    }
}
