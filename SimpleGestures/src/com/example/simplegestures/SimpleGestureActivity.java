package com.example.simplegestures;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;
import android.widget.TextView;

public class SimpleGestureActivity extends Activity implements
		OnGestureListener {
	GestureDetectorCompat gestureDetector;
	private String output = "";
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gestureDetector = new GestureDetectorCompat(this, this);
		tv = (TextView) findViewById(R.id.infodisplay);
		addOnTouchListner();
	}

	public void opClear(View v) {
		//tv.setText("");
		showPrompt();
	}

	public void showPrompt(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setMessage("Are you sure you want to clear the event output?")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				tv.setText("Clear action performed");
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				tv.setText(tv.getText()+"\n"+"Clear action canceled");
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	
	public void addOnTouchListner() {
		ScrollView scv = (ScrollView) findViewById(R.id.scrollView1);
		scv.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				output = String.format("Touch coordinates: (%.2f, %.2f)",
						event.getX(), event.getY());
				tv.setText(tv.getText() + "\t" + output);
				return false;
			}

		});
	}
	

	/*
	 * /* (non-Javadoc)
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		output = String.format("onDonw (%.2f, %.2f)\n", e.getX(), e.getY());
		tv.setText(tv.getText() + "\t" + output);
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		output = String
				.format("onShowPress (%.2f, %.2f)\n", e.getX(), e.getY());
		tv.setText(tv.getText() + "\t" + output);

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		output = String.format("onSingleTapUp (%.2f, %.2f)\n", e.getX(),
				e.getY());
		tv.setText(tv.getText() + "\t" + output);
		return true;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		output = String.format(
				"onScroll (%.2f, %.2f), (%.2f, %.2f)\ndX=%.2f \t dY=%.2f",
				e1.getX(), e1.getY(), e2.getX(), e2.getY(), distanceX,
				distanceY);
		tv.setText(tv.getText() + "\t" + output);
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		output = String
				.format("onLongPress (%.2f, %.2f)\n", e.getX(), e.getY());
		tv.setText(tv.getText() + "\t" + output);

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		output = String.format(
				"onFling (%.2f, %.2f) (%.2f, %.2f)\n vX=%.2f, \tvY=%.2f\n",
				e1.getX(), e1.getY(), e2.getX(), e2.getY(), velocityX,
				velocityY);
		tv.setText(tv.getText() + "\t" + output);
		return true;
	}

}
