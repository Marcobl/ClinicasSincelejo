package com.marcobl.android.clinicassincelejo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends Activity {

	
	private RadioButton rd_admin;
	private RadioButton rd_user;
	private Button bt_acceder;
	private Intent intent;
	
	
	@Override	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 bt_acceder = (Button)findViewById(R.id.button_acceder);
		 rd_admin = (RadioButton)findViewById(R.id.rbutton_admin);
		 rd_user = (RadioButton)findViewById(R.id.rbutton_user);
		 
		 bt_acceder.setOnClickListener(new OnClickListener(){
			 public void onClick(View arg0){
				 if (rd_admin.isChecked()) {
					intent = new Intent(MainActivity.this, AdminActivity.class);
					startActivity(intent);
				} else {

				}
				 
			 }
			 
		 });
		 
	}

	

}
