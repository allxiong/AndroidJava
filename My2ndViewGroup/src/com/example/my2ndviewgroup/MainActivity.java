package com.example.my2ndviewgroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onShowViewClicked(View v){
		LinearLayout L_layout = (LinearLayout)findViewById(R.id.nestedViewGrp);
		Button btnShowView = (Button)v;
		
		if (L_layout.getVisibility()==View.VISIBLE){
			btnShowView.setText("Show View Group");
			L_layout.setVisibility(View.INVISIBLE);
		}
		else{
			btnShowView.setText("Hide View Group");
			L_layout.setVisibility(View.VISIBLE);
		}
	}
	
	public void onShowText(View v){
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Now showing");
		
	}
}
