package com.cheshang8.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class TowOneRelativeLayout extends RelativeLayout{

	public TowOneRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TowOneRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TowOneRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredWidth/2);
		
	}

}
