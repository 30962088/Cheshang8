package com.cheshang8.app.adapter;

import java.util.List;



import com.cheshang8.app.R;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

public class CatInOneAdapter extends BaseAdapter{
	
	public static class Col{
		
		private String name;
		private int count;
		public Col(String name, int count) {
			super();
			this.name = name;
			this.count = count;
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

	
	

	public CatInOneAdapter(Context context, Model model) {
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
					R.layout.cat_in_one_layout, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		if(model.position == null){
			holder.container.setSelected(false);
		}else if(position == model.position){
			holder.container.setSelected(true);
		}else{
			holder.container.setSelected(false);
		}
		holder.name.setText(col.name+"("+col.count+")");
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private TextView name;
		
		private View container;
		
		public ViewHolder(View view) {
			container = view.findViewById(R.id.container);
			name = (TextView) view.findViewById(R.id.name);
		}
		
	}
	
	

}
