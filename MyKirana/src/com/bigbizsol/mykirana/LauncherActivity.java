package com.bigbizsol.mykirana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.bigbizsol.Util.Util;

public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() { 
				
				if(Util.isOnline(getApplicationContext()))
				{

					Intent intent = new Intent(LauncherActivity.this,LoginActivity.class);
					startActivity(intent);
					finish();

				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
				}

			} 
		}, 2000);
	}

}
