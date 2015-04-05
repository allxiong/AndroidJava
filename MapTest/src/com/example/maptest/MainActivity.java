package com.example.maptest;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        GoogleMap mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.addMarker(new MarkerOptions()
        .position(new LatLng(10, 10))
        .snippet("population: 4,897,345")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        .title("Hello world"));
        
        mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(MainActivity.this, marker.getSnippet(), Toast.LENGTH_LONG).show();
				return false;
			}
		});
    }
}
