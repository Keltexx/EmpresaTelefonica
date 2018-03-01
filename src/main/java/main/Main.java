package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println(Menu.getMenu());
		Scanner scanner = new Scanner(System.in);
		System.out.print("Elige una opción:");
		byte opcion = scanner.nextByte();
		Menu opcionMenu = Menu.getOpcion(opcion);
		switch (opcionMenu) {
			case ALTA_CLIENTE:
				darDeAltaCliente(cliente);
				break;
			case BORRAR_CLIENTE:
				borrarCliente(cliente);
				break;
			case CAMBIAR_TARIFA:
				cambiarTarifa(cliente.getNIF(), cliente.getTarifa());
				break;
			case RECUPERAR_CLIENTE_NIF:
				recuperarDatosNIF(cliente.getNIF());
				break;
			case RECUPERAR_TODOS:
				recuperarListadoClientes();
				break;
			case DAR_ALTA_LLAMADA:
				darDeAltaLlamada(cliente.getNIF(),llamada);
				break;
			case LISTAR_LLAMADAS:
				listarLlamadasCliente(cliente.getNIF());
				break;
			case EMITIR_FACTURA:
				 emitirFactura(nif);
				 break;
			case RECUPERAR_DATOS_FACTURA:
				recuperarDatosFacturaCodigo(codigo);
				break;
			case RECUPERAR_FACTURAS:
				recuperarFacturas(cliente.getNIF());
				break;

		}

	}

}
