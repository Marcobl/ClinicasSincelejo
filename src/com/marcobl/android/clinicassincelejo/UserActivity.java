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
		
		convertView = this.inflater.inflate(R.layout.clinicas_layout, null);
		
		TextView nombre = (TextView)findViewById(R.id.textView2_nmbreClinica);
		TextView idntf = (TextView)findViewById(R.id.textView4_idnClinica);
		TextView nivel = (TextView)findViewById(R.id.textView5_nivelClinica);
		TextView dir = (TextView)findViewById(R.id.textView7_drccClinica);
		
		try {
			nombre.setText(list.get(position).getNombre());
			idntf.setText(list.get(position).getIdntfccion());
			nivel.setText(list.get(position).getNivel());
			dir.setText(list.get(position).getDireccion());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertView;
	}
	
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
