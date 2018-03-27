package main;

import java.io.IOException;
import java.util.Calendar;
import cliente.Cliente;
import cliente.Direccion;
import consola.Consola;
import excepciones.ExcepcionClienteNoEncontrado;
import excepciones.ExcepcionClienteYaRegistrado;
import factura.Llamada;
import factura.Tarifa;
import gestion.Gestion;

public class Main {
	private Consola consola = new Consola();
	private Gestion gestion = new Gestion();
	
	private void start() throws IOException, ExcepcionClienteNoEncontrado, ExcepcionClienteYaRegistrado {
		gestion.cargarDatos();
		mostrarMenu();
		gestion.guardarDatos();
	}
	
	public static void main(String[] args) throws IOException, ExcepcionClienteNoEncontrado, ExcepcionClienteYaRegistrado  {
		new Main().start();
	}

		
	private void mostrarMenu() throws ExcepcionClienteNoEncontrado, ExcepcionClienteYaRegistrado {	
		consola.mostrarDato(Menu.getMenu());
		byte opcion = Byte.parseByte(consola.pedirDato("Elige una opción:"));
		Menu menu = Menu.getOpcion(opcion);
		switch (menu) {
		case ALTA_CLIENTE:
			darAltaCliente();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			break;
		case CAMBIAR_TARIFA:
			cambiarTarifa();
			break;
		case RECUPERAR_CLIENTE_NIF:
			recuperarCliente();
			break;
		case RECUPERAR_TODOS:
			gestion.recuperarListadoClientes();
			break;
		case DAR_ALTA_LLAMADA:
			darAltaLlamada();
			break;
		case LISTAR_LLAMADAS:
			listarLlamadas();
			break;
		case EMITIR_FACTURA:
			emitirFactura();
			break;
		case RECUPERAR_DATOS_FACTURA:
			recuperarDatosFactura();
			break;
		case RECUPERAR_FACTURAS:
			recuperarFacturas();
		default:
			break;
		}
		consola.mostrarDato("Ok");
	}

	private void recuperarFacturas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		gestion.recuperarFacturas(nif);
	}

	private void recuperarDatosFactura() {
		int cod = Integer.parseInt(consola.pedirDato("Introduce código de factura: "));
		gestion.recuperarDatosFacturaCodigo(cod);
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
	}

	private void listarLlamadas() {
		String nif = consola.pedirDato("Introduce NIF: ");
		gestion.listarLlamadasCliente(nif);
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
	}

	private void recuperarCliente() throws ExcepcionClienteNoEncontrado {
		String nif = consola.pedirDato("Introduce NIF: ");
		gestion.recuperarDatosNIF(nif);
	}

	private void cambiarTarifa() throws ExcepcionClienteNoEncontrado {
		String nif = consola.pedirDato("Introduce NIF: ");
		Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDato("Introduce tarifa: ")));
		gestion.cambiarTarifa(nif, tarifa);
	}

	private void borrarCliente() throws ExcepcionClienteNoEncontrado {
		String nif = consola.pedirDato("Introduce NIF: ");
		gestion.borrarCliente(nif);
	}
	
	private void darAltaCliente() throws ExcepcionClienteYaRegistrado {
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
		gestion.darDeAltaCliente(cliente);
	}
	
}
