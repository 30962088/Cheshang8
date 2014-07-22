package com.cheshang8.app.widget;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.cheshang8.app.adapter.CatInOneAdapter;
import com.cheshang8.app.adapter.CatInTwoAdapter;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.DistrictsRequest;
import com.cheshang8.app.network.DistrictsRequest.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class CatTwoDialog implements OnClickListener{
	
	private PopupWindow mPopupWindow;
	
	private Context context;
	
	private View parent;
	
	private ListView leftListView;
	
	private ListView rightListView;
	
	private CatInOneAdapter oneAdapter;
	
	private CatInTwoAdapter twoAdapter;
	
	private CatInOneAdapter.Model oneModel;
	
	private List<CatInTwoAdapter.Model> twoModel = new ArrayList<CatInTwoAdapter.Model>();
	
	public static class Model{
		public static class Col{
			private int id;
			private String name;
			private int count;
			
			

			public Col(int id, String name, int count) {
				super();
				this.id = id;
				this.name = name;
				this.count = count;
			}



			public CatInTwoAdapter.Model toModel(){
				return new CatInTwoAdapter.Model(id,name,count);
			}
			
		}
		
		private int id;
		
		private String name;
		
		private int count;
		
		private List<Col> list;

		public Model(String name, int count, List<Col> list) {
			super();
			this.name = name;
			this.count = count;
			this.list = list;
		}
		
		public int getCount() {
			return count;
		}
		public int getId() {
			return id;
		}
		public List<Col> getList() {
			return list;
		}
		public String getName() {
			return name;
		}
		
		public void setCount(int count) {
			this.count = count;
		}
		public void setId(int id) {
			this.id = id;
		}
		public void setList(List<Col> list) {
			this.list = list;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public Model() {
			// TODO Auto-generated constructor stub
		}
		
		public CatInOneAdapter.Col toCol(){
			return new CatInOneAdapter.Col(name,count);
		}
		
		public List<CatInTwoAdapter.Model> toModels(){
			List<CatInTwoAdapter.Model> res = new ArrayList<CatInTwoAdapter.Model>();
			for(Col col : list){
				res.add(col.toModel());
			}
			return res;
		}
		
		public static List<CatInOneAdapter.Col> toModel(List<Model> list){
			List<CatInOneAdapter.Col> res = new ArrayList<CatInOneAdapter.Col>();
			for(Model model : list){
				res.add(model.toCol());
			}
			return res;
		}
		
		
		
	}
	
	public CatTwoDialog(Context context,View parent) {
		this.context = context;
		this.parent = parent;
		
		init();
		
	}
	
	private List<Model> list;
	
	private void init() {
	
			
			
		View view = LayoutInflater.from(context).inflate(R.layout.cat_tow_layout,
				null);
	
		
		
		leftListView = (ListView) view.findViewById(R.id.left);
		
		rightListView = (ListView) view.findViewById(R.id.right);
		
		
		mPopupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, true);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
	/*	mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#80b7bbc2")));
		mPopupWindow.setTouchable(true);
		mPopupWindow.setOutsideTouchable(true);
		*/		
	}
	
	public void setData(int id){
		DistrictsRequest request = new DistrictsRequest(new DistrictsRequest.Params(id));
		request.request(new SimpleRequestHandler(){
			@Override
			public void onSuccess(Object object) {
				list = Result.toList( (List<Result>) object);
				initData();
			}
		});
	}
	
	private List<CatInTwoAdapter.Model> models;
	
	private void initData(){
		
		
		oneModel = new CatInOneAdapter.Model(Model.toModel(list));
		
		oneAdapter = new CatInOneAdapter(context, oneModel);
		
		twoAdapter = new CatInTwoAdapter(context, twoModel);
		
		leftListView.setAdapter(oneAdapter);
		
		rightListView.setAdapter(twoAdapter);
		
		leftListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				oneModel.setPosition(position);
				
				twoModel.clear();
				
				Model model = list.get(position);
				models = model.toModels();
				twoModel.addAll(models);
				
				oneAdapter.notifyDataSetChanged();
				
				twoAdapter.notifyDataSetChanged();
				
			}
		});
		rightListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				if(callback != null){
					CatInTwoAdapter.Model model = models.get(position);
					callback.oncallback(model.getName(), model.getId());
					dismiss();
				}
				
			}
		});
	}
	
	private Callback callback;
	
	public void setCallback(Callback callback) {
		this.callback = callback;
	}
	
	public static interface Callback{
		public void oncallback(String name,int id);
	}
	
	private OnStateChange onStateChange;
	
	public void setOnStateChange(final OnStateChange onStateChange) {
		this.onStateChange = onStateChange;
		mPopupWindow.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				onStateChange.ondismiss();
				
			}
		});
	}
	
	public static interface OnStateChange{
		public void ondismiss();
		public void onshow();
	}


	public void show(){
		if(parent != null){
			
			mPopupWindow.showAsDropDown(parent);
			if(onStateChange != null){
				onStateChange.onshow();
			}
		}
	}
	
	public void dismiss(){
		
		mPopupWindow.dismiss();
		
	}
	
	public void toggle(){
		if(!mPopupWindow.isShowing()){
			show();
		}else{
			dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
