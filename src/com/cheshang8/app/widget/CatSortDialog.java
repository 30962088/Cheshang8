package com.cheshang8.app.widget;



import java.io.IOException;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;

import com.cheshang8.app.adapter.CatSortAdapter;
import com.cheshang8.app.adapter.CatSortAdapter.Model;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class CatSortDialog implements OnClickListener{
	
	private PopupWindow mPopupWindow;
	
	private Context context;
	
	private View parent;
	
	private ListView listView;

	
	public CatSortDialog(Context context,View parent) {
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
	
	public static interface SortCallback{
		public void oncallback(Model model);
	}
	
	private SortCallback callback;
	
	private List<Model> list;
	
	public void setCallback(SortCallback callback) {
		this.callback = callback;
	}
	
	private void init() throws JsonSyntaxException, IOException{
	
			
			
		View view = LayoutInflater.from(context).inflate(R.layout.sort_dialog_layout,
				null);
	
		view.findViewById(R.id.container).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				
			}
		});
		
		list = new Gson().fromJson(
				IOUtils.toString(context.getAssets().open(
						"datas/cat_sort.json")),
				new TypeToken<List<Model>>() {
				}.getType());
		
		
		listView = (ListView) view.findViewById(R.id.listview);
		
		listView.setAdapter(new CatSortAdapter(context, list));
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Model model = list.get(position);
				if(callback != null){
					callback.oncallback(model);
				}
				dismiss();
				
			}
		});
		
		mPopupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, true);
		/*mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());*/
		mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#80b7bbc2")));
		mPopupWindow.setTouchable(true);
		mPopupWindow.setOutsideTouchable(true);
				
	}
	
	private OnStateChange2 onStateChange;
	
	public void setOnStateChange(final OnStateChange2 onStateChange) {
		this.onStateChange = onStateChange;
		mPopupWindow.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				onStateChange.ondismiss2();
				
			}
		});
	}
	
	public static interface OnStateChange2{
		public void ondismiss2();
		public void onshow2();
	}


	public void show(){
		if(parent != null){
			
			mPopupWindow.showAsDropDown(parent);
			if(onStateChange != null){
				onStateChange.onshow2();
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
