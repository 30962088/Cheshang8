package com.cheshang8.app.adapter;

import java.util.List;



import com.cheshang8.app.R;
import com.cheshang8.app.network.ShopsRequest.Params.Sort;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

public class CatSortAdapter extends BaseAdapter{
	
	public static class Model{
		
		private String name;
		
		private int type;
		
		public Model(String name) {
			super();
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public Sort getSort(){
			Sort sort = Sort.DEFAULT;
			switch (type) {
			case 1:
				sort = Sort.DEFAULT;
				break;
			case 2:
				sort = Sort.PRICE;
				break;
			case 3:
				sort = Sort.STAR;
				break;
			
			}
			return sort;
		}
		
		
	}
	
	private Context context;
	
	private List<Model> list;

	
	
	public CatSortAdapter(Context context, List<Model> list) {
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
					R.layout.sort_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(model.name);
		
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private TextView name;
		
		public ViewHolder(View view) {

			name = (TextView) view.findViewById(R.id.name);
		}
		
	}
	
	

}
