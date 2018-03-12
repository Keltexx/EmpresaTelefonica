package cliente;

import java.util.Calendar;
import factura.Tarifa;

public class Particular extends Cliente{
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
}
