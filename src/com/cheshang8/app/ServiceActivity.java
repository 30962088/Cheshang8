package com.cheshang8.app;

import com.cheshang8.app.fragment.TabIndexFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class ServiceActivity extends FragmentActivity implements OnClickListener{
	public static void open(Context context){
		context.startActivity(new Intent(context, ServiceActivity.class));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_layout);
		findViewById(R.id.pay_btn).setOnClickListener(this);
		findViewById(R.id.comment_btn).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pay_btn:
			SubmitActivity.open(this);
			break;
		case R.id.comment_btn:
			CommentListActivity.open(this);
			break;
		default:
			break;
		}
		
	}


}
