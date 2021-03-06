package com.cheshang8.app.network;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;


import com.cheshang8.app.adapter.DetailCommentAdapter.Model;
import com.cheshang8.app.adapter.SelectCityAdapter.Model.Col;
import com.cheshang8.app.database.CommentField;
import com.cheshang8.app.network.CityListRequest.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class CommentsRequest extends BaseClient{

	public static class Result{
		
		public static class Comment{
			public static class User{
				private String phone;
				public User() {
					// TODO Auto-generated constructor stub
				}
				public User(String phone) {
					super();
					this.phone = phone;
				}
				
			}
			public static class Service implements Serializable{
				private String id;
				private String name;
				public Service(String id, String name) {
					super();
					this.id = id;
					this.name = name;
				}
				public Service() {
					// TODO Auto-generated constructor stub
				}
			}
			private Service service;
			private float rating;
			private long date;
			private User user;
			private String content;
			private List<String> imgs = new ArrayList<String>();
			
			public Comment(Service service, float rating, long date, User user,
					String content, List<String> imgs) {
				super();
				this.service = service;
				this.rating = rating;
				this.date = date;
				this.user = user;
				this.content = content;
				this.imgs = imgs;
			}
			
			public Comment() {
				// TODO Auto-generated constructor stub
			}
			public Model toModel(){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String time = format.format(new Date(date)); 
				return new Model("assets://images/ii_1.png", service.name, rating, user.phone, time, content, imgs);
			}
		}
		private int rating;
		private int count;
		private List<Comment> list = new ArrayList<CommentsRequest.Result.Comment>();
		
		public static List<Model> toList(List<Comment> results){
			List<Model> list = new ArrayList<Model>();
			for(Comment result : results){
				list.add(result.toModel());
			}
			return list;
		}
		
		public List<Comment> getList() {
			return list;
		}
		public int getRating() {
			return rating;
		}
		public int getCount() {
			return count;
		}
		
	}
	
	private String shop_id;
	
	
	
	
	
	public CommentsRequest(String shop_id) {
		super();
		this.shop_id = shop_id;
	}

	@Override
	public Object onSuccess(String str) {
		Result result = new Gson().fromJson(str,Result.class);
 		
		for(CommentField field: CommentField.getComment(shop_id)){
			result.list.add(0, field.getComment());
		}
		return result;
		
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
		params.add("shop_id", shop_id);
		return params;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/comments.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
