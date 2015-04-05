package com.example.httpclienttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {
	private static final int NUM1 = 3;
	private static final int NUM2 = 4;
	private static final String LOCALHOST = "10.0.2.2:8888/";
	private TextView tvSum = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSum = (TextView) findViewById(R.id.txtSum);
    }
    
    public void clearText(View v) {
    	TextView tvSum = (TextView) v;
    	tvSum.setText("");
    }
    
    public void sumUsingGet(View v) {
    	
    	
		HttpClient getClient = new DefaultHttpClient();
		String getReq = "http://" + LOCALHOST + "sumWithGet.php?num1=" + NUM1 + "&num2=" + NUM2;
		HttpGet request = new HttpGet(getReq);
		HttpResponse response = null;
		try {
			response = getClient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	tvSum.setText(readResponse(response));
    }
    
    public void sumUsingPost(View v) {
    	HttpClient postClient = new DefaultHttpClient();
    	String postReq = "http://" + LOCALHOST + "sumWithPost.php";
    	HttpPost request = new HttpPost(postReq);
    	List<NameValuePair> postParams = new ArrayList<NameValuePair>();
    	
    	//name value pairs must be encoded as String / String:
    	String strNum1 = String.format("%d", NUM1);
    	String strNum2 = String.format("%d", NUM2);
    	postParams.add(new BasicNameValuePair("num1", strNum1));
    	postParams.add(new BasicNameValuePair("num2", strNum2));
    	
    	// the post parameters list must be wrapped in a form entity object, 
    	// which encodes the parameters for an HTTP call. This may throw an 
    	// exception if the encoding is unsupported:
    	UrlEncodedFormEntity postEntity = null;
    	try {
			postEntity = new UrlEncodedFormEntity(postParams);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		request.setEntity(postEntity);
		
		HttpResponse response = null;
		try {
			response = postClient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tvSum.setText(readResponse(response));
    }
    
    /**
     * processes an HttpResponse and returns a String
     * @param res - an HttpResponse object
     * @return String representation of the response object
     */
    private String readResponse(HttpResponse res) {

    	BufferedReader input = null;
    	StringBuffer strBuff = new StringBuffer("");
    	
    	try {
    		input = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
    		
    		String line = "";
    		while ((line = input.readLine()) != null) {
    			strBuff.append(line);
    		}
    		input.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return strBuff.toString();
    }
}
