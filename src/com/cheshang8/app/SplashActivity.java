package com.cheshang8.app;

import com.cheshang8.app.utils.Preferences.Global;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends Activity implements Runnable{
	
	public static void open(Context context){
		Intent intent = new Intent(context, SplashActivity.class);
		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		ImageView imageView = (ImageView) findViewById(R.id.img);
//		BitmapLoader.displayImage(this, "https://raw.githubusercontent.com/zimengle/Static/master/Images/0.jpg", imageView);
		imageView.postDelayed(this, 2000);
	}

	@Override
	public void run() {
		
		Global global = new Global(this);
		if(global.isFirst()){
			SelectCityActivity.open(this);
			global.setFirst(false);
		}
		finish();
	}

}
