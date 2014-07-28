package com.cheshang8.app;

import com.cheshang8.app.OrderActivity.Model.Pay;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.database.OrderField;
import com.cheshang8.app.database.OrderField.Callback2;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.cheshang8.app.utils.BitmapLoader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.mengle.lib.utils.ConfirmDialog;

public class OrderActivity extends BaseActivity implements OnClickListener{
	public static void open(Context context,String id){
		Intent intent = new Intent(context, OrderActivity.class);
		intent.putExtra("id", id);
		context.startActivity(intent);
	}
	
	private Context context;
	
	private String id;
	
	private ViewHolder holder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getStringExtra("id");
		context = this;
		setContentView(R.layout.order_layout);
		holder = new ViewHolder();
		findViewById(R.id.right_nav_btn).setOnClickListener(this);
		findViewById(R.id.pay_btn).setOnClickListener(this);
		request();
	}
	private Result result;
	
	private Model model;
	
	private void request() {
		OrderField.getOrder(id, new Callback2() {
			
			@Override
			public void callback2(Result result) {
				OrderActivity.this.result = result;
				model = result.toOrderModel();
				holder.setModel(model);
				
			}
		});
	
		
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.pay_btn:
			if(model.status == Status.待支付){
				SubmitActivity.open(this,result.toSubmitModel());
			}else if(model.status == Status.已完成){
				PublishCommentActivity.open(this);
			}else if(model.status == Status.待体验){
				//状态修改成退款完成
				ConfirmDialog.open(this, "确认", "是否要退款？", new ConfirmDialog.OnClickListener() {
					
					@Override
					public void onPositiveClick() {
						result.getOrder().setStatus(Status.退款中);
						model = result.toOrderModel();
						holder.setModel(model);
						OrderField.update(result);
						
					}
					@Override
					public void onNegativeClick() {
					}
				});
				
			}
			
			break;
		case R.id.right_nav_btn:
			ConfirmDialog.open(this, "确认", "是否要取消订单？", new ConfirmDialog.OnClickListener() {
				
				@Override
				public void onPositiveClick() {
					OrderField.deleteOrder(id);
					finish();
					
				}
				
				@Override
				public void onNegativeClick() {
					
					
				}
			});
			break;
		default:
			break;
		}
		
	}
	
	
	private class ViewHolder{
		private View rightBtn;
		private TextView time;
		private TextView no;
		private TextView consume_no;
		private TextView status;
		private TextView pay_btn;
		private ImageView thumbnail; 
		private TextView title;
		private TextView address;
		private TextView phone;
		private TextView service;
		private TextView service_detail;
		private View pay_container;
		private TextView price;
		private TextView price0;
		private TextView price1;
		private TextView price2;
		private TextView price3;
		private TextView pay_from;
		private TextView fapiao;
		public ViewHolder() {
			rightBtn = findViewById(R.id.right_nav_btn);
			time = (TextView) findViewById(R.id.time);
			no = (TextView) findViewById(R.id.no);
			consume_no = (TextView) findViewById(R.id.consume_no);
			status = (TextView) findViewById(R.id.status);
			pay_btn = (TextView) findViewById(R.id.pay_btn);
			thumbnail = (ImageView) findViewById(R.id.thumbnail);
			title = (TextView) findViewById(R.id.title);
			address = (TextView) findViewById(R.id.address);
			phone = (TextView) findViewById(R.id.phone);
			service = (TextView) findViewById(R.id.service);
			service_detail = (TextView) findViewById(R.id.service_detail);
			price = (TextView) findViewById(R.id.price);
			price0 = (TextView) findViewById(R.id.price0);
			price1 = (TextView) findViewById(R.id.price1);
			price2 = (TextView) findViewById(R.id.price2);
			price3 = (TextView) findViewById(R.id.price3);
			pay_from = (TextView) findViewById(R.id.pay_from);
			fapiao = (TextView) findViewById(R.id.fapiao);
			pay_container = findViewById(R.id.pay_container);
		}
		public void setModel(Model model){
			if(model.status == Status.待支付){
				rightBtn.setVisibility(View.VISIBLE);
			}else{
				rightBtn.setVisibility(View.GONE);
			}
			BitmapLoader.displayImage(context, model.thumbnail, thumbnail);
			time.setText("订单日期："+model.time);
			no.setText("订单编号："+model.no);
			consume_no.setText("消费编码："+model.consume_no);
			status.setText(model.status.getText());
			status.setTextColor(model.status.getColor());
			pay_btn.setText(model.status.getBtn());
			pay_btn.setBackgroundColor(model.status.getColor());
			title.setText(model.title);
			address.setText("地址："+model.address);
			phone.setText("预约电话："+model.phone);
			service.setText(model.service);
			service_detail.setText(model.service_detail);
			price.setText(""+model.price);
			price0.setText("原价："+model.price0+"      节省："+model.price0_0);
			if(model.pay == null){
				pay_container.setVisibility(View.GONE);
			}else{
				pay_container.setVisibility(View.VISIBLE);
				Pay pay = model.pay;
				price1.setText(""+pay.price1);
				price2.setText(""+pay.price2);
				price3.setText(""+pay.price3);
				pay_from.setText("支付方式："+pay.pay_from);
				fapiao.setText("发票信息："+pay.fapiao);
			}
			
		}
	}
	
	public static class Model{
		
		
		public static class Pay{
			
			private int price1;
			private int price2;
			private int price3;
			private String pay_from;
			private String fapiao;
			public Pay(int price1, int price2, int price3, String pay_from,
					String fapiao) {
				super();
				this.price1 = price1;
				this.price2 = price2;
				this.price3 = price3;
				this.pay_from = pay_from;
				this.fapiao = fapiao;
			}
			
			
		}
		
		private String time;
		private String no;
		private String consume_no;
		private Status status;
		
		private String thumbnail; 
		private float star;
		private String title;
		private String address;
		private String phone;
		private String service;
		private String service_detail;
		private int price;
		private int price0;
		private int price0_0;
		private Pay pay;
		public Model(String time, String no, String consume_no, Status status,
				String thumbnail, float star, String title, String address,
				String phone, String service, String service_detail, int price,
				int price0, int price0_0, Pay pay) {
			super();
			this.time = time;
			this.no = no;
			this.consume_no = consume_no;
			this.status = status;
			this.thumbnail = thumbnail;
			this.star = star;
			this.title = title;
			this.address = address;
			this.phone = phone;
			this.service = service;
			this.service_detail = service_detail;
			this.price = price;
			this.price0 = price0;
			this.price0_0 = price0_0;
			this.pay = pay;
		}
		
		
		
		

		
		
		
		
	}

	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

}
