package com.cheshang8.app;

import com.cheshang8.app.utils.BitmapLoader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends Activity implements Runnable{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		ImageView imageView = (ImageView) findViewById(R.id.img);
		BitmapLoader.displayImage(this, "https://raw.githubusercontent.com/zimengle/Static/master/Images/0.jpg", imageView);
		imageView.postDelayed(this, 2000);
	}

	@Override
	public void run() {
		MainActivity.open(this);
		finish();
		
	}

}
