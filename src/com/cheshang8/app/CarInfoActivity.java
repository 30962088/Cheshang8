package com.cheshang8.app;

import com.cheshang8.app.fragment.TabIndexFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class CarInfoActivity extends BaseActivity {
	public static void open(Context context){
		context.startActivity(new Intent(context, CarInfoActivity.class));
	}
	private View item1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_info_layout);
		item1 = findViewById(R.id.item1);
		item1.setSelected(true);
	}

	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}


}
