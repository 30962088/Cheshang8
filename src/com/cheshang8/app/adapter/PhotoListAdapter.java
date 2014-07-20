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

public class PhotoListAdapter extends BaseAdapter{
	
	public static class Col{
		
		private String icon;

		private Col(String icon) {
			super();
			this.icon = icon;
		}
		
		
		
	}
	
	public static class Model{
		private Integer position;
		private List<Col> list;
		public Model( List<Col> list) {
			super();
			this.list = list;
		}
		
		public void setPosition(Integer position) {
			this.position = position;
		}
		
	}
	
	
	
	private Context context;
	
	private Model model;

	
	

	public PhotoListAdapter(Context context, Model model) {
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
		return model.list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		Col col = model.list.get(position);
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.photo_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		BitmapLoader.displayImage(context, col.icon, holder.icon);
		
		if(model.position == null){
			holder.selected.setVisibility(View.GONE);
		}else if(position == model.position){
			holder.selected.setVisibility(View.VISIBLE);
		}else{
			holder.selected.setVisibility(View.GONE);
		}
		
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private ImageView icon;
		
		private View selected;
		
		public ViewHolder(View view) {
			selected = view.findViewById(R.id.selected);
			icon = (ImageView) view.findViewById(R.id.icon);
		}
		
	}
	
	

}
