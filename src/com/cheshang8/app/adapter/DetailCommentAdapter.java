package com.cheshang8.app.adapter;

import java.util.List;



import com.cheshang8.app.R;
import com.cheshang8.app.utils.BitmapLoader;
import com.cheshang8.app.widget.CarStarView;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.TextView;

public class DetailCommentAdapter extends BaseAdapter{
	
	public static class Model{
		private String icon;
		private String type;
		private int star;
		private String phone;
		private String time;
		private String detail;
		private List<String> icons;
		public Model(String icon, String type, int star, String phone,
				String time, String detail, List<String> icons) {
			super();
			this.icon = icon;
			this.type = type;
			this.star = star;
			this.phone = phone;
			this.time = time;
			this.detail = detail;
			this.icons = icons;
		}
		
		
	}
	
	private Context context;
	
	private List<Model> list;

	
	
	public DetailCommentAdapter(Context context, List<Model> list) {
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
					R.layout.detail_comment_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		
		BitmapLoader.displayImage(context, model.icon, holder.icon);
		
		holder.type.setText(model.type);
		
		holder.phone.setText(model.phone);
		
		holder.time.setText(model.time);
		
		holder.detail.setText(model.detail);
		
		holder.star.setStar(model.star);
		
		if(model.icons == null && model.icons.size() == 0){
			holder.gridView.setVisibility(View.GONE);
		}else{
			holder.gridView.setAdapter(new ImageListAdapter(context, model.icons));
			holder.gridView.setVisibility(View.VISIBLE);
		}
		
		return convertView;
	}
	
	private static class ImageListAdapter extends BaseAdapter{
		
		private Context context;
		
		private List<String> list;
		
		
		
		public ImageListAdapter(Context context, List<String> list) {
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
			String icon = list.get(position);
			if(convertView == null){
				convertView = LayoutInflater.from(context).inflate(
						R.layout.detail_service_img, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			BitmapLoader.displayImage(context, icon, holder.icon);
			
			return convertView;
		}
		
		private static class ViewHolder{
			private ImageView icon;
			public ViewHolder(View view) {
				icon = (ImageView) view.findViewById(R.id.icon);
			}
		}
		
	}
	
	private static class ViewHolder{
		
		private ImageView icon;
		
		private TextView type;
		
		private CarStarView star;
		
		private TextView phone;
		
		private TextView time;
		
		private TextView detail;
		
		private GridView gridView;
		
		public ViewHolder(View view) {

			icon = (ImageView) view.findViewById(R.id.icon);
			type = (TextView) view.findViewById(R.id.type);
			star = (CarStarView) view.findViewById(R.id.star);
			phone = (TextView) view.findViewById(R.id.phone);
			time = (TextView) view.findViewById(R.id.time);
			detail = (TextView) view.findViewById(R.id.detail);
			gridView = (GridView) view.findViewById(R.id.gridview);
		}
		
	}
	
	

}
