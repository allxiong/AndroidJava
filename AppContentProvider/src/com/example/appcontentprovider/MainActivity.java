package com.example.appcontentprovider;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView tvInfo;
	EditText etName, etGrade;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etName = (EditText)findViewById(R.id.etName);
		etGrade = (EditText)findViewById(R.id.etGrade);
		tvInfo = (TextView)findViewById(R.id.tvInfo);

	}
	
	public void onBtnAddClicked(View v){
/*		ContentValues values = new ContentValues();
		values.put(StudentsProvider.NAME, etName.getText().toString());
		values.put(StudentsProvider.GRADE, etGrade.getText().toString());
		
		Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);
		Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();
		*/
		
		ContentValues values = new ContentValues();
		values.put(StudentsProvider.NAME, etName.getText().toString());
		values.put(StudentsProvider.GRADE, etGrade.getText().toString());
		Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);
		Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();

	}
	
	public void onBtnRetrieveClicked(View v){
		String URL = "content://com.example.appcontentprovider/students";
		Uri students = Uri.parse(URL);
		Cursor c = getContentResolver().query(students, null, null, null, "name");
		if (c.moveToFirst()){
			tvInfo.setText("");
			do{
				tvInfo.append("Student ID: "+c.getString(c.getColumnIndex(StudentsProvider.ID))+
			", NAME: "+c.getString(c.getColumnIndex(StudentsProvider.NAME))+
			", GRADE: "+c.getString(c.getColumnIndex(StudentsProvider.GRADE))+"\n");
			}while( c.moveToNext());
		}

	}	
}
