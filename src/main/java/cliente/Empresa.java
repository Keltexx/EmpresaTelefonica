package cliente;

import java.util.Calendar;

import factura.Tarifa;

public class Empresa extends Cliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917603621901901744L;

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
