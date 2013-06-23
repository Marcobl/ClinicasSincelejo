package com.marcobl.android.clinicassincelejo;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClinicaListAdapter extends BaseAdapter {
	
	private LayoutInflater inflater;
	private ArrayList<Clinica> arrayList;
	
	
	
	public ClinicaListAdapter(LayoutInflater inflater,
			ArrayList<Clinica> arrayList) {
		
		this.inflater = inflater;
		this.arrayList = arrayList;
	}

	@Override
	public int getCount() {
		
		return arrayList.size();
	}

	@Override
	public Clinica getItem(int position) {
		try {
			return arrayList.get(position);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;			
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
		
		TextView nombre = (TextView)convertView.findViewById(R.id.textView2_nmbreClinica);
		TextView idntf = (TextView)convertView.findViewById(R.id.textView4_idnClinica);
		TextView nivel = (TextView)convertView.findViewById(R.id.textView5_nivelClinica);
		TextView dir = (TextView)convertView.findViewById(R.id.textView7_drccClinica);
		
		try {
			nombre.setText(arrayList.get(position).getNombre());
			idntf.setText(arrayList.get(position).getIdntfccion());
			nivel.setText(arrayList.get(position).getNivel());
			dir.setText(arrayList.get(position).getDireccion());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertView;
	}

}
