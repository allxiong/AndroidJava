package com.example.viewgroups;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ViewGroupsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void showViewgroup(View v) {
    	LinearLayout viewlayout = (LinearLayout) findViewById(R.id.viewgrp_layout);
    	Button button = (Button) v;
    	if (viewlayout.getVisibility() == View.VISIBLE) {
    		viewlayout.setVisibility(View.GONE);
    		button.setText("Display ViewGroup");
    	} else {
    		viewlayout.setVisibility(View.VISIBLE);
    		button.setText("Hide ViewGroup");
    	}
    }
    
    public void displayText(View v) {
    	
    	TextView tv = (TextView) findViewById(R.id.textview1);
    	tv.setText("Now showing");
    	
    	/*
    	 * method 2: finding a child of the view group:
    	 */
    	
    	
    	/* LinearLayout ll = (LinearLayout) findViewById(R.id.horizontal_layout);
    	TextView tv = (TextView) ll.getChildAt(1);
    	tv.setText("Hello!"); */
    	

    }

}
