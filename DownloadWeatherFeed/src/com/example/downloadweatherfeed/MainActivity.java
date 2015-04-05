package com.example.downloadweatherfeed;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tvInfo;
	Button btnDownload;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvInfo = (TextView) findViewById(R.id.tvInfo);
		btnDownload = (Button) findViewById(R.id.btnDownload);
		btnDownload.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new downloadAgent().execute();
			}
		});
	}

	class downloadAgent extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String result = "";
			try {
				result = downloadWeather();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		private String downloadWeather() throws Exception {
			String data = "";
			HttpClient client = new DefaultHttpClient();
			URI website = new URI(
					"http://api.geonames.org/weatherJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				data += line + "\n";
				Log.i("DEMO", "weatherFeed=" + line);
			}
			reader.close();
			return data;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				JSONObject mainobj = (JSONObject) new JSONTokener(result)
						.nextValue();
				tvInfo.setText(mainobj.getClass().toString());
				JSONArray weatherObservItems = (JSONArray) mainobj
						.get("weatherObservations");

				String clouds = "";
				String temperature = "";
				String humidity = "";

				for (int i = 0; i < weatherObservItems.length(); i++) {
					JSONObject item = (JSONObject) weatherObservItems.get(i);
					clouds = item.getString("clouds");
					temperature = item.getString("temperature");
					humidity = item.getString("humidity");
					tvInfo.append("clouds: " + clouds + "\ttemperature: "
							+ temperature + "\thumidity: " + humidity + "\n");
				}
				// Object obj2 = obj1.get("weatherObservations");
				// tvInfo.append(obj2.getClass().toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//tvInfo.setText(result);
		}

	}
}
