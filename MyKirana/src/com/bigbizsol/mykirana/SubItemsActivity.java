package com.bigbizsol.mykirana;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigbizsol.Util.Util;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SubItemsActivity extends Activity{

	private ListView listView_subItems;
	private TextView header_Tv;
	private String subHeading;
	private ArrayList<String> subItemsList;
	private List<ParseObject> ob;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subitems);
		listView_subItems = (ListView)findViewById(R.id.listview_subitems);
		header_Tv = (TextView)findViewById(R.id.header_subitems);
		subHeading = getIntent().getStringExtra("subHeading");
		header_Tv.setText(subHeading);

		subItemsList = new ArrayList<String>();
		try {
			// Locate the class table named "Country" in Parse.com
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
					"Essentials");
			// Locate the column named "ranknum" in Parse.com and order list
			// by ascending
			ob = query.find();
			for (ParseObject country : ob) {
				// Locate images in flag column


				String essentials = (String)country.get("items");



				subItemsList.add(essentials);
			}
		} catch (ParseException e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}



		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.listview_items, R.id.text, subItemsList);


		// Assign adapter to ListView
		listView_subItems.setAdapter(adapter); 

		listView_subItems.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				new subsubItemsTask().execute(position);

			}

		}); 




	}

	protected class subsubItemsTask extends AsyncTask<Integer,Void,Void>{

		@Override
		protected Void doInBackground(Integer... params) {
			int position = params[0];
			String  itemValue    = (String) listView_subItems.getItemAtPosition(position);

			Intent intent = new Intent(SubItemsActivity.this,SubSubItems.class);
			intent.putExtra("itemValue", itemValue);
			startActivity(intent);
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Util.dismissProgressDialog();
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Util.showProgressDialog(SubItemsActivity.this);
		}



	}

}
