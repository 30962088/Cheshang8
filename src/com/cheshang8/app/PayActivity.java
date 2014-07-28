package com.cheshang8.app;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.mengle.lib.utils.WigetUtils;
import com.mengle.lib.utils.WigetUtils.OnItemClickListener;
public class PayActivity extends BaseActivity implements OnClickListener{
	public static void open(Context context){
		context.startActivity(new Intent(context, PayActivity.class));
	}
	private View payView;
	private View fapiaoView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_layout);
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
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.pay_btn:
			if(payView.getId() == R.id.bank_btn){
				PayBankActivity.open(this);
			}else{
				PaySuccessActivity.open(this);
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
