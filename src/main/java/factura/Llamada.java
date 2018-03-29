package factura;

import java.io.Serializable;
import java.util.Calendar;

import interfaces.Fecha;

public class Llamada implements Fecha, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3233202990795471243L;
	private int numero;
	private Calendar fecha;
	private int duracion;
	
	
	public Llamada(int numero, Calendar fecha, int duracion){
		this.numero = numero;
		this.fecha = fecha;
		this.duracion = duracion;
	}
	
	public Llamada(Llamada llamada){
		this.numero = llamada.numero;
		this.fecha = llamada.fecha;
		this.duracion = llamada.duracion;
	}
	
	public double getDuracion() {
		return this.duracion;
	}
	
	public Calendar getFecha(){
		return fecha;
	}
	
	@Override
	public String toString() {
		return "Factura [ \n" +
				"\t numero = " + numero + "\n" +
				"\t fecha = " + fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR) +
				"\t hora = " + fecha.get(Calendar.HOUR) + ":" + fecha.get(Calendar.MINUTE) + 
				"\t duracion = " + duracion + "]";
	}
	
}
