package com.cheshang8.app;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;


import com.cheshang8.app.adapter.SearchItemAdapter;
import com.cheshang8.app.adapter.SearchItemAdapter.Model;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		ListView listView = (ListView) findViewById(R.id.listview);
		try {
			List<Model> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/search_result.json")),
					new TypeToken<List<Model>>() {
					}.getType());
			
			SearchItemAdapter adapter = new SearchItemAdapter(this, list);
			
			listView.setAdapter(adapter);
			
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
