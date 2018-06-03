package factura;

import java.util.Calendar;

public class ConTarifaDomingo extends TarifaExtra{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2541021553410858697L;

	Tarifa tarifa;
	
	public ConTarifaDomingo(Tarifa tarifa, double importeExtra) {
		super(tarifa, importeExtra);
		this.tarifa = tarifa;
	}
	
	@Override
	public double calcularImporte(Llamada llamada) {
		double importeBase = llamada.getDuracion() * getImporte();
		
		if(llamada.getFecha().get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
			return importeBase;		
		return super.calcularImporte(llamada);
	}
	
	@Override
	public String toString() {
		return "Domingo con tarifa "+ super.toString();
	}
	
}