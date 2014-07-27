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


public class MsgListAdapter extends BaseAdapter{
	
	public static class Model{
		
		private String title;
		
		private String desc;
		
		private String from;
		
		private String time;

		private boolean isNew;
		
		
		
		
	}
	
	private Context context;
	
	private List<Model> list;

	
	
	public MsgListAdapter(Context context, List<Model> list) {
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
					R.layout.msg_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		if(model.isNew){
			holder.title.setSelected(true);
		}else{
			holder.title.setSelected(false);
		}
		
		int border;
		if(position == 0){
			holder.sep.setVisibility(View.VISIBLE);
			border = R.drawable.border_2;
		}else if(position == list.size() -1){
			border = R.drawable.border_3;
			holder.sep.setVisibility(View.GONE);
		}else{
			border = R.drawable.border_1;
			holder.sep.setVisibility(View.VISIBLE);
		}
		holder.container.setBackgroundResource(border);
		
		holder.title.setText(model.title);
		
		holder.desc.setText(model.desc);
		
		holder.from.setText("来自  "+model.from);
		
		holder.time.setText(model.time);
		
		return convertView;
	}
	
	private static class ViewHolder{
		
		private TextView title;
		
		private TextView desc;
		
		private TextView from;
		
		private TextView time;
		private View container;
		
		private View sep;
		public ViewHolder(View view) {

			title = (TextView) view.findViewById(R.id.title);

			desc = (TextView) view.findViewById(R.id.desc);

			from = (TextView) view.findViewById(R.id.from);
			
			time = (TextView) view.findViewById(R.id.time);
			container = view.findViewById(R.id.container);
			sep = view.findViewById(R.id.sep);
		}
		
	}
	
	

}
