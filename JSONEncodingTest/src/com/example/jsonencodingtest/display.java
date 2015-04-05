package com.example.jsonencodingtest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class display extends Activity{

	TextView tvData;
	Button btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_display);
		tvData = (TextView)findViewById(R.id.tvData);
		getJSONData();
		
	}
	
	private void getJSONData(){
		String data="";
		try {
			Bundle b = getIntent().getExtras();
	        data = b.getString("root");
	        Log.i("JSON", "display JSON string: "+data);
	        JSONObject obj = (JSONObject) new JSONTokener(data).nextValue();
			tvData.setText(obj.getClass().toString()+"\n");
			int id =  obj.getInt("id");
			tvData.append("id: "+id+"\n");
			String username =  obj.getString("username");
			tvData.append("username: "+username+"\n");
			JSONArray items = (JSONArray) obj.get("food");
			tvData.append("food: "+items+"\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnBackClicked(){
		this.finish();
	}

}
