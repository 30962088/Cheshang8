package com.cheshang8.app.utils;



import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

	
	public static class Global{
		
		private static final String NAME = "Global";
		
		private SharedPreferences preferences;

		private Context context;
		
		public Global(Context context) {
			preferences = context.getSharedPreferences(NAME, 0);
			this.context = context;
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
		
	
		
		
		
	}
	
	

}
