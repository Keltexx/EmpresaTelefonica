package cliente;

import java.util.Calendar;
import factura.Tarifa;

public class Particular extends Cliente{
	private String apellidos;
	
	public Particular() {
		super();
	}
	
	public Particular(String nif,Direccion dir, String correo, Calendar fecha, Tarifa tarifa,String apellidos) {
		super(nif,dir,correo,fecha,tarifa);
		this.apellidos=apellidos;
	}
}
