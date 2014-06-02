package com.bigbizsol.mykirana;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Store;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GmailLoginActivity extends Activity{
	private EditText idEdit;
	private EditText passwordEdit;
	private Button ok;
	
	private String domain;
	private String mErrorMsg = "", mEmailId = "",mSpinner_Selected_Type = "",mEmailType = "", mSmtpServer = "", mOutPort = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gmail_login);

		/*Intent receiveIntent = getIntent();
		mEmailId = receiveIntent.getExtras().getString("username");
		String[] arrEmail = mEmailId.split("@");
		String domain = arrEmail[1];
		String username = arrEmail[0];
		*/
		idEdit = (EditText)findViewById(R.id.id);
		passwordEdit = (EditText)findViewById(R.id.password);
		ok = (Button)findViewById(R.id.ok);
		
		ok.setOnClickListener(new OnClickListener() {
			
		

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		
				
				String[] arrEmail = idEdit.getText().toString().split("@");
				domain = arrEmail[1];
				String usernameSub = arrEmail[0];
				
			
				
				LoginTask t = new LoginTask(GmailLoginActivity.this);
				t.execute();
				
			}
		});
		
	}
	
	private class LoginTask extends AsyncTask<String, Void, Void> 
	{
		ProgressDialog mDialog;
		javax.mail.Authenticator authenticator = null;
		private String imap_Server;
		private int port;
		
		LoginTask(Context context) {
			mDialog = ProgressDialog.show(context, "", "Authenticating...", true, false);
			mDialog.setCancelable(false);
			mErrorMsg = "";
		}

		 public Void doInBackground(final String... args) 
		 {
			Store store = null;
			Session session = null; 
			Properties props = null;
			
	 		try 
	 		{
				props = new Properties();
				String username = idEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				
				if (domain.equalsIgnoreCase("gmail.com")){
					
					imap_Server = "imap.gmail.com";
					port = 993;
					
					mSmtpServer = "smtp.gmail.com";
					mOutPort = "587";
				}else if (domain.equalsIgnoreCase("yahoo.com") || domain.equalsIgnoreCase("yahoo.co.in") || domain.equalsIgnoreCase("ymail.com")) {
				
					imap_Server = "imap.mail.yahoo.com";
					port = 993;
					mSmtpServer = "smtp.mail.yahoo.com";
					mOutPort = "587";
				}else if (domain.equalsIgnoreCase("conceptglobal.com")){
					imap_Server = "pop.gmail.com";
					port = 993;
					mSmtpServer = "smtp.gmail.com";
					mOutPort = "587";
				}else if(domain.equalsIgnoreCase("hotmail.com") || domain.equalsIgnoreCase("live.com") ||domain.equalsIgnoreCase("outlook.com")){
					imap_Server = "imap-mail.outlook.com";
					port = 993;
					mSmtpServer = "smtp-mail.outlook.com";
					mOutPort = "587";
					mEmailType = "POP3";
				}

				
				Log.v("AccountSettings", " Connecting to server");

				Log.v("AccountSettings", " Connecting to server " + imap_Server + " port " + port+"..username.."+username+"..password..."+password);
				
				

				session = Session.getInstance(props, null);
				props.put("mail.imap.ssl.enable", "true");
				store = session.getStore("imaps");
				store.connect(imap_Server, port, username, password);
				
				
				Log.v("AccountSettings","Connection established with IMAP Server Successfully.");
				
				if (store != null) {
					store.close();
				}
				
			  }catch (Exception e) {
				  e.printStackTrace();
				  mErrorMsg = "Issue connecting to the specified IMAP setting : " + e.getMessage();
			 } 
	 		 finally{
	 			 try{
	 				store.close(); 
	 			 }catch(Exception ex){}
	 		 }
			return null;
		 }

		public void onPostExecute(Void result) 
		{
			if(mDialog != null){
				mDialog.dismiss();
			}
			
			if(mErrorMsg.length()>0)
			{
				Toast.makeText(getApplicationContext(), mErrorMsg, Toast.LENGTH_LONG).show();
	
			}else{
				//this is checked in the spalsh scrren if any account exists then the screen displayed is Home Screen
				SharedPreferences sharedPreferences;
			    sharedPreferences = GmailLoginActivity.this.getSharedPreferences("oneplace", MODE_WORLD_READABLE);
				SharedPreferences.Editor preferencesEdit;
				preferencesEdit = sharedPreferences.edit();
				preferencesEdit.putBoolean("AccountExists",true);
				preferencesEdit.commit();
				Toast.makeText(getApplicationContext(), "Your account has been configured successfully.", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	

}
