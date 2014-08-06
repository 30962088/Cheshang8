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


public class DetailShopFragment extends Fragment{

	public static DetailShopFragment newInstance(Context context){
		DetailShopFragment fragment  = new DetailShopFragment();
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.detail_shop_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		
	}
	
	
	
}
