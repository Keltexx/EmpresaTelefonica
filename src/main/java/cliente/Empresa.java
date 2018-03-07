package cliente;

import java.util.Calendar;

import factura.Tarifa;

public class Empresa extends Cliente {

	public Empresa() {
		super();
	}
	
	public Empresa(String nombre,String nif,Direccion dir, String correo, Calendar fecha, Tarifa tarifa) {
		super(nombre,nif,dir,correo,fecha,tarifa);
	}
	
	public Empresa(Empresa otro) {
		super(otro);
	}
}
