package com.marcobl.android.clinicassincelejo;

import com.marcobl.android.clinicassincelejo.bd.ClinicasSQLiteHelper;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ClinicaListAdapter extends BaseAdapter {
	
	private LayoutInflater inflater;
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = this.inflater.inflate(R.layout.clinicas_layout, null);
		
		return convertView;
	}

}
