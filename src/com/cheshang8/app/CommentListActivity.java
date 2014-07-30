package com.cheshang8.app;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.DetailCommentAdapter;
import com.cheshang8.app.adapter.OrderAdapter;
import com.cheshang8.app.adapter.DetailCommentAdapter.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CommentListActivity extends BaseActivity {
	public static void open(Context context){
		context.startActivity(new Intent(context, CommentListActivity.class));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment_list_layout);
		ListView listView = (ListView) findViewById(R.id.listview);
		try {
			List<Model> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/detail_comment.json")),
					new TypeToken<List<Model>>() {
					}.getType());
			DetailCommentAdapter adapter = new DetailCommentAdapter(this, list);
			listView.setAdapter(adapter);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		findViewById(R.id.comment_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				;
				
			}
		});
	}
	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}


}
