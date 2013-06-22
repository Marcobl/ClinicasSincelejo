package com.marcobl.android.clinicassincelejo;

public class Clinica {
	
	private String nombre;
	private String idntfccion;
	private String nivel;
	private String direccion;
	
	
	
	public Clinica(String nombre, String idntfccion, String nivel,
			String direccion) {
		super();
		this.nombre = nombre;
		this.idntfccion = idntfccion;
		this.nivel = nivel;
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdntfccion() {
		return idntfccion;
	}
	public void setIdntfccion(String idntfccion) {
		this.idntfccion = idntfccion;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	

}
