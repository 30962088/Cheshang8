package com.cheshang8.app;



import java.util.List;

import org.apache.commons.io.IOUtils;


import com.cheshang8.app.adapter.BankAdapter;
import com.cheshang8.app.adapter.BankAdapter.Model;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PayBankActivity extends BaseActivity implements OnClickListener{
	public static void open(Context context,Result result){
		Intent intent = new Intent(context, PayBankActivity.class);
		intent.putExtra("result", result);
		context.startActivity(intent);
	}
	
	private Fragment[] fragments;
	
	private Result result;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		result = (Result) getIntent().getSerializableExtra("result");
		setContentView(R.layout.pay_bank_layout);
		fragments = new Fragment[]{BankFragment.newInstance(result),BankFragment.newInstance(result)};
		findViewById(R.id.tab1).setOnClickListener(this);
		findViewById(R.id.tab2).setOnClickListener(this);
		findViewById(R.id.tab1).performClick();
	}
	
	
	
	public static class BankFragment extends Fragment{
		
		public static BankFragment newInstance(Result result){
			BankFragment fragment = new BankFragment();
			fragment.result = result;
			return fragment;
		}
		
		private Result result;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			return inflater.inflate(R.layout.bank_listview, null);
		}
		
		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);
			
			ListView listView = (ListView) view.findViewById(R.id.listview);
			
			try {
				List<Model> list = new Gson().fromJson(
						IOUtils.toString(getActivity().getAssets().open(
								"datas/bank.json")),
						new TypeToken<List<Model>>() {
						}.getType());
				
				BankAdapter adapter = new BankAdapter(getActivity(), list);
				
				listView.setAdapter(adapter);
				
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						PaySuccessActivity.open(getActivity(), result);
						
					}
				});
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
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
		}
		lastView = v;
	}
	
	

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


	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}



}
