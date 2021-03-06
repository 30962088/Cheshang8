package com.cheshang8.app.utils;



import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

	public static class User{
		private String phone;
		private String name;
		private String level;
		private String coin;
		public User(String name,String phone, String level, String coin) {
			super();
			this.name = name;
			this.level = level;
			this.coin = coin;
			this.phone = phone;
		}
		public String getCoin() {
			return coin;
		}
		public String getLevel() {
			return level;
		}
		public String getName() {
			return name;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPhone() {
			return phone;
		}
		
	}
	public static class Global{
		
		private static final String NAME = "Global";
		
		private SharedPreferences preferences;

		private Context context;
		
		
		
		public Global(Context context) {
			preferences = context.getSharedPreferences(NAME, 0);
			this.context = context;
		}
		
		public void setUser(User user){
			preferences.edit().putString("user", new Gson().toJson(user)).commit();
		}
		
		public User getUser(){
			String str = preferences.getString("user",null);
			if(str == null){
				return null;
			}
			return new Gson().fromJson(str, User.class);
		}
		
		public void setCity(String id,String name){
			setCityId(id);
			setCityName(name);
		}
		
		public void setCityId(String id){
			preferences.edit().putString("city_id", id).commit();
		}
		
		public String getCityId(){
			return preferences.getString("city_id","1");
		}
		
		public void setCityName(String name){
			preferences.edit().putString("city_name", name).commit();
		}
		
		public String getCityName(){
			return preferences.getString("city_name","北京");
		}
		
		public void setFirst(boolean val){
			preferences.edit().putBoolean("first", val).commit();
		}
		
		public boolean isFirst(){
			return preferences.getBoolean("first", true);
		}
		
		public void setOrders(boolean val){
			preferences.edit().putBoolean("set_order", val).commit();
		}
		
		public boolean isSetOrders(){
			return preferences.getBoolean("set_order", false);
		}
		
	
		
		
		
	}
	
	

}
