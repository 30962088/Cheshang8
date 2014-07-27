package com.cheshang8.app;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

public abstract class BaseActivity extends FragmentActivity{

	
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
		init();
	}
	@Override
	public void setContentView(View view, LayoutParams params) {
		// TODO Auto-generated method stub
		super.setContentView(view, params);
		init();
	}
	
	private void init(){
		if(finishBtn() != null){
			findViewById(finishBtn()).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
					
				}
			});
		}
	}
	
	
	
	abstract protected Integer finishBtn();
	
	
}
