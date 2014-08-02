package com.cheshang8.app.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.cheshang8.app.App;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;
import com.cheshang8.app.network.BaseClient.SimpleRequestHandler;
import com.cheshang8.app.network.CommentsRequest.Result.Comment;
import com.cheshang8.app.network.OrdersRequest;
import com.cheshang8.app.network.OrdersRequest.Result;
import com.cheshang8.app.utils.Preferences.Global;
import com.google.gson.Gson;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="offline_data_field")  
public class OfflineDataField {
	@DatabaseField(id=true)
	private String hash;
	@DatabaseField
	private String data;
	public OfflineDataField(String hash, String data) {
		super();
		this.hash = hash;
		this.data = data;
	}
	

	public OfflineDataField() {
		// TODO Auto-generated constructor stub
	}
	public String getData() {
		return data;
	}
	public static OfflineDataField getOffline(String hash){
		OfflineDataField dataField = null;
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		try {
			dataField = helper.getOfflineDao().queryForId(hash);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataField;
	}
	
	public static void create(OfflineDataField field){
		final DataBaseHelper helper = new DataBaseHelper(App.getInstance());
		try {
			helper.getOfflineDao().create(field);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	
}
