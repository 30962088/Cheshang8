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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SliderFragment extends Fragment {

	public static SliderFragment newInstance(){
		SliderFragment fragment = new SliderFragment();
		return fragment;
	}
	
	private LoopViewPager viewPager;

	private CirclePageIndicator indicator;

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
		try {
			List<String> list = new Gson().fromJson(
					IOUtils.toString(getActivity().getAssets().open(
							"datas/index_slider.json")),
					new TypeToken<List<String>>() {
					}.getType());
			MyAdapter adapter = new MyAdapter(getActivity(), list);
			LoopPagerAdapterWrapper wrapper = new LoopPagerAdapterWrapper(
					adapter);

			viewPager.setAdapter(wrapper);

			indicator.setViewPager(viewPager);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class MyAdapter extends PagerAdapter {

		private Context context;

		private List<String> list;

		public MyAdapter(Context context, List<String> list) {
			super();
			this.context = context;
			this.list = list;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			String url = list.get(position);
			View view = LayoutInflater.from(context).inflate(
					R.layout.slider_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.img);
			BitmapLoader.displayImage(context, url, imageView);
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
