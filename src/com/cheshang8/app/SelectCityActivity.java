package com.cheshang8.app;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.SelectCityAdapter;
import com.cheshang8.app.adapter.SelectCityAdapter.Model;
import com.cheshang8.app.adapter.SelectCityAdapter.Model.Col;
import com.cheshang8.app.network.BaseClient;
import com.cheshang8.app.network.CityListRequest.Result;
import com.cheshang8.app.network.CityListRequest;

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

public class SelectCityActivity extends Activity {

	public static void open(Context context) {
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

		final ListView listView = (ListView) findViewById(R.id.listview);

		listView.addHeaderView(LayoutInflater.from(this).inflate(
				R.layout.select_city_header, null));

		final CityListRequest request = new CityListRequest();
		
		request.request(new BaseClient.SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				List<Result> results = (List<Result>) object;
				listView.setAdapter(new SelectCityAdapter(SelectCityActivity.this, new Model(Result.toList(results), 0)));
			}
		});
		
		

	}

}
