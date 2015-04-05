package com.example.audiotest;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class AudioTest extends Activity {
	MediaPlayer mp = null;
	MediaPlayer local_mp = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onStop() {
    	super.onStop();
    	if(mp != null) {
    		mp.release();
    		mp = null;
    	}
    	if(local_mp != null) {
    		local_mp.release();
    		local_mp = null;
    	}
    }
    
    /**
     * Demonstrates playing a sound on button click:
     * sound is stored in /res/raw as a midi file
     * @param v - button clicked
     */
    public void play(View v) {
    	if(mp != null) {
    		mp.release();
    	}
    	switch(v.getId()) {
    	
    	case R.id.btnC:
    		mp = MediaPlayer.create(getApplicationContext(), R.raw.c);
    		break;
    	case R.id.btnD:
    		mp = MediaPlayer.create(getApplicationContext(), R.raw.d);
    		break;
    	case R.id.btnE:
    		mp = MediaPlayer.create(getApplicationContext(), R.raw.e);
    		break;
    	case R.id.btnF:
    		mp = MediaPlayer.create(getApplicationContext(), R.raw.f);
    		break;
    	case R.id.btnG:
    		mp = MediaPlayer.create(getApplicationContext(), R.raw.g);
    		break;
    	}
    	mp.start();
    }
    
    /**
     * Demonstrates playing sound stored on the sdcard
     * @param v - button that was clicked
     */
    public void playLocalAudio(View v) {
    	// This file must be moved to the sd card of the
    	// emulator or device...
    	String path = "/sdcard/c.mid"; 
    	local_mp = new MediaPlayer();
    	try {
    		local_mp.setDataSource(path);
    		local_mp.prepare();
    		local_mp.start();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    

}
