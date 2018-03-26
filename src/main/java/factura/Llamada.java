package factura;

import java.util.Calendar;

import interfaces.Fecha;

public class Llamada implements Fecha{
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
}
