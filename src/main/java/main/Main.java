package main;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cliente.*;
import consola.Consola;
import excepciones.*;
import factura.*;
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
			mostrarClientesEntreFechas();
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
			mostrarLlamadasEntreFechas();
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
			mostrarFacturasEntreFechas();
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

	
	//metodos de clientes
	
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

	private void borrarCliente() {
		String nif = consola.pedirDato("Introduce NIF: ");
		try {
			gestion.borrarCliente(nif);
			consola.mostrarDato("\nCliente borrado con exito \n\n");
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

	private void mostrarClientesEntreFechas() {
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();
		
		consola.mostrarDato("Introduce la fecha de inicio: \n");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		fechaInicio.set(año, mes, dia);
		
		consola.mostrarDato("Introduce fecha de fin: \n");
		año = Integer.parseInt(consola.pedirDato("	-Año: "));
		mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		fechaFin.set(año, mes, dia);
		
		try {
			Collection<Cliente> clientes = gestion.mostrarListadoClientesFechas(fechaInicio, fechaFin);
			for(Cliente cliente : clientes) {
				consola.mostrarDato(cliente.getNIF());
				consola.mostrarDato("\n\n");
			}
		} catch (ExcepcionFechas | ExcepcionListaClientesVacia e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
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
	
	private void recuperarListadoClientes() {
		HashMap<String, Cliente> clientes = null;
		try {
			clientes = gestion.recuperarListadoClientes();
			for(Cliente cliente : clientes.values()) {
				consola.mostrarDato(cliente.getNIF());
				consola.mostrarDato("\n\n");
			}
		} catch (ExcepcionListaClientesVacia e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
		
	}

	//metodos de llamadas
	
	private void listarLlamadas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		List<Llamada> llamadas = null;
		try {
			llamadas = gestion.listarLlamadasCliente(nif);
			for(Llamada llamada : llamadas) {
				consola.mostrarDato(llamada.toString());
				consola.mostrarDato("\n\n");
			}			
		} catch (ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
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
			consola.mostrarDato("\nLlamada registrada con exito \n\n");
		} catch (ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}	
	}

	private void mostrarLlamadasEntreFechas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();
		
		consola.mostrarDato("Introduce la fecha de inicio: \n");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		fechaInicio.set(año, mes, dia);
		
		consola.mostrarDato("Introduce fecha de fin: \n");
		año = Integer.parseInt(consola.pedirDato("	-Año: "));
		mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		fechaFin.set(año, mes, dia);
		
		try {
			Collection<Llamada> llamadas = gestion.mostrarListadoLlamadasFechas(nif, fechaInicio, fechaFin);
			for(Llamada llamada : llamadas) {
				consola.mostrarDato(llamada.toString());
				consola.mostrarDato("\n\n");
			}
		} catch (ExcepcionListaLlamadasVacia | ExcepcionFechas | ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
	}
	
	//metodos de facturas
	
	private void recuperarFacturas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		try {
			List<Factura> facturas = gestion.recuperarFacturas(nif);
			for(Factura factura : facturas) {
				consola.mostrarDato(factura.toString());
				consola.mostrarDato("\n\n");
			}
		} catch (ExcepcionClienteNoEncontrado | ExcepcionListaFacturasVacia e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
	}

	private void recuperarDatosFactura() {
		int cod = Integer.parseInt(consola.pedirDato("Introduce código de factura: "));
		try {
			Factura factura = gestion.recuperarDatosFacturaCodigo(cod);
			consola.mostrarDato(factura.toString());
			consola.mostrarDato("\n\n");
		} catch (ExcepcionFacturaNoEncontrada e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
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
			consola.mostrarDato("\nFactura realizada \n\n");
		} catch (ExcepcionClienteNoEncontrado e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
	}

	private void mostrarFacturasEntreFechas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();
		
		consola.mostrarDato("Introduce la fecha de inicio: \n");
		int año = Integer.parseInt(consola.pedirDato("	-Año: "));
		int mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		int dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		fechaInicio.set(año, mes, dia);
		
		consola.mostrarDato("Introduce fecha de fin: \n");
		año = Integer.parseInt(consola.pedirDato("	-Año: "));
		mes = Integer.parseInt(consola.pedirDato("	-Mes (numérico): "));		
		dia = Integer.parseInt(consola.pedirDato("	-Día: "));
		fechaFin.set(año, mes, dia);
		
		try {
			Collection<Factura> facturas = gestion.mostrarListadoFacturasFechas(nif, fechaInicio, fechaFin);
			for(Factura factura : facturas) {
				consola.mostrarDato(factura.toString());
				consola.mostrarDato("\n\n");
			}
		} catch (ExcepcionClienteNoEncontrado | ExcepcionListaFacturasVacia | ExcepcionFechas e) {
			consola.mostrarDato("\n"+ e.getMessage() + "\n\n");
		}
	}

	
}
