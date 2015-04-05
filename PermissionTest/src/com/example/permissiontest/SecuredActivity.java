package com.example.permissiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecuredActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secureui);
	}
	
	public void onBtnFinishClicked(View v){
		this.finish();
	}
}
