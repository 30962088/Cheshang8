package com.cheshang8.app.fragment;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.cheshang8.app.SearchActivity;
import com.cheshang8.app.SelectCityActivity;
import com.cheshang8.app.adapter.TabIndexAdapter;
import com.cheshang8.app.adapter.TabIndexAdapter.Model;
import com.cheshang8.app.widget.TabIndexHeaderView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

public class TabIndexFragment extends Fragment{

	public static TabIndexFragment newInstance(){
		TabIndexFragment fragment = new TabIndexFragment();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.tab_index_layout, null);
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		view.findViewById(R.id.btn_city).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SelectCityActivity.open(getActivity());
				
			}
		});
		
		view.findViewById(R.id.search_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SearchActivity.open(getActivity());
				
			}
		});
		
		ListView listView = (ListView) view.findViewById(R.id.listview);
		
		listView.addHeaderView(new TabIndexHeaderView(getActivity(), this));
		
		try {
			List<Model> list = new Gson().fromJson(
					IOUtils.toString(getActivity().getAssets().open(
							"datas/index_list.json")),
					new TypeToken<List<Model>>() {
					}.getType());
			
			TabIndexAdapter adapter = new TabIndexAdapter(getActivity(), list);
			
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
