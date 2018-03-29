package main;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import cliente.Cliente;
import cliente.Direccion;
import consola.Consola;
import excepciones.ExcepcionClienteNoEncontrado;
import excepciones.ExcepcionClienteYaRegistrado;
import factura.Factura;
import factura.Llamada;
import factura.Tarifa;
import gestion.Gestion;
import menu.*;

public class Main {
	private Consola consola = new Consola();
	private Gestion gestion = new Gestion();
	
	private void start() {
		gestion.cargarDatos();
		mostrarMenu();
		gestion.guardarDatos();
	}
	
	public static void main(String[] args) {
		new Main().start();
	}

		
	private void mostrarMenu() {
		consola.mostrarDato(MenuPrincipal.getMenu());
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción:"));
		MenuPrincipal menu = MenuPrincipal.getOpcion(opcion);
	
		switch (menu) {
		case SALIR:
			break;
		case MENU_CLIENTES:
			mostrarMenuClientes();
			break;
		case MENU_FACTURAS:
			mostrarMenuFacturas();
			break;
		case MENU_LLAMADAS:
			mostrarMenuLlamadas();
			break;
		default:
			break;

		}
	}
	
	
	
	private void mostrarMenuClientes() {	
		consola.mostrarDato(MenuClientes.getMenu());
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción:"));
		MenuClientes menu = MenuClientes.getOpcion(opcion);
		switch (menu) {
		case VOLVER:
			mostrarMenu();
			break;
		case ALTA_CLIENTE:
			darAltaCliente();
			mostrarMenuClientes();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			mostrarMenuClientes();
			break;
		case CAMBIAR_TARIFA:
			cambiarTarifa();
			mostrarMenuClientes();
			break;
		case MOSTRAR_LISTADO_CLIENTES_FECHAS:
			mostrarMenuClientes();
			break;
		case RECUPERAR_CLIENTE_NIF:
			recuperarCliente();
			mostrarMenuClientes();
			break;
		case RECUPERAR_TODOS:
			recuperarListadoClientes();
			mostrarMenuClientes();
			break;
		default:
			break;
		}
	}
	
	
	
	
	private void mostrarMenuLlamadas() {	
		consola.mostrarDato(MenuLlamadas.getMenu());
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción:"));
		MenuLlamadas menu = MenuLlamadas.getOpcion(opcion);
		switch (menu) {
		case DAR_ALTA_LLAMADA:
			darAltaLlamada();
			break;
		case LISTAR_LLAMADAS:
			listarLlamadas();
			break;
		case MOSTRAR_LISTADO_LLAMADAS_FECHAS:
			break;
		case VOLVER:
			mostrarMenu();
			break;
		default:
			break;
		}
	}


	
	private void mostrarMenuFacturas() {	
		consola.mostrarDato(MenuFacturas.getMenu());
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción:"));
		MenuFacturas menu = MenuFacturas.getOpcion(opcion);
		switch (menu) {
		case EMITIR_FACTURA:
			emitirFactura();
			break;
		case MOSTRAR_LISTADO_FACTURAS_FECHAS:
			break;
		case RECUPERAR_DATOS_FACTURA:
			recuperarDatosFactura();
			break;
		case RECUPERAR_FACTURAS:
			recuperarFacturas();
			break;
		case VOLVER:
			mostrarMenu();
			break;
		default:
			break;
		}
	}

	
	
	

	private void recuperarListadoClientes() {
		HashMap<String, Cliente> clientes = gestion.recuperarListadoClientes();
		for(Cliente cliente : clientes.values()) {
			consola.mostrarDato(cliente.getNIF());
		}
	}

	private void recuperarFacturas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		gestion.recuperarFacturas(nif);
	}

	private void recuperarDatosFactura() {
		int cod = Integer.parseInt(consola.pedirDato("Introduce código de factura: "));
		Factura factura = gestion.recuperarDatosFacturaCodigo(cod);
		if(factura != null)
			consola.mostrarDato(factura.toString());
		else
			consola.mostrarDato("Factura no encontrada");
	}

	private void emitirFactura() {
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("Introduce fecha: ");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		gestion.emitirFactura(nif, fecha);
		consola.mostrarDato("Factura realizada");
	}

	private void listarLlamadas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		List<Llamada> llamadas = gestion.listarLlamadasCliente(nif);
		if(llamadas != null) {
			for(Llamada llamada : llamadas)
				llamada.toString();
		}else
			consola.mostrarDato("No hay llamadas");
	}

	private void darAltaLlamada() {
		String nif = consola.pedirDato("Introduce NIF: ");
		
		int num = Integer.parseInt(consola.pedirDato("Introduce el número de teléfono: "));
		
		consola.mostrarDato("Introduce fecha: ");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		int hor = Integer.parseInt(consola.pedirDato("Introduce hora: "));
		int min = Integer.parseInt(consola.pedirDato("Introduce minuto: "));
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia, hor, min);
		
		int dur = Integer.parseInt(consola.pedirDato("Introduce duración: "));
		
		Llamada llamada = new Llamada(num, fecha, dur);	
		gestion.darDeAltaLlamada(nif, llamada);
		consola.mostrarDato("Llamada registrada con exito");
	
	}

	private void recuperarCliente() {
		String nif = consola.pedirDato("Introduce NIF: ");
		try {
			Cliente cliente = gestion.recuperarDatosNIF(nif);
			consola.mostrarDato(cliente.toString());
		}catch(ExcepcionClienteNoEncontrado e) {
			e.getMessage();
		}
	}

	private void cambiarTarifa() {
		String nif = consola.pedirDato("Introduce NIF: ");
		Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDato("Introduce tarifa: ")));
		try {
			gestion.cambiarTarifa(nif, tarifa);
			consola.mostrarDato("Tarifa cambiada con exito");
		}catch(ExcepcionClienteNoEncontrado e) {
			e.getMessage();
		}
	}

	private void borrarCliente() {
		String nif = consola.pedirDato("Introduce NIF: ");
		try {
			gestion.borrarCliente(nif);
			consola.mostrarDato("Cliente borrado con exito");
		}catch(ExcepcionClienteNoEncontrado e) {
			e.getMessage();
		}
		
	}
	
	private void darAltaCliente() {
		consola.mostrarDato("Introducir datos cliente: ");
		
		String nombre = consola.pedirDato("Introduce nombre: ");
		String nif = consola.pedirDato("Introduce NIF: ");
		
		consola.mostrarDato("Introduce dirección: ");
		int codP = Integer.parseInt(consola.pedirDato("	-Código Postal: "));
		String prov = consola.pedirDato("	-Provincia: "); 
		String pob = consola.pedirDato("	-Población: ");
		Direccion dir = new Direccion(codP,prov,pob);
		
		String correo = consola.pedirDato("Introduce correo: ");
		consola.mostrarDato("Introduce fecha de alta: ");
		
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		
		Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDato("Introduce tarifa: ")));
		Cliente cliente = new Cliente(nombre,nif,dir,correo,fecha,tarifa);
		
		try {
			gestion.darDeAltaCliente(cliente);
			consola.mostrarDato("Cliente dado de alta");
		}catch(ExcepcionClienteYaRegistrado e) {
			e.getMessage();
		}
		
	}
	
}
