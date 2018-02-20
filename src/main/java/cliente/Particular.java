package cliente;

import java.sql.Date;

import direccion.Direccion;
import tarifa.Tarifa;

public class Particular extends Cliente{
	private String apellidos;
	
	public Particular(String nif,Direccion dir, String correo, Date fecha, Tarifa tarifa,String apellidos) {
		super(nif,dir,correo,fecha,tarifa);
		this.apellidos=apellidos;
	}
}
