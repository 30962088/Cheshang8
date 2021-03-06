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
import android.widget.ImageView;
import android.widget.TextView;

public class SearchItemAdapter extends BaseAdapter {

	public static class Model {
		private String id;
		private String thumbnail;
		private float star;
		private String title;
		private String address;
		private String type;
		private int price;
		private int price_old;
		private int comment;
		private String loc;

		public Model(String id, String thumbnail, float star, String title,
				String address, String type, int price, int price_old,
				int comment, String loc) {
			super();
			this.id = id;
			this.thumbnail = thumbnail;
			this.star = star;
			this.title = title;
			this.address = address;
			this.type = type;
			this.price = price;
			this.price_old = price_old;
			this.comment = comment;
			this.loc = loc;
		}

		public String getId() {
			return id;
		}

	}

	private Context context;

	private List<Model> list;

	public SearchItemAdapter(Context context, List<Model> list) {
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
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.search_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		int border;
		if (position == 0) {
			holder.sep.setVisibility(View.VISIBLE);
			border = R.drawable.border_2;
		} else if (position == list.size() - 1) {
			border = R.drawable.border_3;
			holder.sep.setVisibility(View.GONE);
		} else {
			border = R.drawable.border_1;
			holder.sep.setVisibility(View.VISIBLE);
		}
		holder.container.setBackgroundResource(border);
		BitmapLoader.displayImage(context, model.thumbnail, holder.thumbnail);
		holder.star.setStar(model.star);
		holder.title.setText(model.title);
		holder.address.setText(model.address);
		holder.type.setText("[" + model.type + "]￥");
		holder.price.setText("" + model.price);
		holder.price_old.setText("(" + model.price_old + ")");
		holder.comment.setText("" + model.comment);
		holder.loc.setText("" + model.loc);
		return convertView;
	}

	private static class ViewHolder {
		private ImageView thumbnail;
		private CarStarView star;
		private TextView title;
		private TextView address;
		private TextView type;
		private TextView price;
		private TextView price_old;
		private TextView comment;
		private TextView loc;
		private View container;

		private View sep;

		public ViewHolder(View view) {
			thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
			star = (CarStarView) view.findViewById(R.id.star);
			title = (TextView) view.findViewById(R.id.title);
			address = (TextView) view.findViewById(R.id.address);
			type = (TextView) view.findViewById(R.id.type);
			price = (TextView) view.findViewById(R.id.price);
			price_old = (TextView) view.findViewById(R.id.price_old);
			comment = (TextView) view.findViewById(R.id.comment);
			loc = (TextView) view.findViewById(R.id.loc);
			container = view.findViewById(R.id.container);
			sep = view.findViewById(R.id.sep);
		}
	}

}
