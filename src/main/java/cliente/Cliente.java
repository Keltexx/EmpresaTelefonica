package cliente;

import java.sql.Date;

import direccion.Direccion;
import tarifa.Tarifa;

public class Cliente {
	private String NIF;
	private Direccion direccion;
	private String correo;
	private Date fechaDeAlta;
	private Tarifa tarifa;
	
	public Cliente(String nif,Direccion dir, String correo, Date fecha, Tarifa tarifa) {
		this.NIF=nif;
		this.direccion=dir;
		this.correo=correo;
		this.fechaDeAlta=fecha;
		this.tarifa=tarifa;
	}
	
	
	
	
	
	
}
