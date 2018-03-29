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
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción: "));
		consola.mostrarDato("\n");
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
		}
	}
	
	
	
	private void mostrarMenuClientes() {	
		consola.mostrarDato(MenuClientes.getMenu());
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción:"));
		consola.mostrarDato("\n");
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
		}
	}
	
	
	
	
	private void mostrarMenuLlamadas() {	
		consola.mostrarDato(MenuLlamadas.getMenu());
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción:"));
		consola.mostrarDato("\n");
		MenuLlamadas menu = MenuLlamadas.getOpcion(opcion);
		switch (menu) {
		case VOLVER:
			mostrarMenu();
			break;
		case DAR_ALTA_LLAMADA:
			darAltaLlamada();
			mostrarMenuLlamadas();
			break;
		case LISTAR_LLAMADAS:
			listarLlamadas();
			mostrarMenuLlamadas();
			break;
		case MOSTRAR_LISTADO_LLAMADAS_FECHAS:
			mostrarMenuLlamadas();
			break;
		}
	}


	
	private void mostrarMenuFacturas() {	
		consola.mostrarDato(MenuFacturas.getMenu());
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción:"));
		consola.mostrarDato("\n");
		MenuFacturas menu = MenuFacturas.getOpcion(opcion);
		switch (menu) {
		case VOLVER:
			mostrarMenu();
			break;
		case EMITIR_FACTURA:
			emitirFactura();
			mostrarMenuFacturas();
			break;
		case MOSTRAR_LISTADO_FACTURAS_FECHAS:
			mostrarMenuFacturas();
			break;
		case RECUPERAR_DATOS_FACTURA:
			recuperarDatosFactura();
			mostrarMenuFacturas();
			break;
		case RECUPERAR_FACTURAS:
			recuperarFacturas();
			mostrarMenuFacturas();
			break;
		}
	}

	
	
	

	private void recuperarListadoClientes() {
		HashMap<String, Cliente> clientes = gestion.recuperarListadoClientes();
		for(Cliente cliente : clientes.values()) {
			consola.mostrarDato(cliente.getNIF());
		}
		consola.mostrarDato("\n");
	}

	private void recuperarFacturas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		gestion.recuperarFacturas(nif);
	}

	private void recuperarDatosFactura() {
		int cod = Integer.parseInt(consola.pedirDato("Introduce código de factura: "));
		consola.mostrarDato("\n");
		Factura factura = gestion.recuperarDatosFacturaCodigo(cod);
		if(factura != null)
			consola.mostrarDato(factura.toString());
		else
			consola.mostrarDato("Factura no encontrada \n");
	}

	private void emitirFactura() {
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("\n");
		consola.mostrarDato("Introduce fecha: ");
		consola.mostrarDato("\n");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		consola.mostrarDato("\n");
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));
		consola.mostrarDato("\n");
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		consola.mostrarDato("\n");
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		gestion.emitirFactura(nif, fecha);
		consola.mostrarDato("Factura realizada \n");
	}

	private void listarLlamadas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("\n");
		List<Llamada> llamadas = gestion.listarLlamadasCliente(nif);
		if(llamadas != null) {
			for(Llamada llamada : llamadas)
				llamada.toString();
		}else
			consola.mostrarDato("No hay llamadas \n");
	}

	private void darAltaLlamada() {
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("\n");

		int num = Integer.parseInt(consola.pedirDato("Introduce el número de teléfono: "));
		consola.mostrarDato("\n");
		
		consola.mostrarDato("Introduce fecha: ");
		consola.mostrarDato("\n");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		consola.mostrarDato("\n");
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));
		consola.mostrarDato("\n");
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		consola.mostrarDato("\n");
		int hor = Integer.parseInt(consola.pedirDato("Introduce hora: "));
		consola.mostrarDato("\n");
		int min = Integer.parseInt(consola.pedirDato("Introduce minuto: "));
		consola.mostrarDato("\n");
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia, hor, min);
		
		int dur = Integer.parseInt(consola.pedirDato("Introduce duración: "));
		consola.mostrarDato("\n");
		
		Llamada llamada = new Llamada(num, fecha, dur);	
		gestion.darDeAltaLlamada(nif, llamada);
		consola.mostrarDato("Llamada registrada con exito \n");
	
	}

	private void recuperarCliente() {
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("\n");
		try {
			Cliente cliente = gestion.recuperarDatosNIF(nif);
			consola.mostrarDato(cliente.toString());
			consola.mostrarDato("\n");
		}catch(ExcepcionClienteNoEncontrado e) {
			e.getMessage();
		}
	}

	private void cambiarTarifa() {
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("\n");
		Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDato("Introduce tarifa: ")));
		consola.mostrarDato("\n");
		try {
			gestion.cambiarTarifa(nif, tarifa);
			consola.mostrarDato("Tarifa cambiada con exito \n");
		}catch(ExcepcionClienteNoEncontrado e) {
			e.getMessage();
		}
	}

	private void borrarCliente() {
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("\n");
		try {
			gestion.borrarCliente(nif);
			consola.mostrarDato("Cliente borrado con exito \n");
		}catch(ExcepcionClienteNoEncontrado e) {
			e.getMessage();
		}
		
	}
	
	private void darAltaCliente() {
		consola.mostrarDato("Introducir datos cliente: ");
		consola.mostrarDato("\n");
		
		String nombre = consola.pedirDato("Introduce nombre: ");
		consola.mostrarDato("\n");
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("\n");
		
		consola.mostrarDato("Introduce dirección: ");
		consola.mostrarDato("\n");
		int codP = Integer.parseInt(consola.pedirDato("	-Código Postal: "));
		consola.mostrarDato("\n");
		String prov = consola.pedirDato("	-Provincia: "); 
		consola.mostrarDato("\n");
		String pob = consola.pedirDato("	-Población: ");
		consola.mostrarDato("\n");
		Direccion dir = new Direccion(codP,prov,pob);
		
		String correo = consola.pedirDato("Introduce correo: ");
		consola.mostrarDato("\n");
		consola.mostrarDato("Introduce fecha de alta: ");
		consola.mostrarDato("\n");
		
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		consola.mostrarDato("\n");
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		consola.mostrarDato("\n");
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		consola.mostrarDato("\n");
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		
		Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDato("Introduce tarifa: ")));
		consola.mostrarDato("\n");
		Cliente cliente = new Cliente(nombre,nif,dir,correo,fecha,tarifa);
		
		try {
			gestion.darDeAltaCliente(cliente);
			consola.mostrarDato("Cliente dado de alta \n");
		}catch(ExcepcionClienteYaRegistrado e) {
			e.getMessage();
		}
		
	}
	
}
