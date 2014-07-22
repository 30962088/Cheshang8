package com.cheshang8.app.network;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.*;

public abstract class BaseClient implements HttpResponseHandler{
	private static final String BASE_URL = "http://huiyangche.duapp.com";

	private static AsyncHttpClient client = new AsyncHttpClient();

	
	
	private  class ResponseHandler extends AsyncHttpResponseHandler{
		
		private HttpResponseHandler handler;
		
		
		private ResponseHandler(HttpResponseHandler handler) {
			
			this.handler = handler;
			
		}

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
			handler.onServerError(arg0, arg1, arg2, arg3);
			requestHandler.onServerError(arg0, arg1, arg2, arg3);
		}

		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
			String json = new String(arg2);
			try {
				JSONObject object = new JSONObject(json);
				int error = object.getInt("err");
				if(error ==0){
					String data = object.getString("data");
					requestHandler.onSuccess(handler.onSuccess(data));
				}else{
					handler.onError(error);
					requestHandler.onError(error);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public enum Method{
		
		GET,POST
	}
	
	
	
	
	protected abstract RequestParams getParams();
	
	protected abstract String getUrl();
	
	protected abstract Method getMethod();
	
	public static interface RequestHandler{
		public void onSuccess(Object object);
		public void onError(int error);
		public void onServerError(int arg0, Header[] arg1, byte[] arg2,Throwable arg3);
		
	}
	
	public static class SimpleRequestHandler implements RequestHandler{

		@Override
		public void onSuccess(Object object) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onError(int error) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onServerError(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private RequestHandler requestHandler;
	
	public void request(RequestHandler requestHandler){
		this.requestHandler = requestHandler;
		Method method = getMethod();
		if(method == Method.GET){
			get(getUrl(), getParams(), this);
		}else if(method == Method.POST){
			post(getUrl(), getParams(), this);
		}
	}
	
	private  void get(String url, RequestParams params,
			HttpResponseHandler handler) {
		client.get(getAbsoluteUrl(url), params, new ResponseHandler(handler));
	}

	private  void post(String url, RequestParams params,
			HttpResponseHandler handler) {
		client.post(getAbsoluteUrl(url), params, new ResponseHandler(handler));
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

	
}
