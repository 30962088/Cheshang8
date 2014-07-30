package com.cheshang8.app;


import java.io.Serializable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheshang8.app.network.OrdersRequest.Result;
import com.mengle.lib.utils.WigetUtils;
import com.mengle.lib.utils.WigetUtils.OnItemClickListener;
public class PayActivity extends BaseActivity implements OnClickListener{
	public static void open(Context context,Result result){
		Intent intent = new Intent(context, PayActivity.class);
		intent.putExtra("result", result);
		context.startActivity(intent);
	}
	private View payView;
	private View fapiaoView;
	private Result result;
	private ViewHolder holder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		result = (Result) getIntent().getSerializableExtra("result");
		setContentView(R.layout.pay_layout);
		holder = new ViewHolder();
		holder.setModel(result.toPayModel());
		findViewById(R.id.pay_btn).setOnClickListener(this);
		ViewGroup pay_container = (ViewGroup)findViewById(R.id.pay_container);
		WigetUtils.onChildViewClick(pay_container, new OnItemClickListener(){

			@Override
			public void onItemClick(ViewGroup group, View view, int position,
					long id) {
				if(payView!=null){
					payView.setSelected(false);
				}
				view.setSelected(true);
				payView = view;
			}
			
		});
		pay_container.getChildAt(0).performClick();
		ViewGroup fapiao_type = (ViewGroup)findViewById(R.id.fapiao_type);
		WigetUtils.onChildViewClick(fapiao_type, new OnItemClickListener(){

			@Override
			public void onItemClick(ViewGroup group, View view, int position,
					long id) {
				if(fapiaoView!=null){
					fapiaoView.setSelected(false);
				}
				view.setSelected(true);
				fapiaoView = view;
			}
			
		});
		fapiao_type.getChildAt(0).performClick();
		
	}
	
	public static class Model implements Serializable{
		private String name;
		private String detail;
		private int price;
		private int price1;
		private int price2;
		private int price3;
		private int price4;
		private int price5;
		private int price6;
		public Model(String name, String detail, int price, int price1,
				int price2, int price3, int price4, int price5, int price6) {
			super();
			this.name = name;
			this.detail = detail;
			this.price = price;
			this.price1 = price1;
			this.price2 = price2;
			this.price3 = price3;
			this.price4 = price4;
			this.price5 = price5;
			this.price6 = price6;
		}
		
		
	}
	
	private class ViewHolder{
		private TextView name;
		private TextView detail;
		private TextView price;
		private TextView price1;
		private TextView price2;
		private TextView price3;
		private TextView price4;
		private TextView price5;
		private TextView price6;
		public ViewHolder() {
			name = (TextView) findViewById(R.id.name);
			detail = (TextView) findViewById(R.id.detail);
			price = (TextView) findViewById(R.id.price);
			price1 = (TextView) findViewById(R.id.price1);
			price2 = (TextView) findViewById(R.id.price2);
			price3 = (TextView) findViewById(R.id.price3);
			price4 = (TextView) findViewById(R.id.price4);
			price5 = (TextView) findViewById(R.id.price5);
			price6 = (TextView) findViewById(R.id.price6);
		}
		
		public void setModel(Model model){
			name.setText(model.name);
			detail.setText(model.detail);
			price.setText(""+model.price);
			price1.setText("原价：￥"+model.price1+"   节省：￥25");
			price2.setText(""+model.price2);
			price3.setText(""+model.price3);
			price4.setText(""+model.price4);
			price5.setText(""+model.price5);
			price6.setText(""+model.price6);
		}
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.pay_btn:
			if(payView.getId() == R.id.bank_btn){
				PayBankActivity.open(this);
			}else{
				PaySuccessActivity.open(this,result);
			}
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


}
