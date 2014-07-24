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
		private String img;
		private String title;
		private String company;
		private String location;
		public Model(String img, String title, String company, String location) {
			super();
			this.img = img;
			this.title = title;
			this.company = company;
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
		
		int border;
		if(position == 0){
			holder.sep.setVisibility(View.VISIBLE);
			border = R.drawable.border_2;
		}else if(position == list.size() -1){
			border = R.drawable.border_3;
			holder.sep.setVisibility(View.GONE);
		}else{
			border = R.drawable.border_1;
			holder.sep.setVisibility(View.VISIBLE);
		}
		
		holder.container.setBackgroundResource(border);
		
		BitmapLoader.displayImage(context, model.img, holder.img);
		
		holder.title.setText(model.title);
		
		holder.company.setText(model.company);
		
		holder.location.setText(""+model.location);
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private ImageView img;
		
		private TextView title;
		
		private TextView company;
		
		private TextView location;
		
		private View container;
		
		private View sep;
		
		public ViewHolder(View view) {
			sep = view.findViewById(R.id.sep);
			img = (ImageView) view.findViewById(R.id.img);
			title = (TextView) view.findViewById(R.id.title);
			company = (TextView) view.findViewById(R.id.company);
			location = (TextView) view.findViewById(R.id.location);
			container = view.findViewById(R.id.container);
		}
		
	}
	

}
