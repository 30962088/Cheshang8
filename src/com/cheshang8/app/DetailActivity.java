package com.cheshang8.app;

import java.util.List;

import com.cheshang8.app.adapter.DetailServiceAdapter;
import com.cheshang8.app.fragment.DetailCommentFragment;
import com.cheshang8.app.fragment.DetailMainFragment;
import com.cheshang8.app.fragment.DetailServiceFragment;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.ShopRequest;
import com.cheshang8.app.network.ShopRequest.Result;
import com.cheshang8.app.network.ShopRequest.Result.Service;
import com.cheshang8.app.utils.BitmapLoader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends FragmentActivity implements OnClickListener {

	public static void open(Context context,String id){
		Intent intent = new Intent(context, DetailActivity.class);
		intent.putExtra("id", id);
		context.startActivity(intent);
	}
	
	private String id;
	
	private Context context;
	
	private TextView tab1;
	
	private TextView tab2;
	
	private TextView tab3;
	
	private TextView tab4;
	
	private ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getStringExtra("id");
		context = this;
		setContentView(R.layout.detail_layout);
		tab1 = (TextView) findViewById(R.id.tab1);
		tab1.setOnClickListener(this);
		tab2 = (TextView) findViewById(R.id.tab2);
		tab2.setOnClickListener(this);
		tab3 = (TextView) findViewById(R.id.tab3);
		tab3.setOnClickListener(this);
		tab4 = (TextView) findViewById(R.id.tab4);
		tab4.setOnClickListener(this);
		
		img = (ImageView) findViewById(R.id.img);
		
		request();
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
	
	private void request(){
		
		ShopRequest request = new ShopRequest(id);
		
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				ShopRequest.Result result = (Result) object;
				List<DetailServiceAdapter.Model> list = Service.toList(result.getServices());
				DetailMainFragment.Model model = result.getShop().toModel();
				BitmapLoader.displayImage(context, result.getShop().getLogo(), img);
				fragments = new Fragment[] { DetailMainFragment.newInstance(context,model),
						DetailServiceFragment.newInstance(context,list,result.getShop().getId()),
						new Fragment(),
						DetailCommentFragment.newInstance(context,result.getShop().getId())};
				tab2.setText("服务("+result.getShop().getServices_count()+")");
				tab3.setText("商品("+result.getShop().getProducts_count()+")");
				tab4.setText("评论("+result.getShop().getComment_count()+")");
				tab1.performClick();
			}
		});
		
		
	}

}
