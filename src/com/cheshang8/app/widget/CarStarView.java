package com.cheshang8.app.widget;

import java.util.List;

import com.cheshang8.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RatingBar;

public class CarStarView extends FrameLayout{

	public CarStarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private RatingBar container;

	private void init() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.ratingbar, this);
		
		container = (RatingBar) findViewById(R.id.ratingbar);
		
	}
	
	public void setStar(float count){
		container.setRating(count);
	}
	
	public void setIndicator(boolean val){
		container.setIsIndicator(val);
	}
	
	public float getStar(){
		return container.getRating();
	}
	
	

}
