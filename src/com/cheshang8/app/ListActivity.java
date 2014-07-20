package com.cheshang8.app;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;


import com.cheshang8.app.adapter.SearchItemAdapter;
import com.cheshang8.app.adapter.SearchItemAdapter.Model;
import com.cheshang8.app.widget.CatSortDialog;
import com.cheshang8.app.widget.CatSortDialog.OnStateChange2;
import com.cheshang8.app.widget.CatTwoDialog;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListActivity extends Activity implements OnClickListener,OnStateChange,OnStateChange2{
	
	public static void open(Context context){
		context.startActivity(new Intent(context, ListActivity.class));
	}
	
	private View locBtn;
	
	private View sortBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		findViewById(R.id.nav_right_btn).setOnClickListener(this);
		locBtn = findViewById(R.id.loc_btn);
		locBtn.setOnClickListener(this);
		sortBtn = findViewById(R.id.sort_btn);
		sortBtn.setOnClickListener(this);
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
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				DetailActivity.open(ListActivity.this);
			}
		});
	}
	
	private CatTwoDialog twoDialog;
	
	private CatSortDialog sortDialog;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.nav_right_btn:
			MapActivity.open(this);
			break;
		case R.id.loc_btn:
			if(twoDialog == null){
				twoDialog = new CatTwoDialog(this,v);
				twoDialog.setOnStateChange(this);
			}
			twoDialog.toggle();
			
			break;

		case R.id.sort_btn:
			if(sortDialog == null){
				sortDialog = new CatSortDialog(this,v);
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
		locBtn.setSelected(false);
		
	}

	@Override
	public void onshow() {
		locBtn.setSelected(true);
		
	}

	@Override
	public void ondismiss2() {
		sortBtn.setSelected(false);
		
	}

	@Override
	public void onshow2() {
		sortBtn.setSelected(true);
		
	}

}
