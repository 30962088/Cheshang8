package com.cheshang8.app.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;



import com.cheshang8.app.adapter.TypeListAdapter.Model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class CategoriesRequest extends BaseClient{

	public static class Result{
		private String id;
		private String name;
		
		public Model toModel(){
			return new Model(id, name);
		}
		
		public static List<Model> toList(List<Result> results){
			List<Model> list = new ArrayList<Model>();
			for(Result result : results){
				list.add(result.toModel());
			}
			return list;
		}
	}
	
	
	private String pid;
	
	
	public CategoriesRequest(String pid) {
		super();
		this.pid = pid;
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
		RequestParams params = new RequestParams();
		params.add("pid",pid);
		params.add("city_id", "1");
		return params;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/categories.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
