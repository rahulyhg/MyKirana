package com.bigbizsol.mykirana;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.simonvt.numberpicker.NumberPicker;
import net.simonvt.numberpicker.NumberPicker.OnValueChangeListener;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigbizsol.Util.Util;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ItemsDetailsActivity extends Activity {

	private TextView subitemName_Tv;
	private String itemName;
	private String discount;
	private int price;
	private EditText discount_Edt, price_Edt, total_Edt;
	private int count;
	private int discountInt = 0;
	private String parseDate;
	private Date sysdate1;
	private Date parrdate2;
	private LinearLayout addTOFavorites;
	private String shopId;
	private ImageView star;
	private boolean flag;
	private boolean goldStar;
	private Button cancel_Btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemdetailscreen);

		NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
		np.setMaxValue(200);
		np.setMinValue(0);
		np.setFocusable(true);
		np.setFocusableInTouchMode(true);

		itemName = getIntent().getStringExtra("itemValue");
		goldStar = getIntent().getBooleanExtra("goldstar", false);

		subitemName_Tv = (TextView)findViewById(R.id.subitem_name);
		discount_Edt = (EditText)findViewById(R.id.discount_item_details);
		price_Edt = (EditText)findViewById(R.id.price_item_details);
		total_Edt = (EditText)findViewById(R.id.total_items_details);
		addTOFavorites = (LinearLayout)findViewById(R.id.addtofav_item_details);
		star = (ImageView)findViewById(R.id.star);
		cancel_Btn = (Button)findViewById(R.id.cancel_item_details);
		cancel_Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(ItemsDetailsActivity.this,NewOrder.class);
				startActivity(intent);
				
			}
		});
		
		if(goldStar==true)
		{
			star.setImageResource(R.drawable.staricon);
		}
		else
		{
			star.setImageResource(R.drawable.gray_start);
		}

		subitemName_Tv.setText(getIntent().getStringExtra("itemValue"));
		

		List<ParseObject> ob = null;

		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
				"DalsNPulses");
		query.whereEqualTo("items", itemName);

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

			price = (Integer) country.get("price");


		}

		ParseQuery<ParseObject> queryOffers = new ParseQuery<ParseObject>(
				"DiscountClass");
		queryOffers.whereEqualTo("item", itemName);

		// Locate the column named "ranknum" in Parse.com and order list
		// by ascending
		try {
			ob = queryOffers.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ParseObject country : ob) {
			// Locate images in flag column

			discount = country.getString("discountdescription");
			parseDate = country.getString("item_tv_offerdate");
			discount_Edt.setText(discount);


			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String currentDate = sdf.format(new Date());

			try {
				sysdate1 = sdf.parse(currentDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				parrdate2 = sdf.parse(parseDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(parrdate2.after(sysdate1)  || parrdate2.equals(sysdate1)){

				discount_Edt.setText(discount);

			}
			else if(parrdate2.before(sysdate1)){
				discount_Edt.setText("0%");
				discount="0%";
			}




			discount = discount.substring(0, discount.length()-1);

			discountInt = Integer.parseInt(discount);


		}

		price_Edt.setText(""+price);
		count = np.getValue();




		np.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// do something here



				int finalprice = (price*discountInt)/100;

				finalprice = price-finalprice;

				total_Edt.setText(""+newVal*finalprice);
			}
		});

		addTOFavorites.setOnClickListener(new OnClickListener() {

			

			@Override
			public void onClick(View v) {

				
								new addtoFavTask().execute();
			}
		});

	}

	protected class addtoFavTask extends AsyncTask<Void,Void,String>{
		
		String toastText;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Util.showProgressDialog(ItemsDetailsActivity.this);
		}
		@Override
		protected String doInBackground(Void... params) {
			List<ParseObject> ob1 = null;

			SharedPreferences pref=getSharedPreferences("UserName", 0);
			final String restoredName = pref.getString("UserName", "");


			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
					"UserDetails");
			query.whereEqualTo("userName", restoredName);

			// Locate the column named "ranknum" in Parse.com and order list
			// by ascending
			try {
				ob1 = query.find();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (ParseObject country : ob1) {
				// Locate images in flag column
				shopId = (String)country.get("shopID");
			}


			List<ParseObject> ob2 = null;

			ParseQuery<ParseObject> queryfav = new ParseQuery<ParseObject>(
					"Favorites");
			queryfav.whereEqualTo("item", itemName);

			// Locate the column named "ranknum" in Parse.com and order list
			// by ascending
			try {
				ob2 = queryfav.find();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (ParseObject country : ob2) {
				// Locate images in flag column

				flag = true;


			}

			if(flag)
			{
				ParseQuery<ParseObject> query3 = new ParseQuery<ParseObject>(
						"Favorites");
				query3.whereEqualTo("item", itemName);
				
				List<ParseObject> ob26 = null;
				try {
					ob26 = query3.find();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (ParseObject country : ob26) {
					// Locate images in flag column

					country.deleteEventually();

					country.saveInBackground();
					toastText = "Removed from favorites!!";
					

				}
			}
			else
			{


				ParseObject favorites = new ParseObject("Favorites");
				favorites.put("shopId", shopId);
				favorites.put("item", itemName);
				favorites.put("price", price);

				favorites.saveInBackground();
				
				toastText = "Added to Favorites!!";

			}

			return toastText;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Util.dismissProgressDialog();
			if(result.equals("Added to Favorites!!"))
			{
				star.setImageResource(R.drawable.staricon);
				Toast.makeText(getApplicationContext(),result , Toast.LENGTH_SHORT).show();
			}else
			{
			star.setImageResource(R.drawable.gray_start);
			Toast.makeText(getApplicationContext(),result , Toast.LENGTH_SHORT).show();
			}
		}


	}


}
