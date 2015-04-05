package com.example.saveinternaltest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addSaveBtnListener();
		addLoadBtnListener();
	}
	
	public void addSaveBtnListener(){
		Button btnSave = (Button)findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText etText = (EditText)findViewById(R.id.editText1);
				String text = etText.getText().toString();
				try {
					FileOutputStream fos = openFileOutput("message.txt", Context.MODE_PRIVATE);
					fos.write(text.getBytes());
					fos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.d("DEMO", "Can not save file");
				}
				Log.i("DEMO", "Button save clicked text="+text);
				
			}
		});
	}
	
	public void addLoadBtnListener(){
		Button btnLoad = (Button)findViewById(R.id.btnLoad);
		btnLoad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView tv = (TextView)findViewById(R.id.textView1);
				try {
					FileInputStream fis = openFileInput("message.txt");
					BufferedReader b_reader = new BufferedReader(new InputStreamReader(fis));
					String text="";
					tv.setText("");
					String path = getFilesDir().toString();
					tv.append("Path="+path+"\n");
					while ((text = b_reader.readLine())!= null){
						tv.append(text+"\n");
					}
					fis.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.d("DEMO", "Can not load file");
				}
				Log.i("DEMO", "Button load clicked ");
				
			}
		});
	}
}
