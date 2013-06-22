package com.marcobl.android.clinicassincelejo;

import com.marcobl.android.clinicassincelejo.bd.ClinicasSQLiteHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class AdminActivity extends Activity {
	
	private EditText nombre;
	private EditText idntf;
	private Spinner nivel;
	private EditText dircc;
	
	private Button guardar;
	private Button actualizar;
	private Button eliminar;
	
	private ContentValues values;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administrador);
		
		ClinicasSQLiteHelper bdd = new ClinicasSQLiteHelper(this, "BDClientes", null, 1);
		final SQLiteDatabase database = bdd.getWritableDatabase();
		
		nombre = (EditText)findViewById(R.id.editText_nombre);
		idntf = (EditText)findViewById(R.id.editText_idnt);
		nivel = (Spinner)findViewById(R.id.spinner_niveles);
		dircc = (EditText)findViewById(R.id.editText3_dircc);
		
		guardar = (Button)findViewById(R.id.button_guardar);
		actualizar = (Button)findViewById(R.id.button_actualizar);
		eliminar = (Button)findViewById(R.id.button_eliminar);
		
		guardar.setOnClickListener(new OnClickListener() {			
			
			@SuppressLint("ShowToast")
			public void onClick(View v) {
				try {
					if (nombre.getText().toString().equals("")||idntf.getText().toString().equals("")||
							dircc.getText().toString().equals("")) {
						
						Toast.makeText(AdminActivity.this, "No debe dejar ningun campo vacio", Toast.LENGTH_SHORT).show();
						
					} else {
						
						values.put("nombre", nombre.getText().toString());
						values.put("identificacion", idntf.getText().toString());
						values.put("nivel",nivel.getSelectedItem().toString());
						values.put("direccion", dircc.getText().toString());
						
						database.insert("clinica", null, values);
						
						Toast.makeText(AdminActivity.this, "Clinica ingresada", Toast.LENGTH_SHORT).show();
						nombre.setText("");
						idntf.setText("");
						dircc.setText("");

					}
					
				} catch (Exception e) {
					
				}
				
			}
		});
		
		eliminar.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				try {
					if (nombre.getText().toString().isEmpty()) {
						Toast.makeText(AdminActivity.this, "Debe ingresar parametro de indetificacion para eliminar", Toast.LENGTH_SHORT).show();
					} else {
						database.delete("clinica", "identificacion="+idntf.getText().toString().trim(), null);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
		
	}
	

}
