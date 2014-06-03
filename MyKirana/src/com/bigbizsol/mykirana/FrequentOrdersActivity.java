package com.bigbizsol.mykirana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class FrequentOrdersActivity extends Activity{
	
	private ImageView profilepic_Img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frequentorderslayout);
		profilepic_Img = (ImageView)findViewById(R.id.profilepic_home);
		profilepic_Img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(FrequentOrdersActivity.this,ProfileActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

}
