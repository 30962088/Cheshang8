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
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyCarListAdapter extends BaseAdapter{
	
	public static class Model{
		private String icon;
		private String name;
		private boolean star;
		private float percent;
		private Model(String icon, String name, boolean star, float percent) {
			super();
			this.icon = icon;
			this.name = name;
			this.star = star;
			this.percent = percent;
		}
		
		
		
	}
	
	private Context context;
	
	private List<Model> list;

	
	
	public MyCarListAdapter(Context context, List<Model> list) {
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
					R.layout.my_car_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		BitmapLoader.displayImage(context, model.icon, holder.icon);
		
		holder.name.setText(model.name);
		
		holder.percent.setText(""+(int)(model.percent*100)+"%");
		
		if(model.star){
			holder.star.setVisibility(View.VISIBLE);
		}else{
			holder.star.setVisibility(View.GONE);
		}
		
		holder.seekbar.setProgress((int)(model.percent*100));
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private ImageView icon;
		private TextView name;
		private View star;
		private TextView percent;
		private ProgressBar seekbar;
		
		public ViewHolder(View view) {
			icon = (ImageView) view.findViewById(R.id.icon);
			name = (TextView) view.findViewById(R.id.name);
			star = view.findViewById(R.id.star);
			percent = (TextView) view.findViewById(R.id.percent);
			seekbar = (ProgressBar) view.findViewById(R.id.seekbar);
			
		}
		
	}
	
	

}
