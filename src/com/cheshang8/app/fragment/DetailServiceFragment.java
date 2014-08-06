package com.cheshang8.app.fragment;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.DetailActivity;
import com.cheshang8.app.R;
import com.cheshang8.app.ServiceActivity;
import com.cheshang8.app.adapter.DetailServiceAdapter;
import com.cheshang8.app.adapter.DetailServiceAdapter.Model;
import com.cheshang8.app.adapter.DetailServiceAdapter.Model.Col;
import com.cheshang8.app.adapter.DetailServiceAdapter.Render;
import com.cheshang8.app.network.CategoriesRequest.Result;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DetailServiceFragment extends Fragment {

	public static DetailServiceFragment newInstance(Context context,
			List<Model> list,String shop_id,boolean forService) {
		DetailServiceFragment fragment = new DetailServiceFragment();
		Bundle bundle = new Bundle();
		bundle.putString("list", new Gson().toJson(list));
		bundle.putString("shop_id", shop_id);
		bundle.putBoolean("forService", forService);
		fragment.setArguments(bundle);
		return fragment;
	}

	private String shop_id;
	
	private ListView listView;

	private List<Model> list;
	
	private DetailServiceAdapter adapter;
	
	private boolean forService;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		list = new Gson().fromJson(bundle.getString("list"),
				new TypeToken<List<Model>>() {
				}.getType());
		shop_id = bundle.getString("shop_id");
		forService = bundle.getBoolean("forService");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.detail_service_layout, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		listView = (ListView) view.findViewById(R.id.listview);

		init();

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Render render = adapter.getItem(position);
				if(render.getType() == DetailServiceAdapter.TYPE_MAIN){
					ServiceActivity.open(getActivity(),render.getId(),shop_id);
				}
				

			}
		});
	}

	private void init(){

		adapter = new DetailServiceAdapter(getActivity(),
				list);
		listView.setAdapter(adapter);
		if(forService){
			if(list.size() > 0){
				List<Col> cols = list.get(0).getList();
				if(cols.size()>0){
					ServiceActivity.open(getActivity(),cols.get(0).getId(),shop_id);
				}
			}
		}
		
		
	}

}
