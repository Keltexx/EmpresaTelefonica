package cliente;

import java.util.Calendar;
import java.util.Scanner;

import factura.Tarifa;
import interfaces.Fecha;

public class Cliente implements Fecha {
	private String nombre;
	private String NIF;
	private Direccion direccion;
	private String correo;
	private Calendar fechaDeAlta;
	private Tarifa tarifa;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String nombre,String nif,Direccion dir, String correo, Calendar fecha, Tarifa tarifa) {
		this.nombre=nombre;
		this.NIF=nif;
		this.direccion=dir;
		this.correo=correo;
		this.fechaDeAlta=fecha;
		this.tarifa=tarifa;
	}
	
	public Cliente(Cliente otro) {
		this.nombre=otro.nombre;
		this.NIF=otro.NIF;
		this.direccion= otro.direccion;
		this.correo=otro.correo;
		this.fechaDeAlta=otro.fechaDeAlta;
		this.tarifa=otro.tarifa;
	}

	public Calendar getFecha() {
		return fechaDeAlta;
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
