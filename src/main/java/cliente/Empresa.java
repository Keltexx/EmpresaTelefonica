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
	
	
	public String toString() {
		return "Empresa { \n" +
				"\t nombre = " + getNombre() + "\n" +
				"\t nif = " + getNIF() + "\n" +
				"\t" + getDireccion() + "\n" +
				"\t correo = " + getCorreo() + "\n" +
				"\t fecha de alta = " + getFecha().get(Calendar.DAY_OF_MONTH) + "/" + getFecha().get(Calendar.MONTH) + "/" + getFecha().get(Calendar.YEAR) + "\n" +
				"\t" + getTarifa() + "\n" +
				"}";
	}
}
