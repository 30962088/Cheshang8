package com.cheshang8.app.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.DetailActivity;
import com.cheshang8.app.R;
import com.cheshang8.app.SearchActivity;
import com.cheshang8.app.SelectCityActivity;
import com.cheshang8.app.adapter.CatIndexAdapter;
import com.cheshang8.app.adapter.TabIndexAdapter;
import com.cheshang8.app.adapter.TabIndexAdapter.Model;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.IndexDataRequest;
import com.cheshang8.app.network.IndexDataRequest.Result;
import com.cheshang8.app.network.IndexDataRequest.Result.Promotion;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	
	private ListView listView;
	
	private TabIndexHeaderView headerView;
	
	private TabIndexAdapter adapter;
	
	private List<Model> list = new ArrayList<TabIndexAdapter.Model>();
	
	
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
		
		headerView = new TabIndexHeaderView(getActivity(), this);
		adapter = new TabIndexAdapter(getActivity(), list);
		listView = (ListView) view.findViewById(R.id.listview);
		
		listView.addHeaderView(headerView);
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Model model = list.get(position-1);
				DetailActivity.open(getActivity(), model.getId());
				
			}
		});
		
		request();
		
	
	}
	
	private void request(){
		IndexDataRequest request = new IndexDataRequest(new IndexDataRequest.Params(1));
		
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				IndexDataRequest.Result result = (Result) object;
				List<SliderFragment.Model> sliderList = Result.Banner.toList(result.getBanners());
				List<CatIndexAdapter.Model> catList = Result.Category.toList(result.getCategories());
				headerView.setData(sliderList, catList,Promotion.toList(result.getPromotions()));
				
				list.clear();
				list.addAll(Result.Recommend.toList(result.getRecommends()));
				adapter.notifyDataSetChanged();
				
				
				
				
			}
		});
		
		
	}
	
}
