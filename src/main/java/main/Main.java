package main;

import java.util.Scanner;
import gestion.Gestion;

public class Main {
	public static void main(String[] args) {
		Gestion gestion = new Gestion();			
		System.out.println(Menu.getMenu());
		Scanner scanner = new Scanner(System.in);
		System.out.print("Elige una opción:");
		byte opcion = scanner.nextByte();
		Menu menu = Menu.getOpcion(opcion);
		switch (menu) {
		case ALTA_CLIENTE:
			gestion.darDeAltaCliente(main.Consola.pedirDatosCliente());
			break;
		case BORRAR_CLIENTE:
			gestion.borrarCliente(main.Consola.pedirDatosCliente());
			break;
		case CAMBIAR_TARIFA:
			gestion.cambiarTarifa(main.Consola.pedirNIF(), main.Consola.pedirTarifa());
			break;
		case RECUPERAR_CLIENTE_NIF:
			gestion.recuperarDatosNIF(main.Consola.pedirNIF());
			break;
		case RECUPERAR_TODOS:
			gestion.recuperarListadoClientes();
			break;
		case DAR_ALTA_LLAMADA:
			gestion.darDeAltaLlamada(main.Consola.pedirNIF(), main.Consola.pedirLlamada());
			break;
		case LISTAR_LLAMADAS:
			gestion.listarLlamadasCliente(main.Consola.pedirNIF());
			break;
		case EMITIR_FACTURA:
			gestion.emitirFactura(main.Consola.pedirNIF(), main.Consola.pedirFecha());
			break;
		case RECUPERAR_DATOS_FACTURA:
			gestion.recuperarDatosFacturaCodigo(main.Consola.pedirCod());
			break;
		case RECUPERAR_FACTURAS:
			gestion.recuperarFacturas(main.Consola.pedirNIF());
		default:
			break;
		}
		System.out.println("Ok");
		scanner.close();
	}
}
