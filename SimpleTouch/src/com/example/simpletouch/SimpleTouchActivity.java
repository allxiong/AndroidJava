package com.example.simpletouch;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class SimpleTouchActivity extends Activity  {

	private GestureDetectorCompat gestureDetector;
	TextView tv;
	String outputmsg = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv = (TextView) findViewById(R.id.textView1);
		gestureDetector = new GestureDetectorCompat(this, new myGestureListener());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}

	public void onClear(View v){
		tv.setText("");
	}

	class myGestureListener extends SimpleOnGestureListener{

		/* (non-Javadoc)
		 * @see android.view.GestureDetector.SimpleOnGestureListener#onDown(android.view.MotionEvent)
		 */
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			outputmsg = String.format("onDown (%.2f, %.2f)", e.getX(), e.getY());
			tv.setText(tv.getText()+"\t"+outputmsg);
			return super.onDown(e);
		}

		/* (non-Javadoc)
		 * @see android.view.GestureDetector.SimpleOnGestureListener#onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)
		 */
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			outputmsg = String.format("onScroll (%.2f, %.2f) (%.2f, %.2f)", e1.getX(), e1.getY(), e2.getX(), e2.getY());
			tv.setText(tv.getText()+"\t"+outputmsg);
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		/* (non-Javadoc)
		 * @see android.view.GestureDetector.SimpleOnGestureListener#onSingleTapUp(android.view.MotionEvent)
		 */
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			outputmsg = String.format("onSingleTapUp (%.2f, %.2f)", e.getX(), e.getY());
			tv.setText(tv.getText()+"\t"+outputmsg);
			return super.onSingleTapUp(e);
		}
		
		
	}
}
