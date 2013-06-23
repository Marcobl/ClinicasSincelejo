package com.marcobl.android.clinicassincelejo.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_CLINICA = "clinica";
	public static final String COLUMN_NOMBRE = "nombre";
	public static final String COLUMN_IDNTF = "idntfccion";
	public static final String COLUMN_NIVEL = "nivel";
	public static final String COLUMN_DRCCION = "direccion";
	
	private static final String DATABASE_NAME = "clientes.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table "+ TABLE_CLINICA + "( " + COLUMN_NOMBRE + " text, " + COLUMN_IDNTF + " text, "
			+ COLUMN_NIVEL + " text, " + COLUMN_DRCCION + " text );";
	
	public MySQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),"Actualizando base de datos desde la version " + oldVersion + " a " + newVersion + ", borrara todos los datos");
		db.execSQL("drop table if exists " + TABLE_CLINICA);
		onCreate(db);

	}

}
