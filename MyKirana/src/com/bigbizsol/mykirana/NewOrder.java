package com.bigbizsol.mykirana;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bigbizsol.Util.Util;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class NewOrder extends Activity{

	private ArrayList<String> constantslist;
	private List<ParseObject> ob;
	private Spinner essestials_Spinner;
	private Button go_Btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_order);

		essestials_Spinner = (Spinner)findViewById(R.id.spinner1);
		go_Btn = (Button)findViewById(R.id.go_new_order);


		constantslist = new ArrayList<String>();
		try {
			// Locate the class table named "Country" in Parse.com
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
					"MyKiranaStore");
			// Locate the column named "ranknum" in Parse.com and order list
			// by ascending
			ob = query.find();
			for (ParseObject country : ob) {
				// Locate images in flag column


				String essentials = (String)country.get("items");



				constantslist.add(essentials);
			}
		} catch (ParseException e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}



		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,constantslist);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		essestials_Spinner.setAdapter(dataAdapter);

		go_Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new goBtnTask().execute();

			}
		});
	}

	protected class goBtnTask extends AsyncTask<Void,Void,Void>{


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Util.showProgressDialog(NewOrder.this);
		}
		@Override
		protected Void doInBackground(Void... params) {

			String text = essestials_Spinner.getSelectedItem().toString();

			if(text.equals("Imported & Gourmet"))
			{

			}else if(text.equals("HouseHold"))
			{

			}else if(text.equals("Personal Care"))
			{

			}else if(text.equals("Branded Foods"))
			{

			}else if(text.equals("Tea & coffee"))
			{

			}else if(text.equals("Beverages"))
			{

			}else if(text.equals("Bread Dairy & Eggs"))
			{

			}else if(text.equals("Grocery & Staples"))
			{
				Intent intent = new Intent(NewOrder.this,SubItemsActivity.class);
				intent.putExtra("subHeading", text);
				startActivity(intent);

			}else if(text.equals("Fruits & Vegetables"))
			{

			}


			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Util.dismissProgressDialog();
		}


	}

}
