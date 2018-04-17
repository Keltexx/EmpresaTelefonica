package cliente;

import java.util.Calendar;

import factura.Tarifa;

public class ClienteFactory {
	public static Cliente crearCliente(int tipo,String nombre,String apellidos,String nif,Direccion dir,String correo,Calendar fecha,Tarifa tarifa) {
		Cliente cliente = null;
		
		switch(tipo) {
		case 0:
			cliente = new Empresa(nombre, nif, dir, correo, fecha, tarifa);
			break;
		case 1:
			cliente = new Particular(nombre, apellidos, nif, dir, correo, fecha, tarifa);
			break;
		}
		return cliente;
	}
}
