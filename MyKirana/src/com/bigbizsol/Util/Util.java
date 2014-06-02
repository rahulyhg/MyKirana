package com.bigbizsol.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {
	private static ProgressDialog progressDialog;
	
	
	public static boolean isOnline(Context context) {
		ConnectivityManager cm =
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	
	public static void showProgressDialog(Activity activity){

		progressDialog = new ProgressDialog(activity);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Loading...");
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		if(progressDialog!=null)
		{
			if(!progressDialog.isShowing()){
				progressDialog.show();
			}
		} 
	}

	public static void dismissProgressDialog(){
		if(progressDialog!=null){
			if(progressDialog.isShowing()){
				progressDialog.dismiss();
			}
			progressDialog=null;
		}
	}

}
