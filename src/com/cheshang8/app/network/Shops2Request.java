package com.cheshang8.app.network;


import java.util.List;

import org.apache.http.Header;



import com.cheshang8.app.network.ShopsRequest.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class Shops2Request extends BaseClient{
	
	private double lon;
	
	
	private double lat;
	
	
	
	
	public Shops2Request(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}


	@Override
	public Object onSuccess(String str) {
		return new Gson().fromJson(str,
				new TypeToken<List<Result>>() {
				}.getType());
		
	}
	
	@Override
	public void onError(int error) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onServerError(int arg0, Header[] arg1, byte[] arg2,
			Throwable arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected RequestParams getParams() {
		RequestParams rp = new RequestParams();
		rp.add("lon", ""+lon);
		rp.add("lat", ""+lat);
		return rp;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/shops2.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
