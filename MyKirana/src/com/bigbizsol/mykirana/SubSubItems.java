package com.bigbizsol.mykirana;

import java.util.ArrayList;
import java.util.List;

import com.bigbizsol.Util.Util;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SubSubItems extends Activity{
	
	private TextView header_Tv;
	private ListView listview_subSubItems;
	private String headerText;
	private ArrayList<String> subsubItemsList;
	private List<ParseObject> ob;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_sub_items);
		listview_subSubItems = (ListView)findViewById(R.id.listview_sub_subitems);
		header_Tv = (TextView)findViewById(R.id.header_sub_subitems);
		headerText = getIntent().getStringExtra("itemValue");
		header_Tv.setText(headerText);
		
		subsubItemsList = new ArrayList<String>();
        try {
            // Locate the class table named "Country" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "DalsNPulses");
            // Locate the column named "ranknum" in Parse.com and order list
            // by ascending
            ob = query.find();
            for (ParseObject country : ob) {
                // Locate images in flag column
                
               
				String essentials = (String)country.get("items");
			
			
				
				subsubItemsList.add(essentials);
            }
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
	
		

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          R.layout.listview_items, R.id.text, subsubItemsList);


        // Assign adapter to ListView
        listview_subSubItems.setAdapter(adapter); 
        
        listview_subSubItems.setOnItemClickListener(new OnItemClickListener() {
        	 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
            
             new itemsDetailsTask().execute(position);
       
            }

       }); 
		
		
		
		
	}
	
	protected class itemsDetailsTask extends AsyncTask<Integer,Void,Void>{

		@Override
		protected Void doInBackground(Integer... params) {
			int position = params[0];
		      // ListView Clicked item value
			
			
            String  itemValue    = (String) listview_subSubItems.getItemAtPosition(position);
            boolean goldstar = false;

    		ParseQuery<ParseObject> query3 = new ParseQuery<ParseObject>(
    				"Favorites");
    		query3.whereEqualTo("item", itemValue);
    		
    		
    		List<ParseObject> ob26 = null;
    		try {
    			ob26 = query3.find();
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		for (ParseObject country : ob26) {

    			goldstar = true;

    		}
               
            Intent intent = new Intent(SubSubItems.this,ItemsDetailsActivity.class);
            intent.putExtra("itemValue", itemValue);
            intent.putExtra("goldstar", goldstar);
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
			Util.showProgressDialog(SubSubItems.this);
		}



	}

}
