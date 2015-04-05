package com.example.viewanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewAnimation extends Activity {
	
	private TextView tvText;
	private Button btnClick;
	private ImageView imgIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
    }
    
    public void moveText(View view){
    	tvText = (TextView) findViewById(R.id.tvText);
        Animation myAnimation = AnimationUtils.loadAnimation(this, R.anim.pop_out);
        CycleInterpolator ci = new CycleInterpolator(5f);
        myAnimation.setInterpolator(ci);
        tvText.startAnimation(myAnimation);
    }
    
    public void moveButton(View view) {
    	btnClick = (Button) findViewById(R.id.btnClick);
    	//snaps back after the animation:
    	//Here, we animate using xml:
    	Animation buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.move_button);
    	btnClick.startAnimation(buttonAnimation);

    	
    	
    	//Here, we animate in code:
    	//the constructor args are startDeltaX, endDeltaX, startDeltaY, endDeltaY:
//    	TranslateAnimation buttonAnimation = new TranslateAnimation(0, 100, 0, 100);
//    	buttonAnimation.setDuration(1000);
//    	btnClick.startAnimation(buttonAnimation);
    	
    	
    }
    
    public void moveImage(View view) {
    	imgIcon = (ImageView) findViewById(R.id.imageView);
    	Animation imgAnimation = AnimationUtils.loadAnimation(this, R.anim.move_image);
    	imgIcon.startAnimation(imgAnimation);
    }
}