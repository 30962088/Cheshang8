package com.cheshang8.app;

import java.io.IOException;

import java.util.List;

import org.apache.commons.io.IOUtils;


import com.cheshang8.app.adapter.MsgListAdapter;
import com.cheshang8.app.adapter.MyCarListAdapter;
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
import android.widget.ListView;
import com.mengle.lib.wiget.BaseListView;
import com.mengle.lib.wiget.BaseListView.OnLoadListener;
public class MsgListActivity extends BaseActivity implements OnLoadListener{
	
	public static void open(Context context){
		context.startActivity(new Intent(context, MsgListActivity.class));
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg_list_layout);
		
		BaseListView listView = (BaseListView) findViewById(R.id.listview);
		listView.setOnLoadListener(this);
		try {
			List<MsgListAdapter.Model> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/msg_list.json")),
					new TypeToken<List<MsgListAdapter.Model>>() {
					}.getType());
			
			MsgListAdapter adapter = new MsgListAdapter(this, list);
			
			listView.setAdapter(adapter);
			
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
