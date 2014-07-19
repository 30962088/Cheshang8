package com.cheshang8.app;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.OrderAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

public class AllOrderActivity extends FragmentActivity {

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
	}


}
