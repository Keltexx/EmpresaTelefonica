package cliente;

import java.util.Calendar;
import factura.Tarifa;

public class Particular extends Cliente{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4781412848002462045L;
	private String apellidos;
	
	public Particular() {
		super();
	}
	
	public Particular(String nombre,String apellidos, String nif,Direccion dir, String correo, Calendar fecha, Tarifa tarifa) {
		super(nombre,nif,dir,correo,fecha,tarifa);
		this.setApellidos(apellidos);
	}
	
	public Particular(Particular otro) {
		super(otro);
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String toString() {
		return "Particular { \n" +
				"\t nombre = " + getNombre() + "\n" +
				"\t apellidos = " + apellidos + "\n" +
				"\t nif = " + getNIF() + "\n" +
				"\t" + getDireccion() + "\n" +
				"\t correo = " + getCorreo() + "\n" +
				"\t fecha de alta = " + getFecha().get(Calendar.DAY_OF_MONTH) + "/" + getFecha().get(Calendar.MONTH) + "/" + getFecha().get(Calendar.YEAR) + "\n" +
				"\t" + getTarifa() + "\n" +
				"}";
	}
	
}
