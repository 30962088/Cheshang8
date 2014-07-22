package com.cheshang8.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;


import com.cheshang8.app.adapter.SearchItemAdapter;
import com.cheshang8.app.adapter.SearchItemAdapter.Model;
import com.cheshang8.app.network.ShopsRequest;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.ShopsRequest.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class SearchActivity extends Activity{
	
	public static void open(Context context){
		context.startActivity(new Intent(context, SearchActivity.class));
	}
	
	private ListView listView;
	
	private List<Model> list = new ArrayList<SearchItemAdapter.Model>();
	
	private SearchItemAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new SearchItemAdapter(this, list);
		listView.setAdapter(adapter);
		request();
	}
		
		
	
	private void request(){
		ShopsRequest request = new ShopsRequest(new ShopsRequest.Params(1));
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				List<Result> results = (List<Result>) object;
				list.clear();
				list.addAll(Result.toList(results));
				adapter.notifyDataSetChanged();
			}
		});
	}

}
