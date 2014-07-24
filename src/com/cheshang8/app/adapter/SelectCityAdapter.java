package com.cheshang8.app.adapter;

import java.util.List;

import com.cheshang8.app.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SelectCityAdapter extends BaseAdapter{

	public static class Model{
		
		public static class Col{
			private int id;
			private String name;
			public Col(int id, String name) {
				super();
				this.id = id;
				this.name = name;
			}
			
		}
		
		private List<Col> list;
		
		private Integer selected;

		public Model(List<Col> list, Integer selected) {
			super();
			this.list = list;
			this.selected = selected;
		}
		
		
		
	}
	
	private Context context;
	
	private Model model;
	
	
	
	public SelectCityAdapter(Context context, Model model) {
		super();
		this.context = context;
		this.model = model;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return model.list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return model.list.size();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.city_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.container.setBackgroundColor(Color.WHITE);
		
		int border;
		if(position == 0){
			holder.sep.setVisibility(View.VISIBLE);
			border = R.drawable.border_2;
		}else if(position == model.list.size() -1){
			border = R.drawable.border_3;
			holder.sep.setVisibility(View.GONE);
		}else{
			border = R.drawable.border_1;
			holder.sep.setVisibility(View.VISIBLE);
		}
		holder.container.setBackgroundResource(border);
	/*	if(position == 0){
//			holder.container .setBackgroundColor(Color.RED);
			holder.container.setBackgroundResource(R.drawable.bg_1_t);
		}else if(position == model.list.size() -1 ){
			holder.container.setBackgroundResource(R.drawable.bg_1_b);
		}else{
			holder.container.setBackgroundResource(R.drawable.bg_1);
		}
		*/
		
		holder.name.setText(model.list.get(position).name);
		holder.selected.setVisibility(View.GONE);
		if(model.selected == position){
			holder.selected.setVisibility(View.VISIBLE);
		}
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private TextView name;
		
		private View selected;
		
		private View container;
		
		private View sep;

		public ViewHolder(View view) {
			container = view.findViewById(R.id.container);
			name = (TextView) view.findViewById(R.id.name);
			selected = view.findViewById(R.id.selected);
			sep = view.findViewById(R.id.sep);
			
		}
		
		
		
	}

}
