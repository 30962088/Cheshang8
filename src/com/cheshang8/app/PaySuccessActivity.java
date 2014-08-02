package com.cheshang8.app;


import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.AdIndexAdapter;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.adapter.TabIndexAdapter;
import com.cheshang8.app.adapter.TabIndexAdapter.Model;
import com.cheshang8.app.database.OrderField;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.IndexDataRequest;
import com.cheshang8.app.network.IndexDataRequest.Result.Promotion;
import com.cheshang8.app.network.OrdersRequest.Result;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class PaySuccessActivity extends BaseActivity {
	public static void open(Context context,Result result){
		Intent intent = new Intent(context, PaySuccessActivity.class);
		intent.putExtra("result", result);
		context.startActivity(intent);
	}
	private ListView listView;
	private ViewHolder holder;
	private Result result;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		result = (Result) getIntent().getSerializableExtra("result");
		result.getOrder().setStatus(Status.待体验);
		OrderField.update(result);
		context = this;
		setContentView(R.layout.pay_success_layout);
		listView = (ListView)findViewById(R.id.listview);
		holder = new ViewHolder();
		
		
		IndexDataRequest request = new IndexDataRequest(new IndexDataRequest.Params("1"));
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				IndexDataRequest.Result rs = (IndexDataRequest.Result) object;
				
				holder.setModel(new Model(result.toHeader(), IndexDataRequest.Result.Recommend.toList(rs.getRecommends()), Promotion.toList(rs.getPromotions())));
			}
		});
	}
	

	public static class Model{
		public static class Header{
			private String name;
			private int price;
			private String no;
			public Header(String name, int price, String no) {
				super();
				this.name = name;
				this.price = price;
				this.no = no;
			}
			
		}
		private Header header;
		private List<TabIndexAdapter.Model> models;
		private List<AdIndexAdapter.Model> adModels;
		public Model(Header header,
				List<com.cheshang8.app.adapter.TabIndexAdapter.Model> models,
				List<com.cheshang8.app.adapter.AdIndexAdapter.Model> adModels) {
			super();
			this.header = header;
			this.models = models;
			this.adModels = adModels;
			
			
		}
		
		
	}
	
	private Model model;
	
	private class ViewHolder{
		private TextView name;
		private TextView price;
		private TextView no;
		private GridView adgrid;
		public ViewHolder() {
			View view = LayoutInflater.from(context).inflate(R.layout.pay_success_header, null);
			listView.addHeaderView(view);
			findViewById(R.id.display).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
					
				}
			});
			
			findViewById(R.id.again).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					MainActivity.open(context);
					
				}
			});
			
			name = (TextView) view.findViewById(R.id.name);
			price = (TextView) view.findViewById(R.id.price);
			no = (TextView) view.findViewById(R.id.no);
			adgrid = (GridView) view.findViewById(R.id.adgrid);
		}
		public void setModel(final Model model){
			PaySuccessActivity.this.model = model;
			name.setText(model.header.name);
			price.setText(""+model.header.price);
			no.setText("消费编码："+model.header.no);
			listView.setAdapter(new TabIndexAdapter(context, model.models));
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					
					DetailActivity.open(context, model.models.get(position-1).getId(),false);
				}
			});
			adgrid.setAdapter(new AdIndexAdapter(context, model.adModels));
		}
	}
	
	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

	@Override
	public void finish() {
		
		OrderActivity.openClear(context, result.getOrder().getNo());
	}
	
}
