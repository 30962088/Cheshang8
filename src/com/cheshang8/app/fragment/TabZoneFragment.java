package com.cheshang8.app.fragment;



import com.cheshang8.app.R;

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
		view.findViewById(R.id.nav_left).setOnClickListener(this);
		view.findViewById(R.id.popup_inner).setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
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
