package factura;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import interfaces.Fecha;

public class Factura implements Fecha, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3926114285700250836L;
	private Tarifa tarifa;
	private int codigo;
	private Calendar fechaEmision;
	private double importe;
	
	public Factura(int codigo, Tarifa tarifa, Calendar fechaEmision, double importe){
		this.tarifa = tarifa;
		this.codigo = codigo;
		this.fechaEmision = fechaEmision;
		this.importe = importe;
	}
	
	public Factura(Factura factura){
		this.tarifa = factura.tarifa;
		this.codigo = factura.codigo;
		this.fechaEmision = factura.fechaEmision;
		this.importe = factura.importe;
	}
	
	
	public Calendar getFecha(){
		return fechaEmision;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o ) return true;
		if(!(o instanceof Factura)) return false;
		Factura factura = (Factura) o;
		return codigo == factura.codigo && Double.compare(factura.importe, importe)
				== 0 && Objects.equals(tarifa.getImporte(), factura.tarifa.getImporte());
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public Tarifa getTarifa() {
		return this.tarifa;
	}
	
	public double getImporte() {
		return this.importe;
	}

	@Override
	public String toString() {
		return "Factura [ \n" +
				"\t tarifa = " + tarifa + "\n" +
				"\t codigo = " + codigo + "\n" +
				"\t fecha de emision = " + fechaEmision.get(Calendar.DAY_OF_MONTH) + "/" + fechaEmision.get(Calendar.MONTH) + "/" + fechaEmision.get(Calendar.YEAR) + 
				"\t importe = " + importe + "]";
	}
	
	
	
}
