package factura;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	private Tarifa tarifa;
	private int codigo;
	private Date fechaEmision;
	private ArrayList<Date> periodoFacturacion;
	private double importe;
	
	public Factura(int codigo, Tarifa tarifa, Date fechaEmision, ArrayList<Date> periodoFacturacion, double importe){
		this.tarifa = tarifa;
		this.codigo = codigo;
		this.fechaEmision = fechaEmision;
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
	
	
	public String getFecha(){
		return fechaEmision.toString();
	}
}
