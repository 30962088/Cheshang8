package com.cheshang8.app;

import java.util.List;

import com.cheshang8.app.adapter.DetailServiceAdapter;
import com.cheshang8.app.fragment.DetailCommentFragment;
import com.cheshang8.app.fragment.DetailMainFragment;
import com.cheshang8.app.fragment.DetailServiceFragment;
import com.cheshang8.app.fragment.SliderFragment;
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

public class DetailActivity extends BaseActivity implements OnClickListener {

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
	
	private View starBtn;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getStringExtra("id");
		context = this;
		setContentView(R.layout.detail_layout);
		starBtn = findViewById(R.id.starBtn);
		starBtn.setOnClickListener(this);
		tab1 = (TextView) findViewById(R.id.tab1);
		tab1.setOnClickListener(this);
		tab2 = (TextView) findViewById(R.id.tab2);
		tab2.setOnClickListener(this);
		tab3 = (TextView) findViewById(R.id.tab3);
		tab3.setOnClickListener(this);
		tab4 = (TextView) findViewById(R.id.tab4);
		tab4.setOnClickListener(this);
		
		
		
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
		
		
		switch (v.getId()) {
		case R.id.tab1:
			if (lastView != null) {
				lastView.setSelected(false);
			}
			switchFragment(fragments[0]);
			v.setSelected(true);
			lastView = v;
			break;
		case R.id.tab2:
			if (lastView != null) {
				lastView.setSelected(false);
			}
			switchFragment(fragments[1]);
			v.setSelected(true);
			lastView = v;
			break;
		case R.id.tab3:
			if (lastView != null) {
				lastView.setSelected(false);
			}
			switchFragment(fragments[2]);
			v.setSelected(true);
			lastView = v;
			break;
		case R.id.tab4:
			if (lastView != null) {
				lastView.setSelected(false);
			}
			switchFragment(fragments[3]);
			v.setSelected(true);
			lastView = v;
			break;
		case R.id.starBtn:
			onStar();
			break;
		}
		
	}
	
	private void onStar() {
		starBtn.setSelected(!starBtn.isSelected());
		
	}

	private void request(){
		
		ShopRequest request = new ShopRequest(id);
		
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				ShopRequest.Result result = (Result) object;
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.slider_container,
						SliderFragment.newInstance(result.getShop().toSliderModel())).commit();
				List<DetailServiceAdapter.Model> list = Service.toList(result.getServices());
				DetailMainFragment.Model model = result.getShop().toModel();
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

	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

}
