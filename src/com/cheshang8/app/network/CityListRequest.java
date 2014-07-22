package com.cheshang8.app.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;


import com.cheshang8.app.adapter.SelectCityAdapter.Model.Col;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class CityListRequest extends BaseClient{

	public static class Result{
		private int id;
		private String name;
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public Col toCol(){
			return new Col(id,name);
		}
		public static List<Col> toList(List<Result> results){
			List<Col> list = new ArrayList<Col>();
			for(Result result : results){
				list.add(result.toCol());
			}
			return list;
		}
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/city_list.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
