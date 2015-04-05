package com.example.videotest;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoTest extends Activity {

	VideoView vView = null;
	MediaController mc = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		VideoView vView = (VideoView)findViewById(R.id.vvMp4);
		mc = new MediaController(this);
		vView.setMediaController(mc);
		vView.setVideoPath("sdcard/stunning.mp4");
		vView.requestFocus();
		mc.show();
		vView.start();
	}
}
