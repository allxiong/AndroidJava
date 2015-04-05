package com.example.xmltest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract.Document;
import android.widget.TextView;

public class StockInfoActivity extends Activity {

	private static final String TAG = "STOCKQUOTE";
	TextView tvChange;
	TextView tvDayslow;
	TextView tvDayshigh;
	TextView tvLastTrade;

	static final String KEY_ITEM = "quote";
	static final String KEY_NAME = "Name";
	static final String KEY_CHANGE = "Change";
	static final String KEY_DAYSHIGH = "DaysHigh";
	static final String KEY_DAYSLOW = "DaysLow";
	static final String KEY_YEARHIGH = "YearHigh";
	static final String KEY_YEARLOW = "YearLow";
	static final String KEY_LASTTRADE = "LastTradePriceOnly";

	String Nam = "";
	String Dayslow = "";
	String Dayshigh = "";
	String Change = "";
	String Daysrange = "";
	String LastTrade = "";
	String YearHigh = "";
	String YearLow = "";

	String yqlURL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22MSFT%22)&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
	String yahooURLFirst = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22";
	String yahooURLSecond = "%22)&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stockinfo);

		// tvName = (TextView)findViewById(R.id.tvName);
		tvDayslow = (TextView) findViewById(R.id.tvDayslow);
		tvDayshigh = (TextView) findViewById(R.id.tvDayshigh);
		tvChange = (TextView) findViewById(R.id.tvChange);
		tvLastTrade = (TextView) findViewById(R.id.tvLastTrade);
		
		new myAsyncTask().execute();
	}

	private class myAsyncTask extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			try {
				url = new URL(params[0]);
				URLConnection connection = url.openConnection();
				
				HttpURLConnection httpConnection = (HttpURLConnection)connection;
				int responseCode = httpConnection.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK){
					InputStream in = httpConnection.getInputStream();
					
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					org.w3c.dom.Document dom = db.parse(in);
					
					Element elm = dom.getDocumentElement();
					NodeList node = elm.getElementsByTagName("quote");
					if (node !=null && node.getLength()>0){
						for (int i=0; i<node.getLength(); i++){
							StockInfo theStock = getStockInformation(elm);
							
							Nam = theStock.getName();
							Dayslow = theStock.getDays_low();
							Dayshigh = theStock.getDays_high();
							Change = theStock.getChange();
							Daysrange = theStock.getDays_range();
							LastTrade = theStock.getLastTradePrice();
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			return null;
		}
		
		private StockInfo getStockInformation(Element entry){
			String stockName = getTextValue(entry, "Name");
			String stockDayslow = getTextValue(entry, "DaysLow");
			String stockDayshigh = getTextValue(entry, "DaysHigh");
			String stockChange = getTextValue(entry, "Change");
			String stockDaysrange = getTextValue(entry, "DaysRange");
			String stockLastTrade = getTextValue(entry, "LastTradePriceOnly");
			
			//StockInfo stock = new StockInfo();
			
		}
		
		private String getTextValue(Element entry, String tagName){
			String resultValue = null;
			NodeList nl = entry.getElementsByTagName(tagName);
			if(nl !=null && nl.getLength() > 0){
				Element element = (Element)nl.item(0);
				resultValue = element.getFirstChild().getNodeValue();
			}
			
			return resultValue;
		}
	}
}
