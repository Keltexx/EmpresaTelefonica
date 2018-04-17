package factura;

import java.util.Calendar;

public class ConTarifaDomingo extends TarifaExtra{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2541021553410858697L;

	public ConTarifaDomingo(Tarifa tarifa, double importeExtra) {
		super(tarifa, importeExtra);
	}
	
	@Override
	public double calcularImporte(Llamada llamada) {
		double importeBase = super.calcularImporte(llamada);
		double nuevo = 0;
		if(llamada.getFecha().get(Calendar.DAY_OF_WEEK)==7 )
			return nuevo;
		return importeBase;
	}
	
	@Override
	public String toString() {
		return "Fin de semana gratis con tarifa "+ super.toString();
	}
	
}
