package com.example.canvasdemo;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class DrawingView extends View {
	DrawingView(Context context) {
		super(context);
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		Path path = new Path();
		
		paint.setStyle(Paint.Style.FILL);
		
		//fill the entire canvas with paint color:
		paint.setColor(Color.GREEN);
		canvas.drawPaint(paint);
		
		
		//draw a red circle:
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		canvas.drawCircle(50, 50, 50, paint);
		
		//draw a blue rectangle:
		paint.setColor(Color.BLUE);
		canvas.drawRect(100, 25, 250, 75, paint);
		
		//draw three black lines:
		paint.setStrokeWidth(2.0f);
		paint.setColor(Color.BLACK);
		canvas.drawLine(200, 200, 200, 300, paint);
		canvas.drawLine(200, 300, 250, 350, paint);
		canvas.drawLine(250, 350, 200, 200, paint);
		
		//draw text
		paint.setColor(Color.RED);
		paint.setTextSize(24.0f);
		canvas.drawText("Drawing text", 200, 450, paint);
		
		//draw a yellow triangle using a path
//		paint.setColor(Color.YELLOW);
//		paint.setStyle(Paint.Style.FILL);
//		path.moveTo(350, 50);
//		path.lineTo(350, 100);
//		path.lineTo(400, 50);
//		path.close();
//		canvas.drawPath(path, paint);
		
		//offset the entire path, then redraw:
//		path.offset(75, 75);
//		canvas.drawPath(path, paint);
		
		//draw an image:
//		Resources res = this.getResources();
//		Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.smily);
//		canvas.drawBitmap(bmp, 175, 400, paint);
		
//		canvas.rotate(180, 175, 500);
//		canvas.drawBitmap(bmp, 175, 500, paint);
//		canvas.restore();
		
	}
}

