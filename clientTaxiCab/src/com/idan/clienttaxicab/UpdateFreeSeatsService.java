package com.idan.clienttaxicab;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.IntentService;
import android.content.Intent;

public class UpdateFreeSeatsService extends IntentService
{
	private int freeSeats = -1;
	private String deviceID;
	
	public UpdateFreeSeatsService(String name)
	{
		super(name);
	}
	
	public UpdateFreeSeatsService()
	{
		super(null);
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		this.freeSeats = intent.getIntExtra("freeSeats", -1);
		this.deviceID = intent.getStringExtra("deviceID");
		
		
		final HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://sharetaxi6.appspot.com/updCabOccupation");
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("freeSeats", String.valueOf(freeSeats)));
		pairs.add(new BasicNameValuePair("deviceID", this.deviceID));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(pairs));
			httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		} catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
