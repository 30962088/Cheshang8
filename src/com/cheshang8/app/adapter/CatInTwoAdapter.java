package com.cheshang8.app.adapter;

import java.util.List;



import com.cheshang8.app.R;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

public class CatInTwoAdapter extends BaseAdapter{
	
	public static class Model{
		private int id;
		private String name;
		private int count;
		public Model(int id, String name, int count) {
			super();
			this.id = id;
			this.name = name;
			this.count = count;
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		
		
		
	}
	
	private Context context;
	
	private List<Model> list;

	
	
	public CatInTwoAdapter(Context context, List<Model> list) {
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
					R.layout.cat_in_tow_layout, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		
		holder.name.setText(model.name+"("+model.count+")");
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private TextView name;
		
		public ViewHolder(View view) {

			name = (TextView) view.findViewById(R.id.name);
		}
		
	}
	
	

}
