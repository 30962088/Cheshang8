package com.cheshang8.app;



import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cheshang8.app.adapter.CommentImgAdapter;
import com.cheshang8.app.adapter.CommentImgAdapter.Model;
import com.cheshang8.app.database.CommentField;
import com.cheshang8.app.database.OrderField;
import com.cheshang8.app.database.OrderField.Callback2;
import com.cheshang8.app.network.CommentsRequest.Result.Comment;
import com.cheshang8.app.network.CommentsRequest.Result.Comment.Service;
import com.cheshang8.app.network.CommentsRequest.Result.Comment.User;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.cheshang8.app.widget.CarStarView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;

public class PublishCommentActivity extends BaseActivity implements OnClickListener{
	
	public static class Params implements Serializable{
		private Service service;
		private String shop_id;
		private String order_id;
		
		public Params(String shop_id,String order_id) {
			super();
			this.shop_id = shop_id;
			this.order_id = order_id;
			service = new Service("1","洗车");
		}
		
		
		
	}
	
	public static void open(Context context,Params params){
		Intent intent = new Intent(context, PublishCommentActivity.class);
		intent.putExtra("params", params);
		context.startActivity(intent);
	}
	private ViewHolder holder;
	
	private Context context;
	
	private Params params;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		params = (Params) getIntent().getSerializableExtra("params");
		context = this;
		setContentView(R.layout.publish_comment);
		findViewById(R.id.publish_btn).setOnClickListener(this);
		holder = new ViewHolder();
		
		folder = new File(Environment.getExternalStorageDirectory(), "cheshang8");
		if(!folder.exists()){
			folder.mkdirs();
		}
	}
	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}
	
	private static final int ACTION_REQUEST_GALLERY = 1;
	
	private static final int ACTION_REQUEST_CAMERA = 2;
	
	private File folder;
	
	private class ViewHolder implements OnItemClickListener{
		private CarStarView star;
		private GridView gridView;
		private CommentImgAdapter adapter;
		private EditText text;
		private List<Model> list = new ArrayList<Model>();
		public ViewHolder() {
			text = (EditText) findViewById(R.id.text);
			star = (CarStarView) findViewById(R.id.star);
			star.setIndicator(false);
			gridView = (GridView) findViewById(R.id.gridview);
			list.add(new Model("drawable://"+R.drawable.icon_add));
			adapter = new CommentImgAdapter(context, list);
			gridView.setAdapter(adapter);
			gridView.setOnItemClickListener(this);
		}
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			if(position == list.size() -1){
				picker();
			}
			
		}
		
		
		
		private void picker(){
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("添加照片");
			builder.setItems(new CharSequence[] {"相册", "拍照"}, 
			        new DialogInterface.OnClickListener() {

			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    	initialURI = Uri.fromFile(new File(folder, System.currentTimeMillis() + ".jpg"));
			        switch (which) {
			        case 0:

			            // GET IMAGE FROM THE GALLERY
			            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType("image/*");  
			            
			            Intent chooser = Intent.createChooser(intent, "选择照片");
			            startActivityForResult(chooser, ACTION_REQUEST_GALLERY);
			            
			            break;

			        case 1:
			            Intent getCameraImage = new Intent("android.media.action.IMAGE_CAPTURE");

			           
			            getCameraImage.putExtra(MediaStore.EXTRA_OUTPUT,initialURI);

			            startActivityForResult(getCameraImage, ACTION_REQUEST_CAMERA);

			            break;

			        default:
			            break;
			        }
			    }
			});

			builder.show();
		}
		
	}
	
	
	
	private Uri initialURI;
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK)    {
	        switch (requestCode) {
	        case ACTION_REQUEST_GALLERY:
	        	holder.list.add(holder.list.size()-1, new Model(data.getDataString()));
	        	holder.adapter.notifyDataSetChanged();
	        	break;
	        case ACTION_REQUEST_CAMERA:
	        	holder.list.add(holder.list.size()-1, new Model(initialURI.toString()));
	        	holder.adapter.notifyDataSetChanged();
	            break;          
	        }

	    }
	}
	
	private void publish(){
		final float rating = holder.star.getStar();
		final String content = holder.text.getText().toString();
		final List<String> imgs = new ArrayList<String>();
		
		for(int i = 0;i< holder.list.size()-1;i++){
			imgs.add(holder.list.get(i).getImg());
		}
		final User user = new User("15832112321");
		final long date = new Date().getTime();
		OrderField.getOrder(params.order_id, new Callback2() {
			
			@Override
			public void callback2(Result result) {
				result.setCommented(1);
				OrderField.update(result);
				Comment comment = new Comment(params.service, rating, date, user, content, imgs);
				CommentField.saveOrUpdate(new CommentField(params.shop_id, comment));
				com.mengle.lib.utils.Utils.tip(context, "发布成功");
				finish();
				
			}
		});
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.publish_btn:
			publish();
			break;

		default:
			break;
		}
		
	};


}
