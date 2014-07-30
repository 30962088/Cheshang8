package com.cheshang8.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.OrderAdapter;
import com.cheshang8.app.adapter.OrderAdapter.Model;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.database.OrderField;
import com.cheshang8.app.database.OrderField.Callback;
import com.cheshang8.app.network.OrdersRequest;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.OrdersRequest.Result;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.mengle.lib.wiget.BaseListView;
import com.mengle.lib.wiget.BaseListView.OnLoadListener;
public class AllOrderActivity extends BaseActivity implements OnLoadListener{
	public static void open(Context context,Status status){
		Intent intent = new Intent(context, AllOrderActivity.class);
		intent.putExtra("status", status);
		context.startActivity(intent);
	}
	
	
	
	private BaseListView listView;
	
	private OrderAdapter adapter;
	
	private Status status;
	
	private List<OrderAdapter.Model> list = new ArrayList<OrderAdapter.Model>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		status = (Status) getIntent().getSerializableExtra("status");
		
		setContentView(R.layout.all_order_layout);
		TextView textView = (TextView) findViewById(R.id.title);
		if(status == null){
			textView.setText("全部订单");
		}else{
			textView.setText(status.getText());
		}
		
		listView = (BaseListView) findViewById(R.id.listview);
		
		listView.setOnLoadListener(this);
			
		adapter = new OrderAdapter(this, list);
			
		listView.setAdapter(adapter);
			
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Model model = list.get(position-1);
				OrderActivity.open(AllOrderActivity.this,model.getNo());
				
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		request();
	}
	
	private void request(){
		OrderField.getList(new Callback() {
			@Override
			public void callback(List<Result> result) {
				list.clear();
				for(Model result2 : Result.toList(result)){
					if(status == null|| result2.getStatus() == status){
						list.add(result2);
					}
				}
				
				adapter.notifyDataSetChanged();
				
			}
		});
		
	}

	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

	@Override
	public boolean onLoad(int offset, int limit) {
		
		return false;
	}

	@Override
	public void onLoadSuccess() {
//		request();
		
	}


}
