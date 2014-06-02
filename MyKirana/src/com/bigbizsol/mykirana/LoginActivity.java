package com.bigbizsol.mykirana;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LoginActivity extends Activity{

	private EditText username_Edt;
	private ImageView gmailImage;
	private TextView signupLink;
	private Button login_Btn;
	private EditText password_Edt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		username_Edt = (EditText)findViewById(R.id.username);
		gmailImage = (ImageView)findViewById(R.id.gmail_image);
		signupLink = (TextView)findViewById(R.id.signup_link);
		password_Edt = (EditText)findViewById(R.id.password_login);
		login_Btn = (Button)findViewById(R.id.login);
		gmailImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,GmailLoginActivity.class);
				startActivity(intent);
			}
		});

		signupLink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
				startActivity(intent);
			}
		});
		login_Btn.setOnClickListener(new OnClickListener() {
			
			private String parsePassword;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username,password;
				username = username_Edt.getText().toString();
				password = password_Edt.getText().toString();
				
				List<ParseObject> ob = null;
				
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"UserDetails");
				query.whereEqualTo("userName", username);
				
				// Locate the column named "ranknum" in Parse.com and order list
				// by ascending
				try {
					ob = query.find();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (ParseObject country : ob) {
					// Locate images in flag column
					
					parsePassword = (String)country.get("password");
					
					
					
				}
				if(parsePassword.equals(password))
				{
					/////////////////////////
					Toast.makeText(getApplicationContext(), "Successful Login", Toast.LENGTH_SHORT).show();
					Log.i("***********************", "*************************");
				}
				
				
				
			}
		});


	}

}
