package com.bigbizsol.mykirana;

import android.app.Activity;
import android.content.Intent;
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
		
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() { 
				
				if(Util.isOnline(getApplicationContext()))
				{
					

					// Determine whether the current user is an anonymous user
					if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
						// If user is anonymous, send the user to LoginSignupActivity.class
						Intent intent = new Intent(LauncherActivity.this,LoginActivity.class);
						startActivity(intent);
						finish();
					} else {
						// If current user is NOT anonymous user
						// Get current user data from Parse.com
						ParseUser currentUser = ParseUser.getCurrentUser();
						if (currentUser != null) {
							// Send logged in users to Welcome.class
							//homescreen
							Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
							startActivity(intent);
							finish();
						} else {
							// Send user to LoginSignupActivity.class
							Intent intent = new Intent(LauncherActivity.this,LoginActivity.class);
							startActivity(intent);
							finish();
						}
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
