package com.marcobl.android.clinicassincelejo;

import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class UserActivity extends Activity {	
	
	private ClinicaDataSource dataSource;
	private ListView listView;
	private ClinicaListAdapter adapter;
	private Button listarClinicas;
	private List<Clinica> clinicas;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuario);
		
		dataSource = new ClinicaDataSource(this);
		dataSource.open();
		
		listView = (ListView)findViewById(R.id.listView_clinicas);
		listarClinicas = (Button)findViewById(R.id.button_listarClinica);
		
				
		listarClinicas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					new GetClinicasTask().execute();				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		
		
	}	
	
public class ClinicaListAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private List<Clinica> list;
	
	

	public ClinicaListAdapter(LayoutInflater inflater, List<Clinica> list) {
		
		this.inflater = inflater;
		this.list = list;
	}

	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public Clinica getItem(int position) {
		try {
			return list.get(position);
		} catch (Exception e) {
			
			e.printStackTrace();
			return new Clinica();
		}		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		// Optimiza el ListView para no volver a crearlo cada vez que se hace scroll  
		if (convertView == null) {
			convertView = this.inflater.inflate(R.layout.clinicas_layout, null);
			
			holder = new ViewHolder();
			holder.nombre = (TextView)convertView.findViewById(R.id.textView2_nmbreClinica);
			holder.idntf = (TextView)convertView.findViewById(R.id.textView4_idnClinica);
			holder.nivel = (TextView)convertView.findViewById(R.id.textView5_nivelClinica);
			holder.direccion = (TextView)convertView.findViewById(R.id.textView7_drccClinica);
			
			convertView.setTag(holder);
		}else{
			
			holder = (ViewHolder)convertView.getTag();
		}	
					
		try {
			holder.nombre.setText(list.get(position).getNombre());
			holder.idntf.setText(list.get(position).getIdntfccion());
			holder.nivel.setText(list.get(position).getNivel());
			holder.direccion.setText(list.get(position).getDireccion());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertView;
	}
	
}

/* clase que guarda la referencia de los de los componentes para no volver 
   a crearlos cada vez que se hace scroll en la lista*/
static class ViewHolder{
	
	TextView nombre;
	TextView idntf;
	TextView nivel;
	TextView direccion;
}


public class GetClinicasTask extends AsyncTask<Void, Void, Boolean>{
	
	private ProgressDialog dialog;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialog(UserActivity.this);
		dialog.setMessage("Obteniendo listas de Clinicas");
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.show();
		
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		Boolean error = false;
		
		try {
			clinicas = dataSource.obtenerClinicas();
			if (clinicas!=null) {
				adapter = new ClinicaListAdapter(getLayoutInflater(), clinicas);				
				
			} else {
				error = true;
			}			
			
		} catch (Exception e) {
			error = true;
			e.printStackTrace();
		}
		
		return error;
	}
	@SuppressLint("ShowToast")
	@Override
		protected void onPostExecute(Boolean error) {

			super.onPostExecute(error);
			dialog.dismiss();
			if (error) {
				Toast.makeText(UserActivity.this,"Error obteniendo la lista de clinicas", Toast.LENGTH_SHORT).show();

			} else {
				listView.setAdapter(adapter);
			}

		}
	
}
}
