package com.example.jsondownloader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tvData;
	Button btnDownload;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvData = (TextView)findViewById(R.id.tvData);
		btnDownload = (Button)findViewById(R.id.btnDownload);
		btnDownload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new downloader().execute();
				
			}
		});
	}

	class downloader extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String result="";
			try {
				result = downloadData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		private String downloadData() throws Exception {
			URL url = new URL(
					"https://bugzilla.mozilla.org/rest/bug/707428/comment");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String rdata = "";
			String line = "";
			while ((line=reader.readLine())!=null){
				rdata += line+"\n";
			}
			reader.close();
			return rdata;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//tvData.setText(result);
			try {
				JSONObject obj1 = (JSONObject) new JSONTokener(result).nextValue();
				tvData.setText(obj1.getClass().toString()+"\n");
				JSONObject bugs = (JSONObject) obj1.get("bugs");
				tvData.append(bugs.getClass().toString()+"\n");
				JSONObject child1 = (JSONObject)bugs.get("707428");
				tvData.append(child1.getClass().toString()+"\n");
				JSONArray comments = (JSONArray)child1.get("comments");
				tvData.append(comments.getClass().toString()+"\n");
				
				String author="";
				int id = 0;
				for (int i=0; i<comments.length(); i++){
					JSONObject item = (JSONObject)comments.getJSONObject(i);
					author = item.getString("author");
					id = item.getInt("id");
					tvData.append("author="+author+" id = "+id+"\n");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
