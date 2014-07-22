package com.cheshang8.app.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;



import com.cheshang8.app.widget.CatTwoDialog;
import com.cheshang8.app.widget.CatTwoDialog.Model;
import com.cheshang8.app.widget.CatTwoDialog.Model.Col;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class DistrictsRequest extends BaseClient{

	public static class Result{
		private int id;
		private String name;
		private int count;
		private int pid;
		
		public static List<CatTwoDialog.Model> toList(List<Result> results){
			
			List<Model> list = new ArrayList<CatTwoDialog.Model>();
			
			for(Result result : results){
				if(result.pid == 0){
					CatTwoDialog.Model model = new CatTwoDialog.Model();
					model.setId(result.id);
					model.setCount(result.count);
					model.setName(result.name);
					model.setList(new ArrayList<CatTwoDialog.Model.Col>());
					list.add(model);
				}else{
					for(Model model : list){
						if(model.getId() == result.pid){
							Col col = new Col(result.id,result.name, result.count);
							model.getList().add(col);
							break;
						}
					}
				}
			}
			return list;
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
	
	
	
	public DistrictsRequest(Params params) {
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
		RequestParams params = new RequestParams();
		params.add("city_id", ""+this.params.city_id);
		return params;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/districts.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
