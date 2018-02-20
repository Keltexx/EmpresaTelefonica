package factura;

import java.sql.Date;

public class Llamada {
	private int numero;
	private Date fecha;
	private Date hora;
	private int duracion;
	
	
	public Llamada(int numero, Date fecha, Date hora, int duracion){
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
	
	public String getFecha(){
		return fecha.toString();
	}
}
