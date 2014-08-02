package com.cheshang8.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
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
	
	private long date;
	
	private float x;
	
	private float y;
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			date = new Date().getTime();
			x = ev.getX();
			y = ev.getY();
			break;
			
		case MotionEvent.ACTION_UP:
			long deltaTime = new Date().getTime()-date;
			float deltaX = ev.getX()-x;
			
			if(deltaTime < 200){
				if(deltaX >100 && Math.abs(y-ev.getY())<10 && !isBoundInDisable(new Bound((int)x,(int)y,(int)ev.getX(),(int)ev.getY()))){
					finish();
				}
			}
			break;

		default:
			break;
		}
		
		return super.dispatchTouchEvent(ev);
	}
	
	private static class Bound{
		private int x1;
		private int y1;
		private int x2;
		private int y2;
		public Bound(int x1, int y1, int x2, int y2) {
			super();
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
	}
	
	private boolean isBoundInDisable(int x,int y){
		Bound bound = getDisableBackBound();
		if(bound == null){
			return false;
		}
		System.out.println("zzm:"+x+","+y+"bound:"+new Gson().toJson(bound));
		if((x>=bound.x1 && x<=bound.x2) &&(y>=bound.y1 && y<=bound.y2)){
			System.out.println("zzm:true");
			return true;
		}
		System.out.println("zzm:false");
		return false;
	}
	
	private boolean isBoundInDisable(Bound bound){
		return isBoundInDisable(bound.x1,bound.y1)&&isBoundInDisable(bound.x2,bound.y2);
	}
	
	private Bound getDisableBackBound(){
		Bound bound = null;
		for(View view : disableViews){
			int position[] = new int[2];
			view.getLocationInWindow(position);
			bound = new Bound(position[0], position[1], position[0]+view.getMeasuredWidth(), position[1]+view.getMeasuredHeight());
		}
		return bound;
	}
	
	private List<View> disableViews = new ArrayList<View>();
	
	protected void disableBack(View view){
		disableViews.add(view);
	}
	
	abstract protected Integer finishBtn();
	
	
}
