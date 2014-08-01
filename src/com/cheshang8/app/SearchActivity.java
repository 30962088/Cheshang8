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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends BaseActivity implements TextWatcher{
	
	public static void open(Context context){
		context.startActivity(new Intent(context, SearchActivity.class));
	}
	
	private ListView listView;
	
	private List<Model> list = new ArrayList<SearchItemAdapter.Model>();
	
	private SearchItemAdapter adapter;
	
	private EditText text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		listView = (ListView) findViewById(R.id.listview);
		text = (EditText) findViewById(R.id.text);
		adapter = new SearchItemAdapter(this, list);
		listView.setAdapter(adapter);
		request();
		text.addTextChangedListener(this);
	}
		
		
	private List<Result> results = new ArrayList<Result>();
	
	private void request(){
		ShopsRequest request = new ShopsRequest(new ShopsRequest.Params(1));
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				results = (List<Result>) object;
				/*list.clear();
				list.addAll(Result.toList(results));
				adapter.notifyDataSetChanged();*/
			}
		});
	}



	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}



	@Override
	public void afterTextChanged(Editable s) {
		if(TextUtils.isEmpty(s.toString())){
			list.clear();
			adapter.notifyDataSetChanged();
		}else{
			if(results.size()>0){
				list.add(results.get(0).toModel("人工洗车"));
				adapter.notifyDataSetChanged();
			}
		}
		
	}



	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

}
