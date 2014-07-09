package com.cheshang8.app.widget;

import com.cheshang8.app.R;
import com.cheshang8.app.fragment.SliderFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class TabIndexHeaderView extends FrameLayout{

	private Fragment fragment;
	
	public TabIndexHeaderView(Context context,Fragment fragment) {
		super(context);
		this.fragment = fragment;
		init();
	}

	private void init() {
		 LayoutInflater.from(getContext()).inflate(
					R.layout.tab_index_header, this);
		 fragment.getChildFragmentManager().beginTransaction().replace(R.id.slider_container, SliderFragment.newInstance()).commit();
		 
		 
	}

}
