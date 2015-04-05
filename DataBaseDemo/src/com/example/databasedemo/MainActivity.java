package com.example.databasedemo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	DBAdpater myDB;
	TextView tv;
	EditText etName, etMajor;
	//public static int myid=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etName = (EditText)findViewById(R.id.etName);
		etMajor = (EditText)findViewById(R.id.etMajor);
		openDB();
	}
	
	public void openDB(){
		myDB = new DBAdpater(this);
		myDB.open();
		
	}

	
	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		closeDB();
	}

	public void closeDB(){
		myDB.close();
	}
	
	public void onUpdateClicked(View v){
		
		String name = etName.getText().toString();
		String major = etMajor.getText().toString();
		long newRow = myDB.insertRow(name, major);
		Cursor cursor = myDB.getRow(newRow);
		displayRecords(cursor);
		
	}
	
	public void onQueryClicked(View v){

		Cursor cursor = myDB.getAllRows();
		displayRecords(cursor);
	}
	
	public void onClearClicked(View v){
		myDB.deleteAll();
	}
	
	public void displayRecords(Cursor cursor){
		tv = (TextView)findViewById(R.id.tvDBView);
		String message = "";
		if (cursor.moveToFirst()){
			do{
				int id = cursor.getInt(0);
				String name = cursor.getString(1);
				String major = cursor.getString(2);
				message += "id= "+id+" name="+name+" major="
						+major+"\n";
			}while(cursor.moveToNext());
		}
		tv.setText(message);
		cursor.close();
	}
}
