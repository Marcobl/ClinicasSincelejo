package com.marcobl.android.clinicassincelejo;

import java.util.ArrayList;

import com.marcobl.android.clinicassincelejo.bd.ClinicasSQLiteHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;

public class UserActivity extends Activity {
	
	private Button button_listar;
	private ListView listView;
	private Cursor cursor;
	private ArrayList<Clinica> arrayList;
	private ClinicaListAdapter adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuario);
		
		ClinicasSQLiteHelper bdd = new ClinicasSQLiteHelper(this, "BDClientes", null, 1);
		final SQLiteDatabase database = bdd.getWritableDatabase();
		
		button_listar = (Button)findViewById(R.id.button_listarClinica);
		listView = (ListView)findViewById(R.id.listView_clinicas);
		
		cursor = database.rawQuery("select * from clinica", null);
		
		if (cursor.moveToFirst()) {
		    
		     do {
		          arrayList.add(new Clinica(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
		     } while(cursor.moveToNext());
		}
		
		button_listar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
					adapter = new ClinicaListAdapter(getLayoutInflater(), arrayList);
					listView.setAdapter(adapter);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}	

}
