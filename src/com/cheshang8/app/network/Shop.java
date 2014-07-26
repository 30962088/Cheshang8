package com.cheshang8.app.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cheshang8.app.fragment.DetailMainFragment;
import com.cheshang8.app.fragment.SliderFragment;

public class Shop {
	public static class Category {
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
	
	

	public List<String> getImgs() {
		return imgs;
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

	public String getShop_name() {
		return shop_name;
	}

	public String getShop_address() {
		return shop_address;
	}

	public List<String> getShop_phones() {
		return shop_phones;
	}

	public String getTime() {
		return time;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public String getDescription() {
		return description;
	}

	public DetailMainFragment.Model toModel() {

		String range = "";

		for (Category category : categories) {
			range += category.name + " ";
		}

		return new DetailMainFragment.Model(shop_name, shop_address, time,
				StringUtils.join(shop_phones," "), range, description);
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
	
	public String getPhoneText(){
		return StringUtils.join(shop_phones, " ");
	}
	
	public List<SliderFragment.Model> toSliderModel(){
		List<SliderFragment.Model> models = new ArrayList<SliderFragment.Model>();
		for(String img : imgs){
			models.add(new SliderFragment.Model(null, img, null));
		}
		return models;
		
	}
	
}
