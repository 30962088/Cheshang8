package com.cheshang8.app.widget;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.cheshang8.app.TypeListActivity;
import com.cheshang8.app.adapter.AdIndexAdapter;
import com.cheshang8.app.adapter.CatIndexAdapter;
import com.cheshang8.app.adapter.CatIndexAdapter.Model;
import com.cheshang8.app.fragment.SliderFragment;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;

public class TabIndexHeaderView extends FrameLayout {

	private Fragment fragment;

	private GridView gridView;
	
	private GridView adGridView;

	public TabIndexHeaderView(Context context, Fragment fragment) {
		super(context);
		this.fragment = fragment;
		try {
			init();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init() throws JsonSyntaxException, IOException {
		LayoutInflater.from(getContext()).inflate(R.layout.tab_index_header,
				this);

	}

	public void setData(List<SliderFragment.Model> models,final List<CatIndexAdapter.Model> models2,List<AdIndexAdapter.Model> models3) {
		fragment.getChildFragmentManager()
				.beginTransaction()
				.replace(R.id.slider_container,
						SliderFragment.newInstance(models)).commit();
		
		gridView = (GridView) findViewById(R.id.gridview);

		gridView.setAdapter(new CatIndexAdapter(getContext(), models2));

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				CatIndexAdapter.Model model = models2.get(position);
				TypeListActivity.open(getContext(),model.getId());

			}
		});
		
		adGridView = (GridView) findViewById(R.id.adgrid);
		
		adGridView.setAdapter(new AdIndexAdapter(getContext(), models3));
		
	}

}
