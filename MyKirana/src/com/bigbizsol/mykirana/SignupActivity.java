package com.bigbizsol.mykirana;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity{
	
	private EditText firstName_Edt;
	private EditText lastName_Edt;
	private EditText userName_Edt;
	private EditText address_Edt;
	private EditText shopID_Edt;
	private EditText password_Edt;
	private EditText confirmPassword_Edt;
	private Button signUp_Btn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		firstName_Edt = (EditText)findViewById(R.id.firstname_signup);
		lastName_Edt = (EditText)findViewById(R.id.lastname_signup);
		userName_Edt = (EditText)findViewById(R.id.username_signup);
		address_Edt = (EditText)findViewById(R.id.address_signup);
		shopID_Edt = (EditText)findViewById(R.id.shopid_signup);
		password_Edt = (EditText)findViewById(R.id.password_signup);
		confirmPassword_Edt = (EditText)findViewById(R.id.confirm_password_signup);
		signUp_Btn = (Button)findViewById(R.id.signup);
	
		signUp_Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String firstname,lastname,address,username,password,shopid,confirmpassword;
				
				firstname = firstName_Edt.getText().toString();
				lastname = lastName_Edt.getText().toString();
				address = address_Edt.getText().toString();
				username = userName_Edt.getText().toString();
				password = password_Edt.getText().toString();
				shopid = shopID_Edt.getText().toString();
				confirmpassword = confirmPassword_Edt.getText().toString();
				
				
				ParseObject userDetails = new ParseObject("UserDetails");
				userDetails.put("firstname", firstname);
				userDetails.put("lastName", lastname);
				userDetails.put("address", address);
				userDetails.put("userName", username);
				userDetails.put("shopID", shopid);
				userDetails.put("password", password);
				userDetails.saveInBackground();
				
			/*	
				ParseUser user = new ParseUser();
				user.setUsername(username);
				user.setPassword(password);
				user.signUpInBackground(new SignUpCallback() {
					public void done(ParseException e) {
						if (e == null) {
							// Show a simple Toast message upon successful registration
							Toast.makeText(getApplicationContext(),
									"Successfully Signed up, please log in.",
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(getApplicationContext(),
									"Sign up Error", Toast.LENGTH_LONG)
									.show();
						}
					}
				});*/
				
			}
		});
		
		
				
		
	}
	

}
