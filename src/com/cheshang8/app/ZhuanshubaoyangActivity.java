package com.cheshang8.app;

import com.cheshang8.app.fragment.TabIndexFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ZhuanshubaoyangActivity extends BaseActivity {
	public static void open(Context context){
		context.startActivity(new Intent(context, ZhuanshubaoyangActivity.class));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuanshubaoyang_layout);
	
	}
	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}


}
