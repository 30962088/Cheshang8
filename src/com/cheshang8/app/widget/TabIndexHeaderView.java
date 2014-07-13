package com.cheshang8.app.widget;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.cheshang8.app.adapter.CatIndexAdapter;
import com.cheshang8.app.adapter.CatIndexAdapter.Model;
import com.cheshang8.app.fragment.SliderFragment;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.GridView;

public class TabIndexHeaderView extends FrameLayout{

	private Fragment fragment;
	
	public TabIndexHeaderView(Context context,Fragment fragment) {
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
		 LayoutInflater.from(getContext()).inflate(
					R.layout.tab_index_header, this);
		 fragment.getChildFragmentManager().beginTransaction().replace(R.id.slider_container, SliderFragment.newInstance()).commit();
		 
		 List<Model> list = new Gson().fromJson(
					IOUtils.toString(getContext().getAssets().open(
							"datas/index_cat.json")),
					new TypeToken<List<Model>>() {
					}.getType());
		 
		 GridView gridView =  (GridView) findViewById(R.id.gridview);
		 
		 gridView.setAdapter(new CatIndexAdapter(getContext(), list));
		 
	}

}
