package com.cheshang8.app.network;

import java.io.Serializable;

public class Service implements Serializable{
	private String id;
	
	private String name;
	
	private String detail;
	
	private int price_discount;
	
	private int price_origin;
	
	private float env_rating;
	
	private float rating;
	
	private String description;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice_discount() {
		return price_discount;
	}

	public int getPrice_origin() {
		return price_origin;
	}

	public float getEnv_rating() {
		return env_rating;
	}

	public float getRating() {
		return rating;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDetail() {
		return detail;
	}
}
