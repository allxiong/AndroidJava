package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;

public class Calculator extends Activity {
	
	// a string to represent the content of the display
	private String strDisplay = new String("");

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ((Button)findViewById(R.id.opClear)).setOnClickListener(
        		new Button.OnClickListener(){
        			public void onClick(View view){
        				
        				EditText display = (EditText) findViewById(R.id.display);
        				strDisplay = "";
        				display.setText(strDisplay);
        			}
        		}
       );
    }
    
    public void enterDigit(View view) {
    	EditText display = (EditText) findViewById(R.id.display);
    	Button button = (Button) view;
    	strDisplay += button.getText();
    	display.setText(strDisplay);
    	Toast.makeText(this, button.getText(), Toast.LENGTH_SHORT).show();
    }
    
    public void clicked(View view){
    	
    	switch (view.getId()){
		case R.id.display: 
			EditText ev = (EditText)view;
			Toast.makeText(this, ev.getText(), Toast.LENGTH_SHORT).show();
			break;
		case R.id.status:
			TextView tv = (TextView)view;
			Toast.makeText(this, tv.getText(), Toast.LENGTH_SHORT).show();
			break;
		case R.id.opClear:
			Button bc = (Button)view;
			Toast.makeText(this, bc.getText(), Toast.LENGTH_SHORT).show();
			break;
    	}
    	
    }

    
    
}