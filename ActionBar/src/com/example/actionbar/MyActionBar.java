package com.example.actionbar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MyActionBar extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_action_bar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		TextView tv = (TextView)findViewById(R.id.textView1);

		switch(item.getItemId()){
		case R.id.greeting:
			tv.setText("Hello Action Bar!");
			return true;
		case R.id.show_submenu:
			tv.setText("Show sub menu!");
			return true;
		case R.id.search:
			tv.setText("Search selected!");
			return true;
		case R.id.email:
			tv.setText("Compose Email selected!");
			return true;
		case R.id.settings:
			tv.setText("Settings selected!");
			return true;
		default:
			return super.onOptionsItemSelected(item);
				
		}
		

	}
}
