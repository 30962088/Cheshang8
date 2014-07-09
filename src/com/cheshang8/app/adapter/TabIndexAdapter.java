package com.cheshang8.app.adapter;

import java.util.List;

import com.cheshang8.app.R;
import com.cheshang8.app.utils.BitmapLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TabIndexAdapter extends BaseAdapter{

	public static class Model{
		private String group;
		private String img;
		private int star;
		private String title;
		private String desc;
		private String company;
		private String address;
		private float location;
		public Model(String group, String img, int star, String title,
				String desc, String company, String address, float location) {
			super();
			this.group = group;
			this.img = img;
			this.star = star;
			this.title = title;
			this.desc = desc;
			this.company = company;
			this.address = address;
			this.location = location;
		}
	}
	
	private Context context;
	
	private List<Model> list;
	
	
	
	public TabIndexAdapter(Context context, List<Model> list) {
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
		ViewHolder holder = null;
		Model model = list.get(position);
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.tab_index_list_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(model.group == null){
			holder.group.setVisibility(View.GONE);
		}else{
			holder.group.setVisibility(View.VISIBLE);
			holder.group.setText(model.group);
		}
		
		BitmapLoader.displayImage(context, model.img, holder.img);
		
		holder.title.setText(model.title);
		
		holder.desc.setText(model.desc);
		
		holder.company.setText(model.company);
		
		holder.address.setText(model.address);
		
		holder.location.setText(""+model.location);
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private TextView group;
		
		private ImageView img;
		
		private TextView title;
		
		private TextView desc;
		
		private TextView company;
		
		private TextView address;
		
		private TextView location;
		
		public ViewHolder(View view) {
			group = (TextView) view.findViewById(R.id.group);
			img = (ImageView) view.findViewById(R.id.img);
			title = (TextView) view.findViewById(R.id.title);
			desc = (TextView) view.findViewById(R.id.desc);
			company = (TextView) view.findViewById(R.id.company);
			address = (TextView) view.findViewById(R.id.address);
			location = (TextView) view.findViewById(R.id.location);
		}
		
	}
	

}
