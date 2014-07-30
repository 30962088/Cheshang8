package com.cheshang8.app;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.DetailCommentAdapter;
import com.cheshang8.app.adapter.OrderAdapter;
import com.cheshang8.app.adapter.DetailCommentAdapter.Model;
import com.cheshang8.app.network.CommentsRequest;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.CommentsRequest.Result;

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

import com.mengle.lib.wiget.BaseListView;
import com.mengle.lib.wiget.BaseListView.OnLoadListener;

public class CommentListActivity extends BaseActivity implements OnLoadListener {
	public static void open(Context context,String shop_id){
		Intent intent = new Intent(context, CommentListActivity.class);
		intent.putExtra("shop_id", shop_id);
		context.startActivity(intent);
	}
	
	private String shop_id;
	
	private Context context;
	private BaseListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shop_id = getIntent().getStringExtra("shop_id");
		context = this;
		setContentView(R.layout.comment_list_layout);
		listView = (BaseListView) findViewById(R.id.listview);
		listView.setOnLoadListener(this);
		findViewById(R.id.comment_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		request();
	}
	
	
	private void request() {
		CommentsRequest request = new CommentsRequest(shop_id);
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				CommentsRequest.Result result = (Result) object;
				DetailCommentAdapter adapter = new DetailCommentAdapter(context, Result.toList(result.getList()));
				listView.setAdapter(adapter);
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
