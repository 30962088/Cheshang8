package com.cheshang8.app.fragment;


import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.cheshang8.app.adapter.DetailCommentAdapter;
import com.cheshang8.app.adapter.DetailCommentAdapter.Model;



import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class DetailCommentFragment extends Fragment{

	public static DetailCommentFragment newInstance(Context context){
		DetailCommentFragment fragment  = new DetailCommentFragment();
		
		return fragment;
	}
	
	private ListView listView;
	
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
		listView.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.detail_comment_header, null));
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init() throws Exception {
		List<Model> list = new Gson().fromJson(
				IOUtils.toString(getActivity().getAssets().open(
						"datas/detail_comment.json")),
				new TypeToken<List<Model>>() {
				}.getType());
		DetailCommentAdapter adapter = new DetailCommentAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		
	}
	
}
