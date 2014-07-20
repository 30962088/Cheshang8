package com.cheshang8.app;

import com.cheshang8.app.fragment.TabIndexFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class CarInfoActivity extends FragmentActivity {

	private View item1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_info_layout);
		item1 = findViewById(R.id.item1);
		item1.setSelected(true);
	}


}
