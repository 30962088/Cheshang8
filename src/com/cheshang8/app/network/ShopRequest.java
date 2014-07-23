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
			
			private String id;
			
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
			
			public DetailMainFragment.Model toModel(){
				
				String range = "";
				
				for(Category category : categories){
					 range += category.name+" ";
				}
				
				return new DetailMainFragment.Model(shop_name, shop_address, time, StringUtils.join(shop_phones),range , description);
			}
			public String getLogo() {
				return logo;
			}
			public int getServices_count() {
				return services_count;
			}
			public int getComment_count() {
				return comment_count;
			}
			public int getProducts_count() {
				return products_count;
			}
			public String getId() {
				return id;
			}
		}
		
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
			
			private List<Sub> subs = new ArrayList<ShopRequest.Result.Service.Sub>();

			public DetailServiceAdapter.Model toModel(){
				
				List<Col> cols = new ArrayList<DetailServiceAdapter.Model.Col>();
				
				for(Sub sub : subs){
					cols.add(sub.toCol());
				}
				
				DetailServiceAdapter.Model model = new DetailServiceAdapter.Model(name, "assets://images/is_2.png", cols);
				
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
	
	
	
	public ShopRequest(String id) {
		super();
		this.id = id;
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
