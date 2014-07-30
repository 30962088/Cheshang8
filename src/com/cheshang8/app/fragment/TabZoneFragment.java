package com.cheshang8.app.fragment;



import java.util.List;
import java.util.Map;

import com.cheshang8.app.AllOrderActivity;
import com.cheshang8.app.CarGuanjiaActivity;
import com.cheshang8.app.LoginActivity;
import com.cheshang8.app.MsgListActivity;
import com.cheshang8.app.MyCarActivity;
import com.cheshang8.app.R;
import com.cheshang8.app.UserInfoActivity;
import com.cheshang8.app.ZhuanshubaoyangActivity;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.database.OrderField;
import com.cheshang8.app.database.OrderField.Callback;
import com.cheshang8.app.database.OrderField.Callback3;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.cheshang8.app.utils.Preferences;
import com.cheshang8.app.utils.Preferences.User;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
public class TabZoneFragment extends Fragment implements OnClickListener{

	public static TabZoneFragment newInstance(){
		TabZoneFragment fragment = new TabZoneFragment();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.zone_layout, null);
	}
	
	
	private View popup;
	
	private View container;
	
	private LoginHolder holder;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		container = view;
		OrderHolder orderHolder = new OrderHolder();
		holder = new LoginHolder();
		popup = view.findViewById(R.id.popup);
		popup.setOnClickListener(this);
		view.findViewById(R.id.avatar).setOnClickListener(this);
		view.findViewById(R.id.nav_left).setOnClickListener(this);
		view.findViewById(R.id.popup_inner).setOnClickListener(this);
		
		view.findViewById(R.id.all_order_btn).setOnClickListener(this);
		view.findViewById(R.id.all_order2_btn).setOnClickListener(this);
		view.findViewById(R.id.all_order3_btn).setOnClickListener(this);
		view.findViewById(R.id.all_order1_btn).setOnClickListener(this);
		view.findViewById(R.id.msg_btn).setOnClickListener(this);
		view.findViewById(R.id.zhuanshu_btn).setOnClickListener(this);
		view.findViewById(R.id.my_car_btn).setOnClickListener(this);
		view.findViewById(R.id.car_guanjia_btn).setOnClickListener(this);
	}
	
	private class OrderHolder{
		private TextView order1;
		private TextView order2;
		private TextView order3;
		private TextView order4;
		
		public OrderHolder() {
			order1 = (TextView) container.findViewById(R.id.order1);
			order2 = (TextView) container.findViewById(R.id.order2);
			order3 = (TextView) container.findViewById(R.id.order3);
			order4 = (TextView) container.findViewById(R.id.order4);
			setCount();
		}

		private void setCount() {
			OrderField.getCount(new Callback3() {
				
				@Override
				public void callback3(Map<Status, Integer> map) {
					order1.setText(""+(map.containsKey(Status.待支付)?map.get(Status.待支付):0));
					order2.setText(""+(map.containsKey(Status.待体验)?map.get(Status.待体验):0));
					order3.setText(""+(map.containsKey(Status.已完成)?map.get(Status.已完成):0));
				
					OrderField.getList(new Callback() {
						
						@Override
						public void callback(List<Result> list) {
							order4.setText(""+list.size());
							
						}
					});
					
					
				}
			});
			
		}
	}
	
	private class LoginHolder{
		private TextView name;
		private TextView level;
		private TextView coin;
		private View loginContainer;
		private View loginBtn;
		public LoginHolder() {
			name = (TextView) container.findViewById(R.id.name);
			level = (TextView) container.findViewById(R.id.level);
			coin = (TextView) container.findViewById(R.id.coin);
			loginContainer = container.findViewById(R.id.login_container);
			loginBtn = container.findViewById(R.id.login_btn);
			loginBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					LoginActivity.open(getActivity());
					
				}
			});
		}
		public void setModel(User user){
			if(user == null){
				loginBtn.setVisibility(View.VISIBLE);
				loginContainer.setVisibility(View.GONE);
			}else{
				loginBtn.setVisibility(View.GONE);
				loginContainer.setVisibility(View.VISIBLE);
				name.setText(user.getName());
				level.setText("养车等级："+user.getLevel());
				coin.setText("养车积分："+user.getCoin());
			}
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		User user = new Preferences.Global(getActivity()).getUser();
		holder.setModel(user);
	}

	private boolean checkLogin(){
		User user = new Preferences.Global(getActivity()).getUser();
		if(user != null){
			return true;
		}
		LoginActivity.open(getActivity());
		return false;
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_car_btn:
			if(checkLogin()){
				MyCarActivity.open(getActivity());
			}
			
			break;
		case R.id.car_guanjia_btn:
			if(checkLogin()){
				CarGuanjiaActivity.open(getActivity());
			}
			
			break;
		case R.id.zhuanshu_btn:
			if(checkLogin()){
				ZhuanshubaoyangActivity.open(getActivity());
			}
			
			break;
		case R.id.msg_btn:
			if(checkLogin()){
				MsgListActivity.open(getActivity());
			}
			break;
		case R.id.avatar:
			if(checkLogin()){
				UserInfoActivity.open(getActivity());
			}
			break;
		case R.id.all_order1_btn:
			if(checkLogin()){
				AllOrderActivity.open(getActivity(),Status.已完成);
			}
			break;
		case R.id.all_order2_btn:
			if(checkLogin()){
				AllOrderActivity.open(getActivity(),Status.待体验);
			}
			break;
		case R.id.all_order3_btn:
			if(checkLogin()){
				AllOrderActivity.open(getActivity(),Status.待支付);
				
			}
			
			break;
		case R.id.all_order_btn:
			if(checkLogin()){
				AllOrderActivity.open(getActivity(),null);
			}
			
			break;
		case R.id.popup:
			popup.setVisibility(View.GONE);
			break;
		case R.id.nav_left:
			if(popup.getVisibility() == View.GONE){
				popup.setVisibility(View.VISIBLE);
			}else{
				popup.setVisibility(View.GONE);
			}
			
			break;
		default:
			break;
		}
		
	}
	
}
