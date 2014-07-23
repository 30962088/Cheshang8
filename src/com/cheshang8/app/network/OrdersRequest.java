package com.cheshang8.app.network;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;



import com.cheshang8.app.adapter.OrderAdapter;
import com.cheshang8.app.adapter.SelectCityAdapter.Model.Col;
import com.cheshang8.app.network.CityListRequest.Result;
import com.cheshang8.app.network.Order.Payment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class OrdersRequest extends BaseClient{

	public static class Result{
		
		
		
		private Shop shop;
		
		private Order order;
		
		private Service service;
		
		private Payment payment;
		
		private int commented;
		
		
		public OrderAdapter.Model toModel(){
			int price = 0;
			if(payment != null){
				price = payment.getPay();
			}else{
				price = service.getPrice_discount();
			}
			return new OrderAdapter.Model(shop.getLogo(), order.getDateString(), shop.getShop_name(), service.getName(), price, order.getNo(), order.getStatusModel());
		}
		
		public static List<OrderAdapter.Model> toList(List<Result> results){
			List<OrderAdapter.Model> list = new ArrayList<OrderAdapter.Model>();
			for(Result result : results){
				list.add(result.toModel());
			}
			return list;
		}
		
	}
	
	
	
	
	
	private String shop_id = "1";
	
	private Integer status;
	
	


	


	public OrdersRequest(Integer status) {
		super();
		this.status = status;
	}
	
	public OrdersRequest() {
		// TODO Auto-generated constructor stub
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
		if(status != null){
			params.add("status", ""+status);
		}
		
		
		params.add("shop_id", shop_id);
		return params;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/orders.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
