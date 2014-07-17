package com.cheshang8.app.fragment;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailMainFragment extends Fragment{

	public static DetailMainFragment newInstance(Context context){
		DetailMainFragment fragment  = new DetailMainFragment();
		try {
			Model model = new Gson().fromJson(
					IOUtils.toString(context.getAssets().open(
							"datas/detail_main.json")),Model.class);
			fragment.model = model;
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fragment;
	}
	
	public static class Model{
		private String title;
		private String address;
		private String time;
		private String phone;
		private String range;
		private String detail;
		public Model(String title, String address, String time, String phone,
				String range, String detail) {
			super();
			this.title = title;
			this.address = address;
			this.time = time;
			this.phone = phone;
			this.range = range;
			this.detail = detail;
		}
		public Model() {
			
		}
		
	}
	
	private Model model;
	
	private TextView title;
	
	private TextView address;
	
	private TextView time;
	
	private TextView phone;
	
	private TextView range;
	
	private TextView detail;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.detail_main_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		title = (TextView) view.findViewById(R.id.title);
		address = (TextView) view.findViewById(R.id.address);
		time = (TextView) view.findViewById(R.id.time);
		phone = (TextView) view.findViewById(R.id.phone);
		range = (TextView) view.findViewById(R.id.range);
		detail = (TextView) view.findViewById(R.id.detail);
		init();
	}

	private void init() {
		title.setText(model.title);
		address.setText(model.address);
		time.setText(model.time);
		phone.setText(model.phone);
		range.setText(model.range);
		detail.setText(model.detail);
		
	}
	
}
