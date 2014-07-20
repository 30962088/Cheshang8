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

public class MsgListActivity extends Activity {
	
	public static void open(Context context){
		context.startActivity(new Intent(context, MsgListActivity.class));
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg_list_layout);
		
		ListView listView = (ListView) findViewById(R.id.listview);
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
	
	

}
