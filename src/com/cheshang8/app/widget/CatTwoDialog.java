package com.cheshang8.app.widget;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.cheshang8.app.adapter.CatInOneAdapter;
import com.cheshang8.app.adapter.CatInTwoAdapter;
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
			private String name;
			private int count;
			
			public CatInTwoAdapter.Model toModel(){
				return new CatInTwoAdapter.Model(name,count);
			}
			
		}
		
		private String name;
		
		private int count;
		
		private List<Col> list;

		public Model(String name, int count, List<Col> list) {
			super();
			this.name = name;
			this.count = count;
			this.list = list;
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
		try {
			init();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void init() throws JsonSyntaxException, IOException{
	
			
			
		View view = LayoutInflater.from(context).inflate(R.layout.cat_tow_layout,
				null);
	
		
		final List<Model> list = new Gson().fromJson(
				IOUtils.toString(context.getAssets().open(
						"datas/cat_tow.json")),
				new TypeToken<List<Model>>() {
				}.getType());
		
		
		leftListView = (ListView) view.findViewById(R.id.left);
		
		rightListView = (ListView) view.findViewById(R.id.right);
		
		
		
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
				
				twoModel.addAll(model.toModels());
				
				oneAdapter.notifyDataSetChanged();
				
				twoAdapter.notifyDataSetChanged();
				
			}
		});
		
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
