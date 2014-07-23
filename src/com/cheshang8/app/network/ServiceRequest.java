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
		
		public static class Shop{
			
			public static class Category{
				private int id;
				private String name;
				public int getId() {
					return id;
				}
				public String getName() {
					return name;
				}
				
			}
			
			private int id;
			
			private int services_count;
			
			private int products_count;
			
			private int comment_count;
			
			private String logo;
			
			private List<String> imgs;
			
			private float rating;
			
			private String distance;
			
			private int price_origin;
			
			private int price_discount;
			
			private String shop_name;
			
			private String shop_address;
			
			private List<String> shop_phones;
			
			private String time;
			
			private double latitude;
			
			private double longitude;
			
			private List<Category> categories;
			
			private String description;
			
			
		}
		
		public static class Service{
			
			private String id;
			
			private String name;
			
			private int price_discount;
			
			private int price_origin;
			
			private int env_rating;
			
			private int rating;
			
			private String description;
			
			
			
		}
		
		private Shop shop;
		
		private Service service;
		
		public ServiceActivity.Model toModel(){
			return new ServiceActivity.Model(service.name, service.price_origin, service.price_discount, service.env_rating, service.rating, 
					shop.shop_name, shop.shop_address, shop.time, StringUtils.join(shop.shop_phones," "), shop.description);
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
