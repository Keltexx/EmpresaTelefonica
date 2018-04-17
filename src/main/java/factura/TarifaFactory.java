package factura;

import java.util.Calendar;

import factura.Tarifa;

public class TarifaFactory {
	public static Tarifa crearTarifa(int tipo, double importe) {
		Tarifa tarifa = null;
		
		switch(tipo) {
		case 0:
			tarifa = new TarifaBasica(importe);
			break;
		case 1:
			tarifa = new ConTarifaFinDeSemana();
			break;
		}
		return tarifa;
	}
}