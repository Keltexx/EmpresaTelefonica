package factura;

import java.io.Serializable;

public abstract class Tarifa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3948344019451414987L;
	private double importe;
	
	public Tarifa() {
		super();
	}
	
	public Tarifa(double importe){
		this.importe = importe;
	}
	
	public Tarifa(Tarifa tarifa){
		this.importe = tarifa.importe;
	}
	
	public double getImporte() {
		return this.importe;
	}
	
	public abstract double calcularImporte(Llamada llamada);
	
	@Override
	public String toString() {
		return "Tarifa [ \n" +
				"\t importe = "+ importe + "]";
	}	
	
}
