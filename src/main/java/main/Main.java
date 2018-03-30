package main;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import cliente.Cliente;
import cliente.Direccion;
import cliente.Empresa;
import cliente.Particular;
import consola.Consola;
import excepciones.ExcepcionClienteNoEncontrado;
import excepciones.ExcepcionClienteYaRegistrado;
import excepciones.ExcepcionFacturaNoEncontrada;
import excepciones.ExcepcionListaClientesVacia;
import excepciones.ExcepcionListaFacturasVacia;
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
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción: "));
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
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción: "));
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
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción: "));
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
		HashMap<String, Cliente> clientes = null;
		try {
			clientes = gestion.recuperarListadoClientes();
		} catch (ExcepcionListaClientesVacia e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
		for(Cliente cliente : clientes.values()) {
			consola.mostrarDato(cliente.getNIF());
			consola.mostrarDato("\n\n");
		}
	}

	private void recuperarFacturas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		try {
			gestion.recuperarFacturas(nif);
		} catch (ExcepcionClienteNoEncontrado | ExcepcionListaFacturasVacia e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
	}

	private void recuperarDatosFactura() {
		int cod = Integer.parseInt(consola.pedirDato("Introduce código de factura: "));
		Factura factura = null;
		try {
			factura = gestion.recuperarDatosFacturaCodigo(cod);
		} catch (ExcepcionFacturaNoEncontrada e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
		if(factura != null) {
			consola.mostrarDato(factura.toString());
			consola.mostrarDato("\n\n");
		}
		else
			consola.mostrarDato("\nFactura no encontrada \n\n");
	}

	private void emitirFactura() {
		String nif = consola.pedirDato("Introduce NIF: ");
		consola.mostrarDato("Introduce fecha: \n");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		try {
			gestion.emitirFactura(nif, fecha);
		} catch (ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
		consola.mostrarDato("\nFactura realizada \n\n");
	}

	private void listarLlamadas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		List<Llamada> llamadas = null;
		try {
			llamadas = gestion.listarLlamadasCliente(nif);
		} catch (ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
		if(llamadas != null) {
			for(Llamada llamada : llamadas) {
				llamada.toString();
				consola.mostrarDato("\n\n");
			}
		}else
			consola.mostrarDato("\nNo hay llamadas \n\n");
	}

	private void darAltaLlamada() {
		String nif = consola.pedirDato("Introduce NIF: ");

		int num = Integer.parseInt(consola.pedirDato("Introduce el número de teléfono: "));
		
		consola.mostrarDato("Introduce fecha: \n");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		int hor = Integer.parseInt(consola.pedirDato("Introduce hora: "));
		int min = Integer.parseInt(consola.pedirDato("Introduce minuto: "));
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia, hor, min);
		
		int dur = Integer.parseInt(consola.pedirDato("Introduce duración: "));
		
		Llamada llamada = new Llamada(num, fecha, dur);	
		try {
			gestion.darDeAltaLlamada(nif, llamada);
		} catch (ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
		consola.mostrarDato("\nLlamada registrada con exito \n\n");
	
	}

	private void recuperarCliente() {
		String nif = consola.pedirDato("Introduce NIF: ");
		try {
			Cliente cliente = gestion.recuperarDatosNIF(nif);
			consola.mostrarDato(cliente.toString());
			consola.mostrarDato("\n\n");
		}catch(ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
	}

	private void cambiarTarifa() {
		String nif = consola.pedirDato("Introduce NIF: ");
		Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDato("Introduce tarifa: ")));
		try {
			gestion.cambiarTarifa(nif, tarifa);
			consola.mostrarDato("\nTarifa cambiada con exito \n\n");
		}catch(ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
	}

	private void borrarCliente() {
		String nif = consola.pedirDato("Introduce NIF: ");
		try {
			gestion.borrarCliente(nif);
			consola.mostrarDato("\nCliente borrado con exito \n\n");
		}catch(ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
		
	}
	
	private void darAltaCliente() {
		consola.mostrarDato("Introducir datos cliente: \n");
		
		String empresa = consola.pedirDato("¿Es una empresa? (s/n): ");
		String apellidos = null;
		if(empresa.equals("n"))
			apellidos = consola.pedirDato("Introduce los apellidos: ");
		
		String nombre = consola.pedirDato("Introduce nombre: ");
		String nif = consola.pedirDato("Introduce NIF: ");
		
		consola.mostrarDato("Introduce dirección: \n");
		int codP = Integer.parseInt(consola.pedirDato("	-Código Postal: "));
		String prov = consola.pedirDato("	-Provincia: "); 
		String pob = consola.pedirDato("	-Población: ");
		Direccion dir = new Direccion(codP,prov,pob);
		
		String correo = consola.pedirDato("Introduce correo: ");
		consola.mostrarDato("Introduce fecha de alta: \n");
		
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		
		Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDato("Introduce tarifa: ")));
		
		Cliente cliente;
		if(empresa.equals("s"))
			cliente = new Empresa(nombre,nif,dir,correo,fecha,tarifa);	
		else
			cliente = new Particular(nombre, apellidos, nif, dir, correo, fecha, tarifa);
		
		
		try {
			gestion.darDeAltaCliente(cliente);
			consola.mostrarDato("\nCliente dado de alta \n\n");
		}catch(ExcepcionClienteYaRegistrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
		
	}
	
}
