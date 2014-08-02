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
import android.widget.TextView;

public class RegisterActivity extends BaseActivity implements OnClickListener{
	public static void open(Context context,String to){
		Intent intent = new Intent(context, RegisterActivity.class);
		intent.putExtra("to", to);
		context.startActivity(intent);
	}
	
	private TextView usernameView;
	
	
	private String to;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		to = getIntent().getStringExtra("to");
		setContentView(R.layout.register_layout);
		usernameView = (TextView) findViewById(R.id.username);
		findViewById(R.id.submit).setOnClickListener(this);
	}
	@Override
	protected Integer finishBtn() {
		// TODO Auto-generated method stub
		return R.id.nav_left_btn;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit:
			submit();
			break;

		default:
			break;
		}
		
	}
	private void submit() {
		User user = new User(usernameView.getText().toString(),"18522112212", "菜鸟", "500");
		new Preferences.Global(this).setUser(user);
		Intent intent = new Intent();
		intent.setClassName(this, to);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}


}
