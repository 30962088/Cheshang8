package com.cheshang8.app;



import java.util.ArrayList;
import java.util.List;

import com.cheshang8.app.adapter.CommentImgAdapter;
import com.cheshang8.app.adapter.CommentImgAdapter.Model;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;

public class PublishCommentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_comment);
		GridView gridView = (GridView) findViewById(R.id.gridview);
		List<Model> list = new ArrayList<Model>();
		list.add(new Model("drawable://"+R.drawable.icon_add));
		CommentImgAdapter adapter = new CommentImgAdapter(this, list);
		gridView.setAdapter(adapter);
	}


}
