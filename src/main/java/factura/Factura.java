package factura;

import java.util.ArrayList;
import java.util.Calendar;

import interfaces.Fecha;

public class Factura implements Fecha{
	private Tarifa tarifa;
	private int codigo;
	private Calendar fechaEmision;
	private ArrayList<Calendar> periodoFacturacion;
	private double importe;
	
	public Factura(int codigo, Tarifa tarifa, Calendar fechaEmision, ArrayList<Calendar> periodoFacturacion, double importe){
		this.tarifa = tarifa;
		this.codigo = codigo;
		this.fechaEmision = Calendar.getInstance();
		this.periodoFacturacion = periodoFacturacion;
		this.importe = importe;
	}
	
	public Factura(Factura factura){
		this.tarifa = factura.tarifa;
		this.codigo = factura.codigo;
		this.fechaEmision = factura.fechaEmision;
		this.periodoFacturacion = factura.periodoFacturacion;
		this.importe = factura.importe;
	}
	
	
	public Calendar getFecha(){
		return fechaEmision;
	}
}
