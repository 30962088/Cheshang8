package com.cheshang8.app;

import java.util.Date;
import java.util.Random;

import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.database.OrderField;
import com.cheshang8.app.network.Order;
import com.cheshang8.app.network.OrdersRequest;
import com.cheshang8.app.network.ServiceRequest;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.ServiceRequest.Result;
import com.cheshang8.app.utils.Preferences;
import com.cheshang8.app.utils.Preferences.User;
import com.cheshang8.app.widget.CarStarView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ServiceActivity extends BaseActivity implements OnClickListener{
	
	
	private ViewHolder holder;
	
	public static void open(Context context,String id,String shop_id){
		Intent intent = new Intent(context, ServiceActivity.class);
		intent.putExtra("id", id);
		intent.putExtra("shop_id", shop_id);
		context.startActivity(intent);
	}
	
	private Context context;
	
	private String id;
	
	private String shop_id;
	
	private Result result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		id = getIntent().getStringExtra("id");
		shop_id = getIntent().getStringExtra("shop_id");
		setContentView(R.layout.service_layout);
		holder = new ViewHolder();
		findViewById(R.id.pay_btn).setOnClickListener(this);
		findViewById(R.id.comment_btn).setOnClickListener(this);
		request();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pay_btn:
			onPay();
			break;
		case R.id.comment_btn:
			User user = new Preferences.Global(context).getUser();
			if(user == null){
				LoginActivity.open(context);
			}else{
				CommentListActivity.open(this,shop_id);
			}
			
			break;
		default:
			break;
		}
		
	}
	
	private void onPay(){
		User user = new Preferences.Global(context).getUser();
		if(user == null){
			LoginActivity.open(context);
		}else{
			OrdersRequest.Result rs = new OrdersRequest.Result();
			rs.setCommented(0);
			result.getService().setDescription("");
			rs.setService(result.getService());
			rs.setShop(result.getShop());
			Order order = new Order();
			order.setDate(new Date().getTime());
			order.setNo(""+generateRandom(10));
			order.setConsume_no(""+generateRandom(12));
			order.setStatus(Status.待支付);
			rs.setOrder(order);
			SubmitActivity.open(context, rs);
		}
		
	}
	
	public static long generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}
	
	private void request(){
		ServiceRequest request = new ServiceRequest(id, shop_id);
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				result = (Result) object;
				holder.setModel(result.toModel());
			}
		});
	}
	
	public static class Model{
		private String name;
		private int price_origin;
		private int price_discount;
		private float env_rating;
		private float rating;
		private String title;
		private String address;
		private String time;
		private String phone;
		private String detail;
		public Model(String name, int price_origin, int price_discount,
				float env_rating, float rating, String title, String address,
				String time, String phone, String detail) {
			super();
			this.name = name;
			this.price_origin = price_origin;
			this.price_discount = price_discount;
			this.env_rating = env_rating;
			this.rating = rating;
			this.title = title;
			this.address = address;
			this.time = time;
			this.phone = phone;
			this.detail = detail;
		}
		
	}
	
	private class ViewHolder{
		private TextView name;
		
		private TextView price_origin;
		
		private TextView price_discount;
		
		private TextView price_reduce;
		
		private CarStarView env_rating;
		
		private CarStarView rating;
		
		private TextView title;
		
		private TextView address;
		
		private TextView time;
		
		private TextView phone;
		
		private TextView detail;
		public ViewHolder() {
			name = (TextView) findViewById(R.id.name);
			price_origin = (TextView) findViewById(R.id.price_origin);
			price_discount = (TextView) findViewById(R.id.price_discount);
			price_reduce = (TextView) findViewById(R.id.price_reduce);
			env_rating = (CarStarView) findViewById(R.id.env_rating);
			rating = (CarStarView) findViewById(R.id.rating);
			title = (TextView) findViewById(R.id.title);
			address = (TextView) findViewById(R.id.address);
			time = (TextView) findViewById(R.id.time);
			phone = (TextView) findViewById(R.id.phone);
			detail = (TextView) findViewById(R.id.detail);			
		}
		public void setModel(Model model){
			name.setText("["+model.name+"]");
			price_origin.setText(""+model.price_discount);
			price_discount.setText("原价"+model.price_origin);
			price_reduce.setText("节省"+(model.price_origin-model.price_discount));
			env_rating.setStar(model.env_rating);
			rating.setStar(model.rating);
			title.setText(model.title);
			address.setText("地址:"+model.address);
			time.setText("营业时间:"+model.time);
			phone.setText("预约电话:"+model.phone);
			detail.setText(model.detail);
		}
	}

	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}


}
