package com.example.jsonencoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tvData;
	Button btnEncode, btnSend;
	JSONObject root;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvData = (TextView)findViewById(R.id.tvData);
		btnEncode = (Button)findViewById(R.id.btnEncode);
		btnEncode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				encodeJSONData();
			}
		});
		
		btnSend = (Button)findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendData();
			}
		});
	}
	
	private void encodeJSONData(){
		root = new JSONObject();
		
		try {
			root.put("id", 12345);
			root.put("studentname", "John");
			JSONArray foods = new JSONArray();
			foods.put("pizza");
			foods.put("pasta");
			foods.put("ice cream");
			root.put("foods", foods);
			tvData.setText(root.toString(1));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendData(){
		Intent intent = new Intent(this, receiver.class);
		Log.i("JSON", root.toString());
		Bundle bundle = new Bundle();
		bundle.putString("root", root.toString());
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
