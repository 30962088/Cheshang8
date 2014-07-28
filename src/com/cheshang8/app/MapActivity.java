package com.cheshang8.app;





import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
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
import com.cheshang8.app.widget.CarStarView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MapActivity extends BaseActivity {
	
	
	public static void open(Context context,ArrayList<Model> list){
		Intent intent = new Intent(context, MapActivity.class);
		intent.putExtra("list", list);
		context.startActivity(intent);
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
	private ArrayList<Model> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		list =(ArrayList<MapActivity.Model>)getIntent().getSerializableExtra("list");
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

	

	

	
	public static class Model implements Serializable{
		
		private double x;
		
		private double y;
		
		private String name;
		
		private float star;
		
		private int price;

		public Model(double x, double y, String name, float star, int price) {
			super();
			this.x = x;
			this.y = y;
			this.name = name;
			this.star = star;
			this.price = price;
		}
		
	}
	
	private void onMakerClick(Marker marker){
		Model model = (Model) marker.getExtraInfo().get("model");
		
		containerView.setVisibility(View.VISIBLE);
		
		nameView.setText(model.name);
		
		priceView.setText(""+model.price);
		
		starView.setStar(model.star);
		
		mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(model.x, model.y)),300);
	}
	
	public void initOverlay() throws Exception {
		
		
		for(int i = 0;i<list.size();i++){
			Model model = list.get(i);
			View mapLocView = LayoutInflater.from(this).inflate(
					R.layout.map_loc, null);
			TextView textView = (TextView)mapLocView.findViewById(R.id.text);
			textView.setText(""+(i+1));
			LatLng latLng = new LatLng(model.x, model.y);
			OverlayOptions ooA = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromView(mapLocView))
					.zIndex(9);
			Marker marker = (Marker) (mBaiduMap.addOverlay(ooA));
			Bundle bundle = new Bundle();
			bundle.putSerializable("model", model);
			marker.setExtraInfo(bundle);
			if(i == 0){
				onMakerClick(marker);
			}
		}
		
		
		
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				
				onMakerClick(marker);
				
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
