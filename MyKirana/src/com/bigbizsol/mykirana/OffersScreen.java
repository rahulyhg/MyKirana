package com.bigbizsol.mykirana;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigbizsol.Util.Util;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class OffersScreen extends Activity {
	private ArrayList<DiscountPojo> feedvaluesnew;
	private ListView listView1;
	private List<ParseObject> ob;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.offersscreen);
		listView1=(ListView)findViewById(R.id.listView1);
		new RemoteDataTask().execute();



	}
	class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		private byte[] byteArray1s;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Util.showProgressDialog(OffersScreen.this);

		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create the array
			try {
				feedvaluesnew= new ArrayList<DiscountPojo>();
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"DiscountClass");

				ob = query.find();
				for (ParseObject homefeed : ob) {

					DiscountPojo feed = new DiscountPojo();
					feed.setItemname(((String) homefeed.get("item")));
					feed.setItemDiscountDescription((String) homefeed.get("discountdescription"));
					feed.setItemtvofferdate((String) homefeed.get("item_tv_offerdate"));
					feedvaluesnew.add(feed);
				}
			} catch (ParseException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Void result) {
			Util.dismissProgressDialog();
			CustomAdapter adapter=new CustomAdapter(getApplicationContext(), feedvaluesnew);
			listView1.setAdapter(adapter);
		}
	}
	public class CustomAdapter extends ArrayAdapter<DiscountPojo>{
		Context context;

		ArrayList<DiscountPojo> items;


		public CustomAdapter(Context context, ArrayList<DiscountPojo> items) {
			super(context, R.layout.item_list, items);
			this.context=context;
			this.items=items;
			// TODO Auto-generated constructor stub
		}
		private class ViewHolder
		{
			TextView itemname,itemtvofferdate;
			TextView itemdescription;
			ImageView staricon;



		}
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			final ViewHolder holder;

			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			final DiscountPojo rowItem=items.get(position);
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_list, null);
				holder = new ViewHolder();
				holder.staricon=(ImageView)convertView.findViewById(R.id.item_imageview_star);
				holder.itemdescription = (TextView) convertView.findViewById(R.id.item_tv_itemdescription);
				holder.itemname = (TextView) convertView.findViewById(R.id.item_tv_itemname);
				convertView.setTag(holder);

			}else
			{
				holder=(ViewHolder)convertView.getTag();
			}

			holder.itemname.setText(rowItem.getItemname());
			holder.itemdescription.setText("Discount of " +rowItem.getItemDiscountDescription() +" on this project till "+rowItem.getItemtvofferdate());



			return convertView;
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
	}

}
