package com.cheshang8.app.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;


import com.cheshang8.app.ServiceActivity;
import com.cheshang8.app.adapter.DetailServiceAdapter;
import com.cheshang8.app.adapter.DetailServiceAdapter.Model.Col;
import com.cheshang8.app.fragment.DetailMainFragment;
import com.cheshang8.app.network.CityListRequest.Result;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

public class ServiceRequest extends BaseClient{

	public static class Result{
		
		
		
		private Shop shop;
		
		private Service service;
		
		public ServiceActivity.Model toModel(){
			return new ServiceActivity.Model(service.getName(), service.getPrice_origin(), service.getPrice_discount(), service.getEnv_rating(), service.getRating(), 
					shop.getShop_name(), shop.getShop_address(), shop.getTime(), StringUtils.join(shop.getShop_phones()," "), shop.getDescription());
		}
		
		public Service getService() {
			return service;
		}
		public Shop getShop() {
			return shop;
		}
	}
	
	
	
	private String id;
	
	private String shop_id;
	
	public ServiceRequest(String id, String shop_id) {
		this.id = id;
		this.shop_id = shop_id;
	}

	@Override
	public Object onSuccess(String str) {
		return new Gson().fromJson(str,Result.class);
		
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
		RequestParams params = new RequestParams();
		params.add("id", id);
		params.add("shop_id", shop_id);
		return params;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/service.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
