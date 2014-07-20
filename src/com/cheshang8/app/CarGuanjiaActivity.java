package com.cheshang8.app;

import com.cheshang8.app.fragment.TabIndexFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class CarGuanjiaActivity extends FragmentActivity implements OnClickListener{
	public static void open(Context context){
		context.startActivity(new Intent(context, CarGuanjiaActivity.class));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_guanjia_layout);
		findViewById(R.id.right_nav_btn).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_nav_btn:
			CarInfoActivity.open(this);
			break;
		
		}
		
	}


}
