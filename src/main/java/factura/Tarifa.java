package factura;

public class Tarifa {
	private double importe;
	
	public Tarifa(double importe){
		this.importe = importe;
	}
	
	public Tarifa(Tarifa tarifa){
		this.importe = tarifa.importe;
	}
}
