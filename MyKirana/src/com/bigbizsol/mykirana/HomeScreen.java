package com.bigbizsol.mykirana;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class HomeScreen extends Activity{

	private Button frequentOrders_Btn;
	private ImageView profilepic_Img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreenlayout);
		SharedPreferences pref=getSharedPreferences("LoginDetails", 0);

		Editor editor=pref.edit();
		editor.putBoolean("isLogin",true);
		editor.commit();
		frequentOrders_Btn = (Button)findViewById(R.id.frequent_orders_home);

		profilepic_Img = (ImageView)findViewById(R.id.profilepic_home);
		profilepic_Img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(HomeScreen.this,ProfileActivity.class);
				startActivity(intent);

			}
		});
		frequentOrders_Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeScreen.this,FrequentOrdersActivity.class);
				startActivity(intent);
			}
		});



	}

}
