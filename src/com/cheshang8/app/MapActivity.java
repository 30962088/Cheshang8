package com.cheshang8.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapLongClickListener;
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
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviPara;
import com.baidu.mapapi.overlayutil.DrivingRouteOvelray;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.cheshang8.app.network.Shops2Request;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.ShopsRequest.Result;
import com.cheshang8.app.widget.CarStarView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends BaseActivity implements
		OnGetRoutePlanResultListener, OnClickListener, OnMapLongClickListener {

	public static void open(Context context, ArrayList<Model> list) {
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
		list = (ArrayList<MapActivity.Model>) getIntent().getSerializableExtra(
				"list");
		setContentView(R.layout.map_layout);
		disableBack(findViewById(R.id.map));
		findViewById(R.id.route_btn).setOnClickListener(this);
		findViewById(R.id.nav_btn).setOnClickListener(this);
		containerView = findViewById(R.id.container);
		nameView = (TextView) findViewById(R.id.name);
		priceView = (TextView) findViewById(R.id.price);
		starView = (CarStarView) findViewById(R.id.star);
		BaiduMapOptions mapOptions = new BaiduMapOptions();
		mapOptions.zoomControlsEnabled(false);
		mMapView = new MapView(this, mapOptions);

		mSearch = RoutePlanSearch.newInstance();
		mSearch.setOnGetRoutePlanResultListener(this);
		((ViewGroup) findViewById(R.id.map)).addView(mMapView);

		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMyLocationEnabled(true);
		mBaiduMap.setOnMapLongClickListener(this);
		mBaiduMap.setMyLocationConfigeration(new MyLocationConfigeration(
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

		initOverlay();

	}

	private BDLocation currentLocation;

	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
//			location = new BDLocation(116.404, 39.915, 0);
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
			currentLocation = location;
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	public static class Model implements Serializable {

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

	private Marker currentMarker;

	private void onMakerClick(Marker marker) {
		currentMarker = marker;
		if (marker != null) {
			Model model = (Model) marker.getExtraInfo().get("model");

			containerView.setVisibility(View.VISIBLE);

			nameView.setText(model.name);

			priceView.setText("" + model.price);

			starView.setStar(model.star);

			mBaiduMap.animateMapStatus(MapStatusUpdateFactory
					.newLatLng(new LatLng(model.x, model.y)), 300);
		}else{
			containerView.setVisibility(View.GONE);
		}

	}
	
	

	public void initOverlay() {
		
		System.out.println("zzm:dddd");
		mBaiduMap.clear();
		for (int i = 0; i < list.size(); i++) {
			Model model = list.get(i);
			View mapLocView = LayoutInflater.from(this).inflate(
					R.layout.map_loc, null);
			TextView textView = (TextView) mapLocView.findViewById(R.id.text);
			textView.setText("" + (i + 1));
			LatLng latLng = new LatLng(model.x, model.y);
			System.out.println("zzm:"+model.x+","+ model.y);
			OverlayOptions ooA = new MarkerOptions().position(latLng)
					.icon(BitmapDescriptorFactory.fromView(mapLocView))
					.zIndex(9);
			
			final Marker marker = (Marker) (mBaiduMap.addOverlay(ooA));
			
			
			
			Bundle bundle = new Bundle();
			bundle.putSerializable("model", model);
			marker.setExtraInfo(bundle);
			if (i == 0) {
				mapLocView.postDelayed(new Runnable() {

					@Override
					public void run() {
						onMakerClick(marker);

					}
				}, 500);

			}
		}

		mBaiduMap.setOnMarkerClickListener(myMarkerClickListener);

	}

	private MyMarkerClickListener myMarkerClickListener = new MyMarkerClickListener();

	private class MyMarkerClickListener implements OnMarkerClickListener {

		@Override
		public boolean onMarkerClick(Marker marker) {
			onMakerClick(marker);

			return true;
		}

	}

	private RoutePlanSearch mSearch = null;

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
//		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

	private void drive() {
		if (routeOverlay != null) {
			routeOverlay.removeFromMap();
			mBaiduMap.setOnMarkerClickListener(myMarkerClickListener);
			routeOverlay = null;
		} else {
			if (currentMarker != null) {
				PlanNode enNode = PlanNode.withLocation(currentMarker
						.getPosition());
				PlanNode stNode = PlanNode.withLocation(new LatLng(
						currentLocation.getLatitude(), currentLocation
								.getLongitude()));
				mSearch.drivingSearch((new DrivingRoutePlanOption()).from(
						stNode).to(enNode));
			}
		}

	}

	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

	private OverlayManager routeOverlay = null;

	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
		}
		RouteLine route = result.getRouteLines().get(0);
		DrivingRouteOvelray overlay = new DrivingRouteOvelray(mBaiduMap);
		routeOverlay = overlay;
		mBaiduMap.setOnMarkerClickListener(overlay);
		overlay.setData(result.getRouteLines().get(0));
		overlay.addToMap();
		overlay.zoomToSpan();

	}

	@Override
	public void onGetTransitRouteResult(TransitRouteResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.route_btn:
			drive();
			break;
		case R.id.nav_btn:
			startNav();
			break;
		default:
			break;
		}

	}

	private void startNav() {
		if (currentMarker != null) {
			// 构建 导航参数
			NaviPara para = new NaviPara();
			para.startPoint = new LatLng(currentLocation.getLatitude(),
					currentLocation.getLongitude());
			para.startName = "从这里开始";
			para.endPoint = currentMarker.getPosition();
			para.endName = "到这里结束";
			try {

				BaiduMapNavigation.openBaiduMapNavi(para, this);

			} catch (BaiduMapAppNotSupportNaviException e) {
				BaiduMapNavigation.openWebBaiduMapNavi(para, this);
			}
		}

	}

	@Override
	public void onMapLongClick(final LatLng latLng) {
		if (routeOverlay == null) {
			
			Shops2Request request = new Shops2Request(
					latLng.latitude,latLng.longitude);
			request.request(new SimpleRequestHandler() {
				@Override
				public void onSuccess(Object object) {
					List<Result> results = (List<Result>) object;
					list = Result.toMapList(results);

					initOverlay();
					
					View mapLocView = LayoutInflater.from(MapActivity.this).inflate(
							R.layout.map_loc, null);
					
					TextView textView = (TextView) mapLocView.findViewById(R.id.text);
					
					textView.setText("");
					
					textView.setBackgroundResource(R.drawable.map_loc_blue);
					
					OverlayOptions ooA = new MarkerOptions().position(latLng)
							.icon(BitmapDescriptorFactory.fromView(mapLocView))
							.zIndex(9);
					mBaiduMap.addOverlay(ooA);
					
				}
			});
		}

	}

}
