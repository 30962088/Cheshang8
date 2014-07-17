package com.cheshang8.app.widget;

import java.util.List;

import com.cheshang8.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class CarStarView extends FrameLayout{

	public CarStarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private ViewGroup container;

	private void init() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.car_star, this);
		
		container = (ViewGroup) findViewById(R.id.container);
		
	}
	
	public void setStar(int count){
		
		for(int i = 0;i<container.getChildCount();i++){
			View view = container.getChildAt(i);
			if(i<count){
				view.setSelected(true);
			}else{
				view.setSelected(false);
			}
		}
		
	}
	
	
	
	

}
