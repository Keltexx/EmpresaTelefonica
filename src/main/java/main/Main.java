package main;

import java.util.Scanner;
import cliente.Cliente;
import gestion.Gestion;

public class Main {

	public static void main(String[] args) {
		Gestion gestion = new Gestion();
		Cliente cliente=new Cliente(); 
		cliente=cliente.pedirDatosCliente();
		System.out.println(Menu.getMenu());
		Scanner scanner = new Scanner(System.in);
		System.out.print("Elige una opción:");
		byte opcion = scanner.nextByte();
		Menu opcionMenu = Menu.getOpcion(opcion);
		switch (opcionMenu) {
			case ALTA_CLIENTE:
				gestion.darDeAltaCliente(cliente);
				break;
			case BORRAR_CLIENTE:
				gestion.borrarCliente(cliente);
				break;
			case CAMBIAR_TARIFA:
				gestion.cambiarTarifa(cliente.getNIF(), cliente.getTarifa());
				break;
			case RECUPERAR_CLIENTE_NIF:
				gestion.recuperarDatosNIF(cliente.getNIF());
				break;
			case RECUPERAR_TODOS:
				gestion.recuperarListadoClientes();
				break;
			case DAR_ALTA_LLAMADA:
				//gestion.darDeAltaLlamada(cliente.getNIF(),llamada);
				break;
			case LISTAR_LLAMADAS:
				gestion.listarLlamadasCliente(cliente.getNIF());
				break;
			case EMITIR_FACTURA:
				 gestion.emitirFactura(cliente.getNIF());
				 break;
			case RECUPERAR_DATOS_FACTURA:
				//gestion.recuperarDatosFacturaCodigo(codigo);
				break;
			case RECUPERAR_FACTURAS:
				gestion.recuperarFacturas(cliente.getNIF());
				break;

		}
		scanner.close();
	}

}
