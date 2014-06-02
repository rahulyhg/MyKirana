package com.bigbizsol.mykirana;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

public class LoginActivity extends Activity{
	
	private EditText username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		username = (EditText)findViewById(R.id.username);
		username.requestFocus();
		username.setFocusable(true);
		username.setCursorVisible(true);
	}

}
