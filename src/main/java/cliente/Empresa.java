package cliente;

import java.util.Calendar;

import factura.Tarifa;

public class Empresa extends Cliente {

	public Empresa() {
		super();
	}
	
	public Empresa(String nif,Direccion dir, String correo, Calendar fecha, Tarifa tarifa) {
		super(nif,dir,correo,fecha,tarifa);
	}
	
	public Empresa(Empresa empresa) {
		super(empresa);
	}
}
