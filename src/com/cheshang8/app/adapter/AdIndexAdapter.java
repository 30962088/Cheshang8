package com.cheshang8.app.adapter;

import java.util.List;



import com.cheshang8.app.R;
import com.cheshang8.app.utils.BitmapLoader;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import android.widget.ImageView;


public class AdIndexAdapter extends BaseAdapter{
	
	public static class Model{
		private String id;
		private String img;

		public Model(String id, String img) {
			this.id = id;
			this.img = img;
		}
		public String getId() {
			return id;
		}
		
		
	}
	
	private Context context;
	
	private List<Model> list;

	
	
	public AdIndexAdapter(Context context, List<Model> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		Model model = list.get(position);
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.ad_item, null);
			
			
		}
		
		
		BitmapLoader.displayImage(context, model.img, (ImageView)convertView);
		
		
		return convertView;
	}
	

	
	

}
