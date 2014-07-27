package com.cheshang8.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.SearchItemAdapter;
import com.cheshang8.app.adapter.SearchItemAdapter.Model;
import com.cheshang8.app.network.ShopsRequest;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.ShopsRequest.Params.Sort;
import com.cheshang8.app.network.ShopsRequest.Result;
import com.cheshang8.app.widget.CatSortDialog;
import com.cheshang8.app.widget.CatSortDialog.OnStateChange2;
import com.cheshang8.app.widget.CatSortDialog.SortCallback;
import com.cheshang8.app.widget.CatTwoDialog;
import com.cheshang8.app.widget.CatTwoDialog.Callback;
import com.cheshang8.app.widget.CatTwoDialog.OnStateChange;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListActivity extends BaseActivity implements OnClickListener,
		OnStateChange, OnStateChange2 {

	public static void open(Context context) {
		context.startActivity(new Intent(context, ListActivity.class));
	}

	private TextView locBtn;

	private TextView sortBtn;

	private ListView listView;
	
	private View locContainer;
	
	private View sortContainer;

	private SearchItemAdapter adapter;

	private List<Model> list = new ArrayList<SearchItemAdapter.Model>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		findViewById(R.id.nav_right_btn).setOnClickListener(this);
		sortContainer = findViewById(R.id.sort_container);
		sortContainer.setOnClickListener(this);
		locContainer = findViewById(R.id.loc_container);
		locContainer.setOnClickListener(this);
		locBtn = (TextView) findViewById(R.id.loc_btn);
		sortBtn = (TextView) findViewById(R.id.sort_btn);
		listView = (ListView) findViewById(R.id.listview);

		adapter = new SearchItemAdapter(this, list);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Model model = list.get(position);
				DetailActivity.open(ListActivity.this,model.getId());
			}
		});
		request();
	}
	
	private Sort sort = Sort.DEFAULT;
	
	private Integer dist1;
	
	private Integer dist2;
	
	private ArrayList<MapActivity.Model> mapList = new ArrayList<MapActivity.Model>();
	
	private void request(){
		ShopsRequest request = new ShopsRequest(new ShopsRequest.Params(1,dist1,dist2,sort));
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				List<Result> results = (List<Result>) object;
				mapList = Result.toMapList(results);
				list.clear();
				list.addAll(Result.toList(results));
				adapter.notifyDataSetChanged();
			}
		});
	}

	private CatTwoDialog twoDialog;

	private CatSortDialog sortDialog;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.nav_right_btn:
			MapActivity.open(this,mapList);
			break;
		case R.id.loc_container:
			if (twoDialog == null) {
				twoDialog = new CatTwoDialog(this, v);
				twoDialog.setCallback(new Callback() {
					
					@Override
					public void oncallback(String name,int id) {
						locBtn.setText(name);
						dist2 = id;
						request();
						
					}
				});
				twoDialog.setData(1);
				twoDialog.setOnStateChange(this);
			}
			twoDialog.toggle();

			break;

		case R.id.sort_container:
			if (sortDialog == null) {
				sortDialog = new CatSortDialog(this, v);
				sortDialog.setCallback(new SortCallback() {
					
					@Override
					public void oncallback(com.cheshang8.app.adapter.CatSortAdapter.Model model) {
						sortBtn.setText(model.getName());
						sort = model.getSort();
						request();
						
					}
				});
				sortDialog.setOnStateChange(this);
			}
			sortDialog.toggle();
			break;
		default:
			break;
		}

	}

	@Override
	public void ondismiss() {
		locContainer.setSelected(false);

	}

	@Override
	public void onshow() {
		locContainer.setSelected(true);

	}

	@Override
	public void ondismiss2() {
		sortContainer.setSelected(false);

	}

	@Override
	public void onshow2() {
		sortContainer.setSelected(true);

	}

	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

}
