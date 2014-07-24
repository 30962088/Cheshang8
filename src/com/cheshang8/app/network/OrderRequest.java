package com.cheshang8.app.network;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;


import com.cheshang8.app.OrderActivity;
import com.cheshang8.app.ServiceActivity;
import com.cheshang8.app.adapter.DetailServiceAdapter;
import com.cheshang8.app.adapter.DetailServiceAdapter.Model.Col;
import com.cheshang8.app.fragment.DetailMainFragment;
import com.cheshang8.app.network.CityListRequest.Result;
import com.cheshang8.app.network.Order.Payment;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

public class OrderRequest extends BaseClient{

	public static class Result{
		
		
		
		private Shop shop;
		
		private Order order;
		
		
		
		private Payment payment;
		
		public OrderActivity.Model toModel(){
			Service service = order.getService();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			return new OrderActivity.Model(order.getDateString(), order.getNo(),order.getConsume_no(), order.getStatusModel(), 
					shop.getLogo(), shop.getShop_name(), shop.getShop_address(), shop.getPhoneText(), service.getName(),
					service.getDetail(), service.getPrice_origin(), service.getPrice_discount(), service.getPrice_origin()-service.getPrice_discount(), 
					15, 15,15, "淘宝","百度");
		}
		
		
	}
	
	
	
	private String id;
	
	
	public OrderRequest(String id) {
		this.id = id;

	}

	@Override
	public Object onSuccess(String str) {
		return new Gson().fromJson(str,Result.class);
		
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
		params.add("id", id);
		params.add("shop_id", "1");
		return params;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return "/data/order.php";
	}

	@Override
	protected Method getMethod() {
		// TODO Auto-generated method stub
		return Method.GET;
	}
}
