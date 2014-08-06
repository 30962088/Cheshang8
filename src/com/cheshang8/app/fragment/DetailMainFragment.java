package com.cheshang8.app.fragment;



import java.util.ArrayList;

import com.cheshang8.app.MapActivity;
import com.cheshang8.app.R;
import com.google.gson.Gson;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DetailMainFragment extends Fragment implements OnClickListener{

	public static DetailMainFragment newInstance(Context context,Model model){
		DetailMainFragment fragment  = new DetailMainFragment();
		Bundle bundle = new Bundle();
		bundle.putString("model", new Gson().toJson(model));
		fragment.setArguments(bundle);
		return fragment;
	}
	
	public static class Model{
		private String title;
		private String address;
		private String time;
		private String phone;
		private String range;
		private String detail;
		private double x;
		private double y;
		private float star;
		private int price;
		
		

	


		public Model(String title, String address, String time, String phone,
				String range, String detail, double x, double y, float star,
				int price) {
			super();
			this.title = title;
			this.address = address;
			this.time = time;
			this.phone = phone;
			this.range = range;
			this.detail = detail;
			this.x = x;
			this.y = y;
			this.star = star;
			this.price = price;
		}

		public Model() {
			
		}
		
		public MapActivity.Model toMapModel(){
			return new MapActivity.Model(x, y, title, star, price);
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
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		model = new Gson().fromJson(bundle.getString("model"), Model.class) ;
	}
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
		view.findViewById(R.id.address).setOnClickListener(this);
		init();
	}

	private void init() {
		title.setText(model.title);
		address.setText(model.address);
		time.setText("营业时间："+model.time);
		phone.setText("预约电话："+model.phone);
		range.setText(model.range);
		detail.setText(model.detail);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.address:
			onMap();
			break;

		default:
			break;
		}
		
	}

	private void onMap() {
		MapActivity.open(getActivity(), new ArrayList<MapActivity.Model>(){{
			add(model.toMapModel());
		}});
		
	}
	
}
