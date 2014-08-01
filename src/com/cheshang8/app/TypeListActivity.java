package com.cheshang8.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.MsgListAdapter;
import com.cheshang8.app.adapter.TypeListAdapter;
import com.cheshang8.app.network.CategoriesRequest;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.CategoriesRequest.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mengle.lib.wiget.BaseListView;
import com.mengle.lib.wiget.BaseListView.OnLoadListener;

public class TypeListActivity extends BaseActivity implements OnLoadListener{

	public static void open(Context context, String pid) {
		Intent intent = new Intent(context, TypeListActivity.class);
		intent.putExtra("pid", pid);
		context.startActivity(intent);
	}

	private String pid;

	private List<TypeListAdapter.Model> list = new ArrayList<TypeListAdapter.Model>();

	private BaseListView  listView;

	private TypeListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pid = getIntent().getStringExtra("pid");
		setContentView(R.layout.type_list_layout);
		listView = (BaseListView ) findViewById(R.id.listview);
		listView.setOnLoadListener(this);
		adapter = new TypeListAdapter(this, list);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				ListActivity.open(TypeListActivity.this,list.get(position-1).getText());

			}
		});
		request();
	}

	private void request() {
		CategoriesRequest request = new CategoriesRequest(pid);
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				list.clear();
				list.addAll(Result.toList((List<Result>) object));
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLoadSuccess() {
		// TODO Auto-generated method stub
		
	}
	
	

}
