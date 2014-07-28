package com.cheshang8.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class App extends Application {

	private static App instance;
	
	public static App getInstance() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
	}

}