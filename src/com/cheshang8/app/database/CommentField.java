package com.cheshang8.app.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.cheshang8.app.App;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.CommentsRequest.Result.Comment;
import com.cheshang8.app.network.OrdersRequest;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.cheshang8.app.utils.Preferences.Global;
import com.google.gson.Gson;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="comment_field")  
public class CommentField {
	@DatabaseField(id=true)
	private String id;
	@DatabaseField
	private String shop_id;
	@DatabaseField
	private String data;
	

	
	public CommentField() {
		
	}
	
	



	public CommentField(String shop_id, Comment comment) {
		super();
		id = ""+new Date().getTime();
		this.shop_id = shop_id;
		this.data = new Gson().toJson(comment);
	}





	public Comment getComment(){
		return new Gson().fromJson(data, Comment.class);
	}
	
	public static interface Callback{
		public void callback(List<Result> list);
	}
	public static interface Callback2{
		public void callback2(Result result);
	}
	
	public static List<CommentField> getComment(String shop_id){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		List<CommentField> list = new ArrayList<CommentField>();
		try {
//			list = helper.getCommentDao().queryBuilder().where().eq("shop_id", shop_id).query();
			list = helper.getCommentDao().queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public static void saveOrUpdate(CommentField field){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		try {
			helper.getCommentDao().createOrUpdate(field);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
