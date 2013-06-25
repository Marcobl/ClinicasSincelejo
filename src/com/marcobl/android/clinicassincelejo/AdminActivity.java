package com.marcobl.android.clinicassincelejo;


import android.app.Activity;
import android.os.Bundle;
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
	
	private ClinicaDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administrador);
		
		dataSource = new ClinicaDataSource(this);
		dataSource.open();
		
		nombre = (EditText)findViewById(R.id.editText_nombre);
		idntf = (EditText)findViewById(R.id.editText_idnt);
		nivel = (Spinner)findViewById(R.id.spinner_niveles);
		dircc = (EditText)findViewById(R.id.editText3_dircc);
		
		guardar = (Button)findViewById(R.id.button_guardar);
		actualizar = (Button)findViewById(R.id.button_actualizar);
		eliminar = (Button)findViewById(R.id.button_eliminar);
	
		guardar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (nombre.getText().toString().isEmpty()||idntf.getText().toString().isEmpty()|| 
					dircc.getText().toString().isEmpty()){
					Toast.makeText(AdminActivity.this, "Debe ingresar todos los campos", Toast.LENGTH_SHORT).show();
					
				} else {
					try {
						
						dataSource.crearClinica(nombre.getText().toString(), idntf.getText().toString(), 
								nivel.getSelectedItem().toString(), dircc.getText().toString());
				
						nombre.setText("");
						idntf.setText("");
						dircc.setText("");
						Toast.makeText(AdminActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
						nombre.requestFocus();
						
					} catch (Exception e) {
						Toast.makeText(AdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
					}					

				}				
				
			}
		});
		
		eliminar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (idntf.getText().toString().isEmpty()) {
					Toast.makeText(AdminActivity.this, "Debe ingresar la identificacion para eliminar la clinica", Toast.LENGTH_SHORT).show();
				} else {
					try {
						
						dataSource.eliminarClinica(idntf.getText().toString());
						Toast.makeText(AdminActivity.this, "Clinica eliminada", Toast.LENGTH_SHORT).show();
						nombre.setText("");
						idntf.setText("");
						dircc.setText("");
						
					} catch (Exception e) {
						Toast.makeText(AdminActivity.this, "Clinica no encontrada por Identificacion", Toast.LENGTH_SHORT).show();
					}			

				}				
			}
		});
		
		actualizar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (idntf.getText().toString().isEmpty()) {
					Toast.makeText(AdminActivity.this, "Debe ingresar la identificacion para consultar la clinica", Toast.LENGTH_SHORT).show();
				} else {
					try {						
						Clinica clin = dataSource.consultarClinica(idntf.getText().toString());
						nombre.setText(clin.getNombre());
						
						dircc.setText(clin.getDireccion());
						
					} catch (Exception e) {
						Toast.makeText(AdminActivity.this, "Clinica no encontrada por Identificacion", Toast.LENGTH_SHORT).show();
					}	
				}				
			}
		});
	
		
	}
	

}
