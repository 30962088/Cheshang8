package com.cheshang8.app;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.SelectCityAdapter;
import com.cheshang8.app.adapter.SelectCityAdapter.Model;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class SelectCityActivity extends Activity{

	
	public static void open(Context context ){
		Intent intent = new Intent(context, SelectCityActivity.class);
		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.select_city_layout);
		
		findViewById(R.id.close).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		ListView listView = (ListView) findViewById(R.id.listview);
		
		listView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.select_city_header, null));
		
		try {
			List<String> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/city.json")),
					new TypeToken<List<String>>() {
					}.getType());
			
			listView.setAdapter(new SelectCityAdapter(this, new Model(list, 0)));
			
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
