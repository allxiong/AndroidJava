package com.example.webviewex;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView wv = (WebView)findViewById(R.id.webView1);
		//wv.loadUrl("http://www.google.com");
		String htmlString = "<HTML><body>This is a html page</body></HTML>";
		wv.loadData(htmlString, "text/html", null);
	}
}
