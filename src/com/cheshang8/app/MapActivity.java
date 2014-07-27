package com.cheshang8.app;





import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfigeration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.MyLocationConfigeration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.cheshang8.app.adapter.TabIndexAdapter.Model;
import com.cheshang8.app.widget.CarStarView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MapActivity extends BaseActivity {
	public static void open(Context context){
		context.startActivity(new Intent(context, MapActivity.class));
	}
	private MapView mMapView;
	private LocationClient mLocClient;
	private BaiduMap mBaiduMap;
	private BitmapDescriptor mCurrentMarker;
	
	private boolean isFirstLoc = true;// 是否首次定位
	private MyLocationListenner myListener = new MyLocationListenner();
	private TextView nameView;
	private TextView priceView;
	private CarStarView starView;
	private View containerView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		containerView = findViewById(R.id.container);
		nameView = (TextView) findViewById(R.id.name);
		priceView = (TextView) findViewById(R.id.price);
		starView = (CarStarView) findViewById(R.id.star);
		
		mMapView = new MapView(this, new BaiduMapOptions());
		
		((ViewGroup)findViewById(R.id.map)).addView(mMapView);
		
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMyLocationEnabled(true);
		mBaiduMap
		.setMyLocationConfigeration(new MyLocationConfigeration(
				LocationMode.NORMAL, true, null));
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
		try {
			initOverlay();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	

	// 初始化全局 bitmap 信息，不用时及时 recycle
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_marka);
	

	
	public static class Model implements Serializable{
		
		private double x;
		
		private double y;
		
		private String name;
		
		private int star;
		
		private int price;

		public Model(double x, double y, String name, int star, int price) {
			super();
			this.x = x;
			this.y = y;
			this.name = name;
			this.star = star;
			this.price = price;
		}
		
	}
	
	public void initOverlay() throws Exception {
		
		List<Model> list = new Gson().fromJson(
				IOUtils.toString(getAssets().open(
						"datas/map.json")),
				new TypeToken<List<Model>>() {
				}.getType());
		
		for(Model model : list){
			LatLng latLng = new LatLng(model.x, model.y);
			OverlayOptions ooA = new MarkerOptions().position(latLng).icon(bdA)
					.zIndex(9);
			Marker marker = (Marker) (mBaiduMap.addOverlay(ooA));
			Bundle bundle = new Bundle();
			bundle.putSerializable("model", model);
			marker.setExtraInfo(bundle);
		}
		
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				
				Model model = (Model) marker.getExtraInfo().get("model");
				
				containerView.setVisibility(View.VISIBLE);
				
				nameView.setText(model.name);
				
				priceView.setText(""+model.price);
				
				starView.setStar(model.star);
				
				return true;
			}
			
		});

		
	}


	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}


	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

	
}
