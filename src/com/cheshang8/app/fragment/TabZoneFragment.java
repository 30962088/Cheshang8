package com.cheshang8.app.fragment;



import com.cheshang8.app.AllOrderActivity;
import com.cheshang8.app.CarGuanjiaActivity;
import com.cheshang8.app.LoginActivity;
import com.cheshang8.app.MsgListActivity;
import com.cheshang8.app.MyCarActivity;
import com.cheshang8.app.R;
import com.cheshang8.app.UserInfoActivity;
import com.cheshang8.app.ZhuanshubaoyangActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
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
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		popup = view.findViewById(R.id.popup);
		popup.setOnClickListener(this);
		view.findViewById(R.id.avatar).setOnClickListener(this);
		view.findViewById(R.id.nav_left).setOnClickListener(this);
		view.findViewById(R.id.popup_inner).setOnClickListener(this);
		view.findViewById(R.id.login_btn).setOnClickListener(this);
		view.findViewById(R.id.all_order_btn).setOnClickListener(this);
		view.findViewById(R.id.all_order2_btn).setOnClickListener(this);
		view.findViewById(R.id.all_order3_btn).setOnClickListener(this);
		view.findViewById(R.id.all_order1_btn).setOnClickListener(this);
		view.findViewById(R.id.msg_btn).setOnClickListener(this);
		view.findViewById(R.id.zhuanshu_btn).setOnClickListener(this);
		view.findViewById(R.id.my_car_btn).setOnClickListener(this);
		view.findViewById(R.id.car_guanjia_btn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_car_btn:
			MyCarActivity.open(getActivity());
			break;
		case R.id.car_guanjia_btn:
			CarGuanjiaActivity.open(getActivity());
			break;
		case R.id.zhuanshu_btn:
			ZhuanshubaoyangActivity.open(getActivity());
			break;
		case R.id.msg_btn:
			MsgListActivity.open(getActivity());
			break;
		case R.id.avatar:
			UserInfoActivity.open(getActivity());
			break;
		case R.id.all_order1_btn:
			AllOrderActivity.open(getActivity());
			break;
		case R.id.all_order2_btn:
			AllOrderActivity.open(getActivity());
			break;
		case R.id.all_order3_btn:
			AllOrderActivity.open(getActivity());
			break;
		case R.id.all_order_btn:
			AllOrderActivity.open(getActivity());
			break;
		case R.id.login_btn:
			LoginActivity.open(getActivity());
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
