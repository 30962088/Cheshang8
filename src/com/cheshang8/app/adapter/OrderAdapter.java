package com.cheshang8.app.adapter;

import java.util.List;

import com.cheshang8.app.CommentListActivity;
import com.cheshang8.app.PayActivity;
import com.cheshang8.app.PublishCommentActivity;
import com.cheshang8.app.R;
import com.cheshang8.app.SubmitActivity;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.database.OrderField;
import com.cheshang8.app.database.OrderField.Callback2;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.cheshang8.app.utils.BitmapLoader;
import com.mengle.lib.wiget.ConfirmDialog;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
			已评价("已评价","查看评价",Color.parseColor("#73e048")),
			退款中("退款中",null,Color.parseColor("#f49e17")),
			退款完成("退款完成",null,Color.parseColor("#898989")),
			退款失败("退款失败",null,Color.parseColor("#ff0000"));
			
			private String id;
			
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
		private String shop_id;
		private String thumbnail;
		private String time;
		private String name;
		private String type;
		private int price;
		private String no;
		private Status status;
		
		
		

		
		
		public Model(String shop_id, String thumbnail, String time,
				String name, String type, int price, String no, Status status) {
			super();
			this.shop_id = shop_id;
			this.thumbnail = thumbnail;
			this.time = time;
			this.name = name;
			this.type = type;
			this.price = price;
			this.no = no;
			this.status = status;
		}
		public String getNo() {
			return no;
		}
		public Status getStatus() {
			return status;
		}
		public String getShop_id() {
			return shop_id;
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
		final Model model = list.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.order_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
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
			holder.status_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					OrderField.getOrder(model.no, new Callback2() {
						
						@Override
						public void callback2(final Result result) {
							if(model.status == Status.待支付){
								PayActivity.open(context, result);
							}else if(model.status == Status.已完成){
								PublishCommentActivity.open(context,new PublishCommentActivity.Params(model.getShop_id(),model.getNo()));
							}else if(model.status == Status.已评价){
								CommentListActivity.open(context, result.getShop().getId());
							}else if(model.status == Status.待体验){
								//状态修改成退款完成
								ConfirmDialog.open(context, "确认", "是否要退款？", new ConfirmDialog.OnClickListener() {
									
									@Override
									public void onPositiveClick() {
										result.getOrder().setStatus(Status.退款中);
										
										OrderField.update(result);
										model.status = Status.退款中;
										notifyDataSetChanged();
									}
									@Override
									public void onNegativeClick() {
									}
								});
								
							}
							
						}
					});
					
					
				}
			});
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
		private View container;
		
		private View sep;
		public ViewHolder(View view) {

			thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
			time = (TextView) view.findViewById(R.id.time);
			name = (TextView) view.findViewById(R.id.name);
			type = (TextView) view.findViewById(R.id.type);
			price = (TextView) view.findViewById(R.id.price);
			no = (TextView) view.findViewById(R.id.no);
			status = (TextView) view.findViewById(R.id.status);
			status_btn = (TextView) view.findViewById(R.id.status_btn);
			container = view.findViewById(R.id.container);
			sep = view.findViewById(R.id.sep);
		}

	}

}
