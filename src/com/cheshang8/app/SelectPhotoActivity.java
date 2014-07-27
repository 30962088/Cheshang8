
package com.cheshang8.app;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.cheshang8.app.adapter.PhotoListAdapter;
import com.cheshang8.app.adapter.TabIndexAdapter;
import com.cheshang8.app.adapter.TabIndexAdapter.Model;
import com.cheshang8.app.fragment.DetailCommentFragment;
import com.cheshang8.app.fragment.DetailMainFragment;
import com.cheshang8.app.fragment.DetailServiceFragment;
import com.cheshang8.app.utils.BitmapLoader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;

public class SelectPhotoActivity extends BaseActivity {
	public static void open(Context context){
		context.startActivity(new Intent(context, SelectPhotoActivity.class));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.select_photo_layout);
		GridView gridView = (GridView) findViewById(R.id.gridview);
		
		try {
			List<PhotoListAdapter.Col> list = new Gson().fromJson(
					IOUtils.toString(getAssets().open(
							"datas/photo_list.json")),
					new TypeToken<List<PhotoListAdapter.Col>>() {
					}.getType());
			
			
			PhotoListAdapter.Model model = new PhotoListAdapter.Model(list);
			model.setPosition(0);
			PhotoListAdapter adapter = new PhotoListAdapter(this, model);
			
			gridView.setAdapter(adapter);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}

	

}
