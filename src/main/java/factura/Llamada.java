package factura;

import java.util.Calendar;

import interfaces.Fecha;

public class Llamada implements Fecha{
	private int numero;
	private Calendar fecha;
	private Calendar hora;
	private int duracion;
	
	
	public Llamada(int numero, Calendar fecha, Calendar hora, int duracion){
		this.numero = numero;
		this.fecha = fecha;
		this.hora = hora;
		this.duracion = duracion;
	}
	
	public Llamada(Llamada llamada){
		this.numero = llamada.numero;
		this.fecha = llamada.fecha;
		this.hora = llamada.hora;
		this.duracion = llamada.duracion;
	}
	
	public double getDuracion() {
		return this.duracion;
	}
	
	public Calendar getFecha(){
		return fecha;
	}
}
