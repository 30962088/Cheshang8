package com.cheshang8.app;

import com.cheshang8.app.fragment.TabIndexFragment;
import com.cheshang8.app.utils.Preferences;
import com.cheshang8.app.utils.Preferences.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements OnClickListener{
	public static void open(Context context){
		context.startActivity(new Intent(context, LoginActivity.class));
	}
	
	private ViewHolder holder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		holder = new ViewHolder();
		findViewById(R.id.reg_btn).setOnClickListener(this);
		findViewById(R.id.login_btn).setOnClickListener(this);
	}
	
	
	private class ViewHolder{
		private EditText username;
		private EditText password;
		public ViewHolder() {
			username = (EditText) findViewById(R.id.username);
			password = (EditText) findViewById(R.id.password);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.reg_btn:
			RegisterActivity.open(this);
			break;
		case R.id.login_btn:
			User user = new User(holder.username.getText().toString(),"18522112212", "菜鸟", "500");
			new Preferences.Global(this).setUser(user);
			finish();
			break;
		default:
			break;
		}
		
	}
	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}


}
