package cliente;

import java.util.Date;

import factura.Tarifa;
import interfaces.Fecha;

public class Cliente implements Fecha {
	private String NIF;
	private Direccion direccion;
	private String correo;
	private Date fechaDeAlta;
	private Tarifa tarifa;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String nif,Direccion dir, String correo, Date fecha, Tarifa tarifa) {
		this.NIF=nif;
		this.direccion=dir;
		this.correo=correo;
		this.fechaDeAlta=fecha;
		this.tarifa=tarifa;
	}
	

	public String getFecha() {
		return fechaDeAlta.toString();
	}
	
	public String getNIF() {
		return this.NIF;
	}
	
	public Tarifa getTarifa() {
		return this.tarifa;
	}
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	
	
	
}
