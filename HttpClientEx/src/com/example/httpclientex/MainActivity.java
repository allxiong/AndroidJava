package com.example.httpclientex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tvHttpData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvHttpData = (TextView)findViewById(R.id.tvHttpData);
		new HttpGetAgent().execute();
	
	}
	
	class HttpGetAgent extends AsyncTask<Void, String, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String webData;
			try {
				webData = getInternetData();
				publishProgress(webData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		public String getInternetData() throws Exception {
			String data = "";
			try {
				HttpClient client = new DefaultHttpClient();
				URI website = new URI("http://www.google.com");
				HttpGet request = new HttpGet();
				request.setURI(website);
				HttpResponse response = client.execute(request);
				BufferedReader reader = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()));
				String line = "";
				while ((line=reader.readLine())!=null){
					data+=line+"\n";
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}

		protected void onProgressUpdate(String... values) {
			tvHttpData.setText(values[0]);

		}

	}
}
