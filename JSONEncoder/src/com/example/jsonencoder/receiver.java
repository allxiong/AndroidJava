package com.example.jsonencoder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class receiver extends Activity{

	TextView tvData;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_receiver);
		tvData = (TextView)findViewById(R.id.tvData);
		getJSONDAta();
	}
	
	private void btnBackClicked(){
		this.finish();
	}
	
	private void getJSONDAta(){
		String data="";
		Bundle bund = getIntent().getExtras();
		data = bund.getString("root");
		tvData.setText(data);
	}

}
