package com.cheshang8.app;

import java.util.ArrayList;
import java.util.List;

import com.cheshang8.app.adapter.SelectCityAdapter;
import com.cheshang8.app.adapter.SelectCityAdapter.Model;
import com.cheshang8.app.adapter.SelectCityAdapter.Model.Col;
import com.cheshang8.app.network.BaseClient;
import com.cheshang8.app.network.CityListRequest.Result;
import com.cheshang8.app.network.CityListRequest;
import com.cheshang8.app.utils.Preferences.Global;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class SelectCityActivity extends BaseActivity implements OnItemClickListener{

	public static void open(Context context) {
		Intent intent = new Intent(context, SelectCityActivity.class);
		context.startActivity(intent);
	}

	private Global global;

	private ListView listView;

	private List<Model.Col> list = new ArrayList<SelectCityAdapter.Model.Col>();

	private Model model = new Model(list);

	private SelectCityAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		global = new Global(this);

		setContentView(R.layout.select_city_layout);

		View headerView = LayoutInflater.from(this).inflate(
				R.layout.select_city_header, null);

		headerView.findViewById(R.id.city_name).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						global.setCity("1", "北京");
						finish();
					}
				});

		listView = (ListView) findViewById(R.id.listview);

		listView.addHeaderView(headerView);

		adapter = new SelectCityAdapter(this, model);

		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(this);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		request();
	}

	private void request() {
		final CityListRequest request = new CityListRequest();

		request.request(new BaseClient.SimpleRequestHandler() {
			@Override
			public void onSuccess(Object object) {
				List<Result> results = (List<Result>) object;
				list.clear();
				String id = global.getCityId();
				Integer index = null;
				List<Model.Col> cols = Result.toList(results);
				for(int i = 0;i<cols.size();i++){
					Col col = cols.get(i);
					if(TextUtils.equals(col.getId(), id)){
						index = i;
						break;
					}
				}
				list.addAll(cols);
				model.setSelected(index);
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		if(position>0){
			Col col = list.get(position-1);
			global.setCity(col.getId(), col.getName());
			finish();
		}
		
		
	}

}
