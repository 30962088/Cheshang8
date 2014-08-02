package com.cheshang8.app.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.cheshang8.app.App;
import com.cheshang8.app.adapter.OrderAdapter;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.OrdersRequest;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.cheshang8.app.utils.Preferences.Global;
import com.google.gson.Gson;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="order_field")  
public class OrderField {
	@DatabaseField(id=true)
	private String id;
	@DatabaseField
	private String data;
	@DatabaseField
	private long createTime;
	

	
	public OrderField() {
		
	}
	
	public OrderField(String id,long createTime, Result result) {
		this();
		this.id = id;
		this.createTime = createTime;
		this.data = new Gson().toJson(result);
	}



	public Result getOrder(){
		return new Gson().fromJson(data, Result.class);
	}
	
	public static interface Callback{
		public void callback(List<Result> list);
	}
	public static interface Callback2{
		public void callback2(Result result);
	}
	
	public static void getOrders(final Callback callback){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		OrdersRequest request = new OrdersRequest();
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				List<Result> list = (List<Result>) object;
				for(Result result : list){
					try {
						helper.getOrderDao().create(new OrderField(result.getOrder().getNo(),result.getOrder().getDate(), result));
						callback.callback(list);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				callback.callback(list);
			}
		});
	}
	
	public static void create(Result result){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		try {
			helper.getOrderDao().create(new OrderField(result.getOrder().getNo(),result.getOrder().getDate(), result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(Result result){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		try {
			helper.getOrderDao().update(new OrderField(result.getOrder().getNo(),result.getOrder().getDate(), result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getList(final Callback callback){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		Global global = new Global(App.getInstance());
		if(!global.isSetOrders()){
			getOrders(callback);
			global.setOrders(true);
		}else{
			try {
				List<OrderField> fields = helper.getOrderDao().queryBuilder().orderBy("createTime", false).query();
				List<Result> results = new ArrayList<OrdersRequest.Result>();
				for(OrderField field : fields){
					results.add(field.getOrder());
				}
				callback.callback(results);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void _getOrder(String no,Callback2 callback2){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		try {
			callback2.callback2(helper.getOrderDao().queryForId(no).getOrder());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteOrder(String no){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		try {
			helper.getOrderDao().deleteById(no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static interface Callback3{
		public void callback3( Map<Status, Integer>  map);
	}
	public static void getCount(final Callback3 callback3){
		
		getList(new Callback() {
			
			@Override
			public void callback(List<Result> list) {
				Map<Status, Integer> map = new HashMap<Status, Integer>();
				for(Result result : list){
					Integer integer = map.get(result.getOrder().getStatusModel());
					if(integer == null){
						integer = 0;
						
					}
					if(!(result.getOrder().getStatusModel()==Status.已完成 && result.getCommented() == 1)){
						integer++;
					}
					
					map.put(result.getOrder().getStatusModel(), integer);
				}
				callback3.callback3(map);
			}
		});
	}
	
	public static void getOrder(final String no,final Callback2 callback2){
		
		Global global = new Global(App.getInstance());
		if(!global.isSetOrders()){
			getOrders(new Callback() {
				
				@Override
				public void callback(List<Result> list) {
					_getOrder(no,callback2);
					
				}
			});
			global.setOrders(true);
		}else{
			_getOrder(no,callback2);
		}
	}
	
}
