package factura;

import java.util.Calendar;

public class ConTarifaTardes extends TarifaExtra{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1636978535843579339L;
	
	public ConTarifaTardes(Tarifa tarifa, double importeExtra) {
		super(tarifa, importeExtra);
	}

	@Override
	public double calcularImporte(Llamada llamada) {
        double importeBase = llamada.getDuracion()* getImporte();
        
        if(llamada.getFecha().get(Calendar.HOUR)>=16 && llamada.getFecha().get(Calendar.HOUR)<20 && importeBase < super.calcularImporte(llamada))
            return importeBase;
        
        return super.calcularImporte(llamada);
	}
	
	@Override
	public String toString() {
		return "Tardes con tarifa "+ super.toString();
	}

}