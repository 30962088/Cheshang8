package com.cheshang8.app.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;


import com.cheshang8.app.adapter.DetailServiceAdapter;
import com.cheshang8.app.adapter.DetailServiceAdapter.Model.Col;
import com.cheshang8.app.fragment.DetailMainFragment;
import com.cheshang8.app.network.CityListRequest.Result;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

public class ShopRequest extends BaseClient{

	public static class Result{
		
		
		public static class Service{
			
			public static class Sub{
				private String id;
				
				private String name;
				
				private int price;
				
				public Col toCol(){
					return new Col(id,name, price);
				}
				
			}
			
			private int id;
			
			private String name;
			
			private String icon;
			
			private List<Sub> subs = new ArrayList<ShopRequest.Result.Service.Sub>();

			public DetailServiceAdapter.Model toModel(){
				
				List<Col> cols = new ArrayList<DetailServiceAdapter.Model.Col>();
				
				for(Sub sub : subs){
					cols.add(sub.toCol());
				}
				
				DetailServiceAdapter.Model model = new DetailServiceAdapter.Model(name, icon, cols);
				
				return model;
				
			}
			
			public static List<DetailServiceAdapter.Model> toList(List<Service> results){
				List<DetailServiceAdapter.Model> list = new ArrayList<DetailServiceAdapter.Model>();
				for(Service result : results){
					list.add(result.toModel());
				}
				return list;
			}
			
		}
		
		private Shop shop;
		
		private List<Service> services;
		
		public List<Service> getServices() {
			return services;
		}
		public Shop getShop() {
			return shop;
		}
		
	}
	
	
	
	private String id;
	
	private String lat;
	
	private String lon;
	
	public ShopRequest(String id) {
		super();
		this.id = id;
	}
	
	

	public ShopRequest(String id, String lat, String lon) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
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
		
		return params;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/shop.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
