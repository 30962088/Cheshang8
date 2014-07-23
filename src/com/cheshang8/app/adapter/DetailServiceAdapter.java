package com.cheshang8.app.adapter;

import java.util.ArrayList;
import java.util.List;

import com.cheshang8.app.R;
import com.cheshang8.app.adapter.DetailServiceAdapter.Model.Col;
import com.cheshang8.app.utils.BitmapLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

public class DetailServiceAdapter extends BaseAdapter {

	public static final int TYPE_MAIN = 0;

	public static final int TYPE_SEPARATOR = 1;

	public static final int TYPE_COUNT = 2;

	public static class Model {

		private String id;
		
		private String name;

		private String icon;
		
		private List<Col> list;

		public static class Col {

			private String id;
			
			private String name;

			private int price;

			public Col(String id, String name, int price) {
				this.id = id;
				this.name = name;
				this.price = price;
			}
			
			

		}

		public Model(String name, String icon, List<Col> list) {
			super();
			this.name = name;
			this.icon = icon;
			this.list = list;
		}
		
		

	}
	
	public static class Render{
		
		private String id;
		
		private String name;
		
		private String icon;
		
		private Integer price;
		
		private int type;

		
		
		public Render(String id, String name, String icon, Integer price,
				int type) {
			super();
			this.id = id;
			this.name = name;
			this.icon = icon;
			this.price = price;
			this.type = type;
		}



		public int getType() {
			return type;
		}
		
		public String getId() {
			return id;
		}
	}

	private Context context;

	private List<Model> list;
	
	private List<Render> renders;

	public DetailServiceAdapter(Context context, List<Model> list) {
		super();
		this.context = context;
		this.list = list;
		toRenders();
	}
	
	public List<Render> getRenders() {
		return renders;
	}
	
	private void toRenders(){
		renders = new ArrayList<DetailServiceAdapter.Render>();
		for(Model model : list){
			renders.add(new Render(model.id, model.name, model.icon, null, TYPE_SEPARATOR));
			for(Col col : model.list){
				renders.add(new Render(col.id,col.name, null, col.price, TYPE_MAIN));
			}
		}
	}

	@Override
	public int getCount() {
		return renders.size();
	}

	@Override
	public Render getItem(int position) {
		// TODO Auto-generated method stub
		return renders.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return TYPE_COUNT;
	}
	
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return ((Render)renders.get(position)).type;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		Render render = renders.get(position);
		int type = getItemViewType(position);
		if (convertView == null) {
			
			switch (type) {
			case TYPE_SEPARATOR:
				convertView = LayoutInflater.from(context).inflate(
						R.layout.detail_service_title, null);
				convertView.setTag(new SepHolder(convertView));
				break;

			case TYPE_MAIN:
				convertView = LayoutInflater.from(context).inflate(
						R.layout.detail_service_item, null);
				convertView.setTag(new MainHolder(convertView));
				break;
			}
			
			
		}
		
		switch (type) {
		case TYPE_SEPARATOR:
			SepHolder sepHolder = (SepHolder) convertView.getTag();
			BitmapLoader.displayImage(context,render.icon, sepHolder.icon);
			sepHolder.name.setText(render.name);
			break;

		case TYPE_MAIN:
			MainHolder mainHolder = (MainHolder) convertView.getTag();
			mainHolder.name.setText(render.name);
			mainHolder.price.setText(""+render.price);
			break;
		}
		

		return convertView;
	}

	private static class SepHolder {

		private TextView name;
		
		private ImageView icon;

		public SepHolder(View view) {

			name = (TextView) view.findViewById(R.id.name);
			
			icon = (ImageView) view.findViewById(R.id.icon);
			
		}

	}
	
	private static class MainHolder {

		private TextView name;
		
		private TextView price;

		public MainHolder(View view) {

			name = (TextView) view.findViewById(R.id.name);
			
			price = (TextView) view.findViewById(R.id.price);
			
		}

	}

}
