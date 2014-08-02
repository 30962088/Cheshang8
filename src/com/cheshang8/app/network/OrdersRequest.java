package com.cheshang8.app.network;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;



import com.cheshang8.app.OrderActivity;
import com.cheshang8.app.PayActivity;
import com.cheshang8.app.PaySuccessActivity;
import com.cheshang8.app.SubmitActivity;
import com.cheshang8.app.OrderActivity.Model.Pay;
import com.cheshang8.app.adapter.OrderAdapter;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.adapter.SelectCityAdapter.Model.Col;
import com.cheshang8.app.network.CityListRequest.Result;
import com.cheshang8.app.network.Order.Payment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

public class OrdersRequest extends BaseClient{

	public static class Result implements Serializable{
		
		
		
		private Shop shop;
		
		private Order order;
		
		private Service service;
		
		private Payment payment;
		
		private int commented;
		
		public Shop getShop() {
			return shop;
		}
		
		public int getCommented() {
			return commented;
		}
		
		public Order getOrder() {
			return order;
		}
		
		public PaySuccessActivity.Model.Header toHeader(){
			return new PaySuccessActivity.Model.Header(service.getName(), service.getPrice_discount(), order.getConsume_no());
		}
		
		public PayActivity.Model toPayModel(){
			return new PayActivity.Model(service.getName(), service.getDescription(), service.getPrice_discount(), service.getPrice_origin(), 1,1);
		}
		
		public SubmitActivity.Model toSubmitModel(){
			return new SubmitActivity.Model(shop.getLogo(), service.getName(), service.getDescription(), 
					service.getPrice_discount(), service.getPrice_origin(), shop.getShop_name(), shop.getShop_address(), shop.getPhoneText(), "158****1121");
		}
		
		public void setCommented(int commented) {
			this.commented = commented;
		}
		public void setOrder(Order order) {
			this.order = order;
		}
		public void setPayment(Payment payment) {
			this.payment = payment;
		}
		public void setService(Service service) {
			this.service = service;
		}
		public void setShop(Shop shop) {
			this.shop = shop;
		}
		
		public OrderActivity.Model toOrderModel(){
			Pay pay = null;
			if(payment != null){
				pay = payment.toModel();
			}
			Status st = order.getStatusModel();
			if(st == Status.已完成 && commented == 1){
				st = Status.已评价;
			}
			return new OrderActivity.Model(order.getDateString(), order.getNo(), order.getConsume_no(), st, shop.getLogo(), shop.getRating(), 
					shop.getShop_name(), shop.getShop_address(), shop.getPhoneText(), service.getName(), service.getDetail(), 
					service.getPrice_discount(), service.getPrice_origin(), service.getPrice_origin()-service.getPrice_discount(), pay);
		}
		
		public OrderAdapter.Model toModel(){
			int price = 0;
			if(payment != null){
				price = payment.getPay();
			}else{
				price = service.getPrice_discount();
			}
			Status st = order.getStatusModel();
			if(st == Status.已完成 && commented == 1){
				st = Status.已评价;
			}
			return new OrderAdapter.Model(shop.getId(), shop.getLogo(), order.getDateString(), shop.getShop_name(), service.getName(), price, order.getNo(),st );
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
