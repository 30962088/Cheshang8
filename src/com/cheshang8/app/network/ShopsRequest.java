package com.cheshang8.app.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;


import com.cheshang8.app.MapActivity;
import com.cheshang8.app.adapter.SearchItemAdapter;
import com.cheshang8.app.adapter.SelectCityAdapter.Model.Col;
import com.cheshang8.app.network.CityListRequest.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class ShopsRequest extends BaseClient{

	public static class Result{
		private String logo;
		private String id;
		private float rating;
		private String distance;
		private int price_origin;
		private int price_discount;
		private int comment_count;
		private double longitude;
		private double latitude;
		private String shop_name;
		private String shop_address;
		private List<String> shop_phones;
		public String getLogo() {
			return logo;
		}
		public String getId() {
			return id;
		}
		public float getRating() {
			return rating;
		}
		public String getDistance() {
			return distance;
		}
		public int getPrice_origin() {
			return price_origin;
		}
		public int getPrice_discount() {
			return price_discount;
		}
		public int getComment_count() {
			return comment_count;
		}
		public String getShop_name() {
			return shop_name;
		}
		public String getShop_address() {
			return shop_address;
		}
		public List<String> getShop_phones() {
			return shop_phones;
		}
		public SearchItemAdapter.Model toModel(String type){
			return new SearchItemAdapter.Model(id, logo, rating, shop_name, "地址:"+shop_address, type, price_discount, price_origin, comment_count, distance);
		}
		
		public MapActivity.Model toMapModel(){
			return new MapActivity.Model(longitude, latitude, shop_name, rating, price_discount);
		}
		
		public static ArrayList<MapActivity.Model> toMapList(List<Result> results){
			ArrayList<MapActivity.Model> list = new ArrayList<MapActivity.Model>();
			for(Result result : results){
				list.add(result.toMapModel());
			}
			return list;
		}
		
		public static List<SearchItemAdapter.Model> toList(List<Result> results,String type){
			List<SearchItemAdapter.Model> list = new ArrayList<SearchItemAdapter.Model>();
			for(Result result : results){
				list.add(result.toModel(type));
			}
			return list;
		}
	}
	
	public static class Params{
		
		public enum Sort{
			DEFAULT(1),PRICE(2),STAR(3);
			private int type;
			private Sort(int type) {
				this.type = type;
			}
			public int getType() {
				return type;
			}
		}
		
		private int city_id;
		private Integer dist1;
		private Integer dist2;
		private Sort sort = Sort.DEFAULT;
		public Params(int city_id, Integer dist1, Integer dist2, Sort sort) {
			super();
			this.city_id = city_id;
			this.dist1 = dist1;
			this.dist2 = dist2;
			this.sort = sort;
		}
		public Params(int city_id) {
			super();
			this.city_id = city_id;
		}
		
		
		
	}
	
	
	
	private Params params;
	
	
	
	public ShopsRequest(Params params) {
		super();
		this.params = params;
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
		rp.add("city_id", ""+params.city_id);
		if(params.dist1 != null){
			rp.add("dist1", ""+params.dist1);
		}
		if(params.dist2 != null){
			rp.add("dist2", ""+params.dist2);
		}
		rp.add("sort", ""+params.sort.getType());
		
		return rp;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/shops.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
