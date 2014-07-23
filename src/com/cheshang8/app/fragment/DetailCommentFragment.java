package com.cheshang8.app.fragment;


import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.cheshang8.app.adapter.DetailCommentAdapter;
import com.cheshang8.app.adapter.DetailCommentAdapter.Model;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.CommentsRequest;
import com.cheshang8.app.network.CommentsRequest.Result;



import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class DetailCommentFragment extends Fragment{

	public static DetailCommentFragment newInstance(Context context,String shop_id){
		DetailCommentFragment fragment  = new DetailCommentFragment();
		fragment.shop_id = shop_id;
		return fragment;
	}
	
	private ListView listView;
	
	private String shop_id;
	
	private ViewHolder holder;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.detail_comment_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		listView = (ListView) view.findViewById(R.id.listview);
		View header = LayoutInflater.from(getActivity()).inflate(R.layout.detail_comment_header, null);
		holder = new ViewHolder(header);
		listView.addHeaderView(header);
		
		request();
		
	}
	
	private static class ViewHolder{
		
		private TextView star;
		private TextView count;
		public ViewHolder(View view) {
			star = (TextView) view.findViewById(R.id.star);
			count = (TextView) view.findViewById(R.id.count);
		}
		public void setModel(int star,int count){
			this.star.setText("总评分："+star+"分");
			this.count.setText("评价人数："+count+"人");
		}
		
	}

	private void request() {
		CommentsRequest request = new CommentsRequest(shop_id);
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				CommentsRequest.Result result = (Result) object;
				holder.setModel(result.getRating(), result.getCount());
				DetailCommentAdapter adapter = new DetailCommentAdapter(getActivity(), Result.toList(result.getList()));
				listView.setAdapter(adapter);
			}
		});
		
		
		
	}
	
}
