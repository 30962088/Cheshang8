package com.cheshang8.app;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.OrderAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AllOrderActivity extends FragmentActivity {
	public static void open(Context context){
		context.startActivity(new Intent(context, AllOrderActivity.class));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_order_layout);
		ListView listView = (ListView) findViewById(R.id.listview);
		try {
			List<OrderAdapter.Model> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/order_list.json")),
					new TypeToken<List<OrderAdapter.Model>>() {
					}.getType());
			
			OrderAdapter adapter = new OrderAdapter(this, list);
			
			listView.setAdapter(adapter);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				OrderActivity.open(AllOrderActivity.this);
				
			}
		});
	}


}
