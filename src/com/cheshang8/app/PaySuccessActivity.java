package com.cheshang8.app;


import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.TabIndexAdapter;
import com.cheshang8.app.adapter.TabIndexAdapter.Model;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.ListView;

public class PaySuccessActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_success_layout);
		ListView listView = (ListView)findViewById(R.id.listview);
		
		listView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.pay_success_header, null));
		
		try {
			List<Model> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/index_list.json")),
					new TypeToken<List<Model>>() {
					}.getType());
			
			TabIndexAdapter adapter = new TabIndexAdapter(this, list);
			
			listView.setAdapter(adapter);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}
