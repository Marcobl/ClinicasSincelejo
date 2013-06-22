package com.marcobl.android.clinicassincelejo.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ClinicasSQLiteHelper extends SQLiteOpenHelper {
	
	private String sqlCreateTable="CREATE TABLE clinica(nombre TEXT, identificacion TEXT, nivel TEXT, direccion TEXT)";
	

	public ClinicasSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreateTable);

	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
