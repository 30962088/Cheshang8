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

public class CatIndexAdapter extends BaseAdapter{
	
	public static class Model{
		private String id;
		private String img;
		private String name;
		public Model(String id, String img, String name) {
			super();
			this.id = id;
			this.img = img;
			this.name = name;
		}
		public String getId() {
			return id;
		}
		
		
	}
	
	private Context context;
	
	private List<Model> list;

	
	
	public CatIndexAdapter(Context context, List<Model> list) {
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
					R.layout.index_cat_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		BitmapLoader.displayImage(context, model.img, holder.img);
		
		holder.name.setText(model.name);
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private ImageView img;
		
		private TextView name;
		
		public ViewHolder(View view) {
			img = (ImageView) view.findViewById(R.id.img);
			name = (TextView) view.findViewById(R.id.name);
		}
		
	}
	
	

}
