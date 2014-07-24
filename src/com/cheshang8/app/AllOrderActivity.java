package com.cheshang8.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.OrderAdapter;
import com.cheshang8.app.adapter.OrderAdapter.Model;
import com.cheshang8.app.network.OrdersRequest;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.OrdersRequest.Result;

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
		Intent intent = new Intent(context, AllOrderActivity.class);
		
		context.startActivity(intent);
	}
	
	
	
	private ListView listView;
	
	private OrderAdapter adapter;
	
	private List<OrderAdapter.Model> list = new ArrayList<OrderAdapter.Model>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.all_order_layout);
		listView = (ListView) findViewById(R.id.listview);
		
			
			
		adapter = new OrderAdapter(this, list);
			
		listView.setAdapter(adapter);
			
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Model model = list.get(position);
				OrderActivity.open(AllOrderActivity.this,model.getNo());
				
			}
		});
		request();
	}
	
	private void request(){
		OrdersRequest request = new OrdersRequest();
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				List<Result> result = (List<Result>) object;
				list.clear();
				list.addAll(Result.toList(result));
				adapter.notifyDataSetChanged();
			}
		});
	}


}
