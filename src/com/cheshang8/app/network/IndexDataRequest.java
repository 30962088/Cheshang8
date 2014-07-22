package com.cheshang8.app.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;


import com.cheshang8.app.adapter.CatIndexAdapter;
import com.cheshang8.app.adapter.SelectCityAdapter.Model.Col;
import com.cheshang8.app.adapter.TabIndexAdapter;
import com.cheshang8.app.fragment.SliderFragment;
import com.cheshang8.app.fragment.SliderFragment.Model;
import com.cheshang8.app.network.CityListRequest.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class IndexDataRequest extends BaseClient{

	public static class Result{
		
		public static class Banner{
			private String img;
			private String target_page;
			private String target_id;
			public SliderFragment.Model toModel(){
				return new SliderFragment.Model(target_id, img, target_page);
			}
			
			public static List<SliderFragment.Model> toList(List<Banner> results){
				List<SliderFragment.Model> list = new ArrayList<SliderFragment.Model>();
				for(Banner result : results){
					list.add(result.toModel());
				}
				return list;
			}
		}
		
		public static class Category{
			private int id;
			private String name;
			private int pid;
			public CatIndexAdapter.Model toModel(){
				return new CatIndexAdapter.Model(name);
			}
			public static List<CatIndexAdapter.Model> toList(List<Category> results){
				List<CatIndexAdapter.Model> list = new ArrayList<CatIndexAdapter.Model>();
				for(Category result : results){
					list.add(result.toModel());
				}
				return list;
			}
		}
		
		public static class Promotion{
			private String img;
			private String target_page;
			private String target_id;
		}
		
		public static class Recommend{
			public static class Shop{
				private String logo;
				private float rating;
				private String distance;
				private String shop_name;
				private String shop_address;
				private List<String> shop_phones;
			}
			private String target_page;
			private String target_id;
			private String title;
			private String description;
			private Shop shop;
			public TabIndexAdapter.Model toModel(){
				
				return new TabIndexAdapter.Model(null, shop.logo, (int)shop.rating, title, description, shop.shop_name, shop.shop_address,shop.distance);
			}
			public static List<TabIndexAdapter.Model> toList(List<Recommend> results){
				List<TabIndexAdapter.Model> list = new ArrayList<TabIndexAdapter.Model>();
				for(Recommend result : results){
					list.add(result.toModel());
				}
				return list;
			}
			
			
		}
		
		private List<Banner> banners;
		
		private List<Category> categories;
		
		private List<Promotion> promotions;
		
		private List<Recommend> recommends;
		
		public List<Banner> getBanners() {
			return banners;
		}
		public List<Category> getCategories() {
			return categories;
		}
		public List<Recommend> getRecommends() {
			return recommends;
		}
		
		
	}
	
	public static class Params{
		private int city_id;

		public Params(int city_id) {
			super();
			this.city_id = city_id;
		}
		
	}
	
	private Params params;
	
	
	
	public IndexDataRequest(Params params) {
		super();
		this.params = params;
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
		params.add("city_id", ""+this.params.city_id);
		return params;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/home.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
