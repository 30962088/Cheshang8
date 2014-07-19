package com.cheshang8.app.adapter;

import java.util.List;

import com.cheshang8.app.R;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.utils.BitmapLoader;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {

	

	public static class Model {
		
		public enum Status {
			待支付("待支付","立即支付",Color.parseColor("#ff0000")),
			待体验("待体验","立即退款",Color.parseColor("#f49e17")),
			已完成("已完成","立即评价",Color.parseColor("#73e048")),
			已退款("已退款",null,Color.parseColor("#898989"));
			
			private String btn;
			
			private String text;

			private int color;

			private Status( String text, String btn,int color) {
				this.btn = btn;
				this.text = text;
				this.color = color;
			}




			public String getBtn() {
				return btn;
			}
			public int getColor() {
				return color;
			}
			public String getText() {
				return this.text;
			}

		}
		private String thumbnail;
		private String time;
		private String name;
		private String type;
		private int price;
		private String no;
		private int status;
		public Model() {
			// TODO Auto-generated constructor stub
		}
		public Status getStatus() {
			return new Status[]{Status.待支付,Status.待体验,Status.已完成,Status.已退款}[status];
		}
		

	}

	private Context context;

	private List<Model> list;

	public OrderAdapter(Context context, List<Model> list) {
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
					R.layout.order_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Status status = model.getStatus();
		BitmapLoader.displayImage(context, model.thumbnail, holder.thumbnail);
		
		holder.time.setText("订单日期："+model.time);
		
		holder.name.setText(model.name);
		
		holder.type.setText(""+model.type);
		
		holder.no.setText("订单编号："+model.no);
		
		holder.price.setText("￥"+model.price);
		
		holder.status.setText(status.getText());
		
		holder.status.setTextColor(status.getColor());
		
		if(status.getBtn() == null){
			holder.status_btn.setVisibility(View.GONE);
		}else{
			holder.status_btn.setVisibility(View.VISIBLE);
			holder.status_btn.setText(status.getBtn());
			holder.status_btn.setBackgroundColor(status.getColor());
		}
	
		

		return convertView;
	}

	private static class ViewHolder {

		private ImageView thumbnail;
		private TextView time;
		private TextView name;
		private TextView type;
		private TextView price;
		private TextView no;
		private TextView status;
		private TextView status_btn;

		public ViewHolder(View view) {

			thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
			time = (TextView) view.findViewById(R.id.time);
			name = (TextView) view.findViewById(R.id.name);
			type = (TextView) view.findViewById(R.id.type);
			price = (TextView) view.findViewById(R.id.price);
			no = (TextView) view.findViewById(R.id.no);
			status = (TextView) view.findViewById(R.id.status);
			status_btn = (TextView) view.findViewById(R.id.status_btn);
			
		}

	}

}
