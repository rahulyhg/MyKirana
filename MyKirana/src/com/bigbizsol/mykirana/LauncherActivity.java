package com.bigbizsol.mykirana;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.bigbizsol.Util.Util;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Parse.initialize(this, "kTgKOMXuGDs5OOSYeOQUWxgEZsmgH9Py2UxCYZwf", "iP1s5kW6ni8NT8eeBETHWpbL9CmxC3llCwTBXQ3n");

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);

		setContentView(R.layout.splash_screen);

		SharedPreferences pref=getSharedPreferences("LoginDetails", 0);
		final boolean restoredText = pref.getBoolean("isLogin", false);

		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() { 

				if(Util.isOnline(getApplicationContext()))
				{
					// Determine whether the current user is an anonymous user
					if (restoredText==true) {
						Intent intent = new Intent(LauncherActivity.this, HomeScreen.class);
						startActivity(intent);
						finish();
					} else {
						// Send user to LoginSignupActivity.class
						Intent intent = new Intent(LauncherActivity.this,LoginActivity.class);
						startActivity(intent);
						finish();
					}


				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
				}

			} 
		}, 2000);
	}

}