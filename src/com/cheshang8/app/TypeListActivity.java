package com.cheshang8.app;



import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.MsgListAdapter;
import com.cheshang8.app.adapter.TypeListAdapter;
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

public class TypeListActivity extends FragmentActivity {

	public static void open(Context context){
		context.startActivity(new Intent(context, TypeListActivity.class));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.type_list_layout);
		ListView listView = (ListView) findViewById(R.id.listview);
		try {
			List<TypeListAdapter.Model> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/type_list.json")),
					new TypeToken<List<TypeListAdapter.Model>>() {
					}.getType());
			
			TypeListAdapter adapter = new TypeListAdapter(this, list);
			
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
				ListActivity.open(TypeListActivity.this);
				
			}
		});
		
	}


}
