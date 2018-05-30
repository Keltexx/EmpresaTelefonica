package factura;

public abstract class TarifaExtra extends TarifaBasica {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6764271660877292862L;
	public TarifaExtra(Tarifa tarifa, double importeExtra) {
		super(importeExtra);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
