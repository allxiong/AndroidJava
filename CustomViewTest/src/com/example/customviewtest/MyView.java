package com.example.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

public class MyView extends View{

	public static final int BG_COLOR_ON = Color.RED;
	public static final int BG_COLOR_OFF = Color.BLUE;
	public static final int FG_COLOR_ON = Color.GREEN;
	public static final int FG_COLOR_OFF = Color.YELLOW;
	
	public static final boolean STATE_ON = true;
	public static final boolean STATE_OFF = false;
	
	private int bgColor = BG_COLOR_OFF;
	private int fgColor = FG_COLOR_OFF;
	private boolean viewState = STATE_OFF;
	
	private static final int MIN_WIDTH = 400;
	private static final int MIN_HEIGHT = 400;

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setMinimumWidth(MIN_WIDTH);
		setMinimumHeight(MIN_HEIGHT);
		
		this.setClickable(true);
		this.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if (viewState == STATE_OFF){
					viewState = STATE_ON;
					bgColor = BG_COLOR_ON;
					fgColor = FG_COLOR_ON;
					
				}else{
					viewState = STATE_OFF;
					bgColor = BG_COLOR_OFF;
					fgColor = FG_COLOR_OFF;
				}
				invalidate();
			}
		});
	}
	
	protected void onMeasure(int width, int height){
		setMeasuredDimension(
				getSuggestedMinimumWidth(), 
				getSuggestedMinimumHeight()
		);
		
	}
	
	protected void onDraw(Canvas canvas){
		canvas.drawColor(this.bgColor);
		Paint fgPaint = new Paint();
		fgPaint.setColor(this.fgColor);
		fgPaint.setAntiAlias(true);
		float centerX = this.getWidth()/2;
		float centerY = this.getHeight()/2;
		float radius = centerY/2;
		canvas.drawCircle(centerX, centerY, radius, fgPaint);
		invalidate();
	}
}
