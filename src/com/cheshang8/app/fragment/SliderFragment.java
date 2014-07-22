package com.cheshang8.app.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.R;
import com.cheshang8.app.utils.BitmapLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imbryk.viewPager.LoopPagerAdapterWrapper;
import com.imbryk.viewPager.LoopViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SliderFragment extends Fragment {

	public static SliderFragment newInstance(List<Model> models) {
		SliderFragment fragment = new SliderFragment();
		fragment.models = models;
		return fragment;
	}

	private LoopViewPager viewPager;

	private CirclePageIndicator indicator;
	
	private List<Model> models;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.slider_layout, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		viewPager = (LoopViewPager) view.findViewById(R.id.viewPager);
		indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
		
			
		MyAdapter adapter = new MyAdapter(getActivity(), models);
		LoopPagerAdapterWrapper wrapper = new LoopPagerAdapterWrapper(
				adapter);

		viewPager.setAdapter(wrapper);
		viewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				PointF downP = new PointF();
				PointF curP = new PointF();
				int act = event.getAction();
				if (act == MotionEvent.ACTION_DOWN
						|| act == MotionEvent.ACTION_MOVE
						|| act == MotionEvent.ACTION_UP) {
					((ViewGroup) v)
							.requestDisallowInterceptTouchEvent(true);
					if (downP.x == curP.x && downP.y == curP.y) {
						return false;
					}
				}
				return false;
			}

		});
		indicator.setViewPager(viewPager);

		

	}
	
	public static class Model{
		private String id;
		private String img;
		private String target_page;
		public Model(String id, String img, String target_page) {
			super();
			this.id = id;
			this.img = img;
			this.target_page = target_page;
		}
		
	}

	private static class MyAdapter extends PagerAdapter {

		private Context context;

		private List<Model> list;

		public MyAdapter(Context context, List<Model> list) {
			super();
			this.context = context;
			this.list = list;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Model model = list.get(position);
			View view = LayoutInflater.from(context).inflate(
					R.layout.slider_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.img);
			BitmapLoader.displayImage(context, model.img, imageView);
			container.addView(view);
			return view;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			// TODO Auto-generated method stub
			return view == (View) obj;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

}
