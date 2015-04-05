package com.example.asynch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

public class Asynch extends Activity {
    /** Called when the activity is first created. */
	int count = 1;
	TextView tvSqrt;
    TextView tvHello;
    ProgressBar progBar;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tvHello = (TextView) findViewById(R.id.txtHello);
        
        FindSquareRoot task = new FindSquareRoot();
        task.execute(new Integer[] {63, 1, 15});
        
        ((Button) findViewById(R.id.btnSayHello)).setOnClickListener(
    		new Button.OnClickListener() {
    			public void onClick(View btn) {
    				tvHello.setText("Hello " + count);
    				count++;
    			}
    		}
        );
    }
    
    private class FindSquareRoot extends AsyncTask<Integer, Integer, String> {
    	Integer progress = new Integer(0);
    	
    	@Override
    	protected String doInBackground(Integer... args) {
    		Double result = NewtonSqrt.sqrt(args[0], args[1], args[2]);
    		
    		//simulate a long task by delaying for 5 seconds:
    		long startTime = System.currentTimeMillis();
    		long delay = 5000;
    		long elapsedTime;
    		while(System.currentTimeMillis() < startTime + delay) {
    			elapsedTime = System.currentTimeMillis() - startTime;
    			if( elapsedTime % 50 == 0) {
    				//update the progress bar...
    				progress = (int) elapsedTime / 50;
    				publishProgress(progress);
    			}
    		}
    		//return a string representation of the square root:
    		return String.valueOf(result);
    	}
    	
    	@Override
    	protected void onProgressUpdate(Integer... units) {
    		progBar = (ProgressBar) findViewById(R.id.progressBar);
    		progBar.setProgress(units[0]);
    	}
    	
    	@Override
		protected void onPostExecute(String result) {
    		tvSqrt = (TextView) findViewById(R.id.txtSqrt);
    		progBar = (ProgressBar) findViewById(R.id.progressBar);
    		progBar.setVisibility(View.GONE); //GONE is a constant value
    		tvSqrt.setText(result);
    	}
    }
}

