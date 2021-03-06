package com.cheshang8.app;

import com.cheshang8.app.fragment.TabIndexFragment;
import com.cheshang8.app.fragment.TabZoneFragment;
import com.cheshang8.app.utils.Preferences.Global;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements OnClickListener{

	public static void open(Context context){
		Intent intent = new Intent(context, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SplashActivity.open(this);
		setContentView(R.layout.activity_main);
		fragments = new Fragment[]{TabIndexFragment.newInstance(),new Fragment(),new Fragment(),TabZoneFragment.newInstance()};
		findViewById(R.id.tab1).setOnClickListener(this);
		findViewById(R.id.tab2).setOnClickListener(this);
		findViewById(R.id.tab3).setOnClickListener(this);
		findViewById(R.id.tab4).setOnClickListener(this);
		findViewById(R.id.tab1).performClick();
	}
	
	private Fragment[] fragments;

	private Fragment lastFragment;

	public void switchFragment(Fragment fragment) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		if (lastFragment != null) {
			transaction.hide(lastFragment);
		}
		if (!fragment.isAdded()) {
			transaction.add(R.id.fragment_container, fragment);
		} else {
			transaction.show(fragment);
			fragment.onResume();
		}
		transaction.commitAllowingStateLoss();
		lastFragment = fragment;
	}
	
	private View lastView;

	@Override
	public void onClick(View v) {
		if (lastView != null) {
			lastView.setSelected(false);
		}
		v.setSelected(true);
		switch (v.getId()) {
		case R.id.tab1:
			switchFragment(fragments[0]);
			break;
		case R.id.tab2:
			switchFragment(fragments[1]);
			break;
		case R.id.tab3:
			switchFragment(fragments[2]);
			break;
		case R.id.tab4:
			switchFragment(fragments[3]);
			break;

		}
		lastView = v;
	}


}
