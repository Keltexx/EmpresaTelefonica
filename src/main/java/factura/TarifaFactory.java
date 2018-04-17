package factura;

import factura.Tarifa;

public class TarifaFactory {
	public static Tarifa crearTarifa(int tipo, double importe) {
		Tarifa tarifa = null;
		
		switch(tipo) {
		case 0:
			tarifa = new TarifaBasica(importe);
			break;
		case 1:
			tarifa = new ConTarifaFinDeSemana(tarifa,importe);
			break;
		case 2:
			tarifa = new ConTarifaMañanas(tarifa,importe);
			break;			
		}
		
		return tarifa;
	}
}