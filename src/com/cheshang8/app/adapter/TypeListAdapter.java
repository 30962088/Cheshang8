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


public class TypeListAdapter extends BaseAdapter{
	
	public static class Model{
		
		private String id;
		
		private String text;

		public Model(String id, String text) {
			super();
			this.id = id;
			this.text = text;
		}

		public String getId() {
			return id;
		}
		
		public String getText() {
			return text;
		}
		
		
		
		
	}
	
	private Context context;
	
	private List<Model> list;

	
	
	public TypeListAdapter(Context context, List<Model> list) {
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
					R.layout.type_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		
		holder.text.setText(model.text);
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private TextView text;
		
		public ViewHolder(View view) {

			text = (TextView) view.findViewById(R.id.text);
		}
		
	}
	
	

}
