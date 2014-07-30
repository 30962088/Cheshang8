package com.cheshang8.app;

import java.io.Serializable;

import com.cheshang8.app.fragment.TabIndexFragment;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.cheshang8.app.utils.BitmapLoader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.method.MovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SubmitActivity extends BaseActivity implements OnClickListener{
	public static void open(Context context,Result result){
		Intent intent = new Intent(context, SubmitActivity.class);
		intent.putExtra("model", result);
		context.startActivity(intent);
	}
	
	private ViewHolder holder;
	
	private Result result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		result = (Result) getIntent().getSerializableExtra("model");
		setContentView(R.layout.submit_layout);
		holder = new ViewHolder();
		holder.setModel(result.toSubmitModel());
		findViewById(R.id.submit_btn).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit_btn:
			PayActivity.open(this,result);
			break;

		default:
			break;
		}
		
	}
	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

	
	public static class Model implements Serializable{
		private String thumbnail;
		private String name;
		private String detail;
		private int price;
		private int price1;
		private String title;
		private String address;
		private String phone;
		private String my_phone;
		public Model(String thumbnail, String name, String detail,
				int price, int price1, String title,
				String address, String phone, String my_phone) {
			super();
			this.thumbnail = thumbnail;
			this.name = name;
			this.detail = detail;
			this.price = price;
			this.price1 = price1;
			this.title = title;
			this.address = address;
			this.phone = phone;
			this.my_phone = my_phone;
		}
		
	}
	
	private class ViewHolder{
		private ImageView thumbnail;
		private TextView name;
		private TextView detail;
		private TextView price;
		private TextView price1;
		private TextView title;
		private TextView address;
		private TextView phone;
		private TextView my_phone;
		public ViewHolder() {
			thumbnail = (ImageView) findViewById(R.id.thumbnail);
			name = (TextView) findViewById(R.id.name);
			detail = (TextView) findViewById(R.id.detail);
			price = (TextView) findViewById(R.id.price);
			price1 = (TextView) findViewById(R.id.price1);
			title = (TextView) findViewById(R.id.title);
			address = (TextView) findViewById(R.id.address);
			phone = (TextView) findViewById(R.id.phone);
			my_phone = (TextView) findViewById(R.id.my_phone);
		}
		
		public void setModel(Model model){
			BitmapLoader.displayImage(SubmitActivity.this, model.thumbnail, thumbnail);
			name.setText(model.name);
			detail.setText(model.detail);
			price.setText(""+model.price);
			price1.setText("原价：￥"+model.price1+"    节省：￥"+(model.price1-model.price));
			title.setText(model.title);
			address.setText("地址："+model.address);
			my_phone.setText("电话："+model.my_phone);
		
		}
		
	}

}
