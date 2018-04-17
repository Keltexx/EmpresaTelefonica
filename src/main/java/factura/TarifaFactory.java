package factura;

import factura.Tarifa;

public class TarifaFactory {
	public static Tarifa crearTarifa(int tipo,Tarifa tarifa, double importe) {
		
		switch(tipo) {
		case 0:
			tarifa = new TarifaBasica(importe);
			break;
		case 1:
			tarifa = new ConTarifaDomingo(tarifa,importe);
			break;
		case 2:
			tarifa = new ConTarifaTardes(tarifa,importe);
			break;			
		}
		
		return tarifa;
	}
}