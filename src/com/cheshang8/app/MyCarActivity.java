package com.cheshang8.app;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;


import com.cheshang8.app.adapter.MyCarListAdapter;
import com.cheshang8.app.adapter.SearchItemAdapter;
import com.cheshang8.app.adapter.SearchItemAdapter.Model;
import com.cheshang8.app.widget.CatSortDialog;
import com.cheshang8.app.widget.CatSortDialog.OnStateChange2;
import com.cheshang8.app.widget.CatTwoDialog;
import com.cheshang8.app.widget.CatTwoDialog.OnStateChange;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MyCarActivity extends Activity implements OnClickListener{
	
	public static void open(Context context){
		context.startActivity(new Intent(context, MyCarActivity.class));
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_car_layout);
		findViewById(R.id.right_nav_btn).setOnClickListener(this);
		findViewById(R.id.add_car_btn).setOnClickListener(this);
		ListView listView = (ListView) findViewById(R.id.listview);
		try {
			List<MyCarListAdapter.Model> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/my_car_list.json")),
					new TypeToken<List<MyCarListAdapter.Model>>() {
					}.getType());
			
			MyCarListAdapter adapter = new MyCarListAdapter(this, list);
			
			listView.setAdapter(adapter);
			
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_nav_btn:
			CarInfoActivity.open(this);
			break;
		case R.id.add_car_btn:
			CarInfoActivity.open(this);
			break;
		}
		
	}

}
