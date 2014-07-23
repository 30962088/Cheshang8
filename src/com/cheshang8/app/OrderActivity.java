package com.cheshang8.app;

import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.fragment.TabIndexFragment;
import com.cheshang8.app.fragment.TabZoneFragment;
import com.cheshang8.app.utils.BitmapLoader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends FragmentActivity implements OnClickListener{
	public static void open(Context context){
		context.startActivity(new Intent(context, OrderActivity.class));
	}
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.order_layout);
		findViewById(R.id.pay_btn).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pay_btn:
			SubmitActivity.open(this);
			break;

		default:
			break;
		}
		
	}
	
	
	private class ViewHolder{
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
		private TextView price;
		private TextView price0;
		private TextView price1;
		private TextView price2;
		private TextView price3;
		private TextView pay_from;
		private TextView fapiao;
		public ViewHolder() {
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
		}
		public void setModel(Model model){
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
			price1.setText(""+model.price1);
			price2.setText(""+model.price2);
			price3.setText(""+model.price3);
			pay_from.setText("支付方式："+model.pay_from);
			fapiao.setText("发票信息："+model.fapiao);
		}
	}
	
	public static class Model{
		
		private String time;
		private String no;
		private String consume_no;
		private Status status;
		private Status pay_btn;
		private String thumbnail; 
		private String title;
		private String address;
		private String phone;
		private String service;
		private String service_detail;
		private int price;
		private int price0;
		private int price0_0;
		private int price1;
		private int price2;
		private int price3;
		private String pay_from;
		private String fapiao;
		public Model(String time, String no, String consume_no, Status status,
				Status pay_btn, String thumbnail, String title, String address,
				String phone, String service, String service_detail, int price,
				int price0, int price0_0, int price1, int price2, int price3,
				String pay_from, String fapiao) {
			super();
			this.time = time;
			this.no = no;
			this.consume_no = consume_no;
			this.status = status;
			this.pay_btn = pay_btn;
			this.thumbnail = thumbnail;
			this.title = title;
			this.address = address;
			this.phone = phone;
			this.service = service;
			this.service_detail = service_detail;
			this.price = price;
			this.price0 = price0;
			this.price0_0 = price0_0;
			this.price1 = price1;
			this.price2 = price2;
			this.price3 = price3;
			this.pay_from = pay_from;
			this.fapiao = fapiao;
		}
		
		
		
	}

}
