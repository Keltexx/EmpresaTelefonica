package factura;

import java.io.Serializable;

public class Tarifa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3948344019451414987L;
	private double importe;
	
	public Tarifa(double importe){
		this.importe = importe;
	}
	
	public Tarifa(Tarifa tarifa){
		this.importe = tarifa.importe;
	}
	
	public double getImporte() {
		return this.importe;
	}
	
	public String toString() {
		return "Tarifa [ \n" +
				"\t importe = "+ importe + "]";
	}	
	
}
