package com.marcobl.android.clinicassincelejo;

import java.util.ArrayList;
import java.util.List;

import com.marcobl.android.clinicassincelejo.bd.MySQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ClinicaDataSource {
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] columnas = {MySQLiteHelper.COLUMN_NOMBRE,MySQLiteHelper.COLUMN_IDNTF,MySQLiteHelper.COLUMN_NIVEL,
			           				MySQLiteHelper.COLUMN_DRCCION};
	
	public ClinicaDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open()throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	private Clinica cursorToClinica(Cursor cursor) {
		Clinica clinica = new Clinica();
		clinica.setNombre(cursor.getString(0));
		clinica.setIdntfccion(cursor.getString(1));
		clinica.setNivel(cursor.getString(2));
		clinica.setDireccion(cursor.getString(3));
		
		return clinica;
	}
	
	public void crearClinica(String nm, String id, String lvl, String dir){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NOMBRE, nm);
		values.put(MySQLiteHelper.COLUMN_IDNTF, id);
		values.put(MySQLiteHelper.COLUMN_NIVEL, lvl);
		values.put(MySQLiteHelper.COLUMN_DRCCION, dir);
		
		database.insert(MySQLiteHelper.TABLE_CLINICA, null, values);
				
	}
	
	public void eliminarClinica(String idn){
		
		database.delete(MySQLiteHelper.TABLE_CLINICA, MySQLiteHelper.COLUMN_IDNTF + " = " +"'"+idn+"'", null);	
		
	}
	
	public Clinica consultarClinica(String idn){
		
		Cursor cursor = database.rawQuery("select * from " + MySQLiteHelper.TABLE_CLINICA + " where " + MySQLiteHelper.COLUMN_IDNTF + " =" +"'"+idn+"';", null);
		cursor.moveToFirst();
		Clinica clinic = cursorToClinica(cursor);			
		cursor.close();
		return clinic;
	}
	
	public List<Clinica> obtenerClinicas(){
		List<Clinica> clinicas = new ArrayList<Clinica>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CLINICA,columnas,null,null,null,null,null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Clinica clinica = cursorToClinica(cursor);
			clinicas.add(clinica);
			cursor.moveToNext();
		}
		cursor.close();
		return clinicas;
		
	}

	

}
