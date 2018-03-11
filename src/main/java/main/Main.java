package main;

import java.util.Calendar;
import java.util.Scanner;

import cliente.Cliente;
import cliente.Direccion;
import factura.Llamada;
import factura.Tarifa;
import gestion.Gestion;

public class Main {
	public static void main(String[] args) {
		Gestion gestion = new Gestion();			
		System.out.println(Menu.getMenu());
		Scanner scan = new Scanner(System.in);
		System.out.print("Elige una opción:");
		byte opcion = scan.nextByte();
		Menu menu = Menu.getOpcion(opcion);
		switch (menu) {
		case ALTA_CLIENTE:
			System.out.println("Introducir datos cliente: ");
			System.out.print("Introduce nombre: ");
			String nombre = scan.next();
			System.out.print("Introduce NIF: ");
			String nif = scan.next();
			System.out.println("Introduce dirección: ");
			System.out.print("	-Código Postal: ");
			int codP = scan.nextInt();
			System.out.print("	-Provincia: ");
			String prov = scan.next();
			System.out.print("	-Población: ");
			String pob = scan.next();
			Direccion dir = new Direccion(codP,prov,pob);
			System.out.print("Introduce correo: ");
			String correo = scan.next();
			System.out.println("Introduce fecha de alta: ");
			System.out.print("	-Año: ");
			int año = scan.nextInt();
			System.out.print("	-Mes (numérico): ");
			int mes = scan.nextInt();
			System.out.print("	-Día: ");
			int dia = scan.nextInt();
			Calendar fecha = Calendar.getInstance();
			fecha.set(año, mes, dia);
			System.out.print("Introduce tarifa: ");
			Tarifa tarifa = new Tarifa(scan.nextDouble());
			Cliente cliente = new Cliente(nombre,nif,dir,correo,fecha,tarifa);
			gestion.darDeAltaCliente(cliente);
			break;
		case BORRAR_CLIENTE:
			System.out.println("Introducir datos cliente: ");
			System.out.print("Introduce nombre: ");
			nombre = scan.next();
			System.out.print("Introduce NIF: ");
			nif = scan.next();
			System.out.println("Introduce dirección: ");
			System.out.print("	-Código Postal: ");
			codP = scan.nextInt();
			System.out.print("	-Provincia: ");
			prov = scan.next();
			System.out.print("	-Población: ");
			pob = scan.next();
			dir = new Direccion(codP,prov,pob);
			System.out.print("Introduce correo: ");
			correo = scan.next();
			System.out.println("Introduce fecha de alta: ");
			System.out.print("	-Año: ");
			año = scan.nextInt();
			System.out.print("	-Mes (numérico): ");
			mes = scan.nextInt();
			System.out.print("	-Día: ");
			dia = scan.nextInt();
			Calendar fecha2 = Calendar.getInstance();
			fecha2.set(año, mes, dia);
			System.out.print("Introduce tarifa: ");
			tarifa = new Tarifa(scan.nextDouble());
			cliente = new Cliente(nombre,nif,dir,correo,fecha2,tarifa);
			gestion.borrarCliente(cliente);
			break;
		case CAMBIAR_TARIFA:
			System.out.print("Introduce NIF: ");
			nif = scan.next();
			System.out.print("Introduce tarifa: ");
			tarifa = new Tarifa(scan.nextDouble());
			gestion.cambiarTarifa(nif, tarifa);
			break;
		case RECUPERAR_CLIENTE_NIF:
			System.out.print("Introduce NIF: ");
			nif = scan.next();
			gestion.recuperarDatosNIF(nif);
			break;
		case RECUPERAR_TODOS:
			gestion.recuperarListadoClientes();
			break;
		case DAR_ALTA_LLAMADA:
			System.out.print("Introduce NIF: ");
			nif = scan.next();
			System.out.print("Introduce el número de teléfono: ");
			int num =scan.nextInt();
			System.out.println("Introduce fecha: ");
			System.out.print("	-Año: ");
			año = scan.nextInt();
			System.out.print("	-Mes (numérico): ");
			mes = scan.nextInt();
			System.out.print("	-Día: ");
			dia = scan.nextInt();
			fecha = Calendar.getInstance();
			fecha.set(año, mes, dia);
			System.out.print("Introduce hora: ");
			int hor = scan.nextInt();
			System.out.print("Introduce minuto: ");
			int min = scan.nextInt();
			Calendar hora = Calendar.getInstance();
			hora.set(año, mes, dia, hor, min);
			System.out.print("Introduce duración: ");
			int dur=scan.nextInt();
			
			Llamada llamada = new Llamada(num, fecha, hora, dur);
			gestion.darDeAltaLlamada(nif, llamada);
			break;
		case LISTAR_LLAMADAS:
			System.out.print("Introduce NIF: ");
			nif = scan.next();
			gestion.listarLlamadasCliente(nif);
			break;
		case EMITIR_FACTURA:
			System.out.print("Introduce NIF: ");
			nif = scan.next();
			System.out.println("Introduce fecha: ");
			System.out.print("	-Año: ");
			año = scan.nextInt();
			System.out.print("	-Mes (numérico): ");
			mes = scan.nextInt();
			System.out.print("	-Día: ");
			dia = scan.nextInt();
			fecha = Calendar.getInstance();
			fecha.set(año, mes, dia);
			gestion.emitirFactura(nif, fecha);
			break;
		case RECUPERAR_DATOS_FACTURA:
			System.out.print("Introduce código de factura: ");
			String cod = scan.next();
			gestion.recuperarDatosFacturaCodigo(cod);
			break;
		case RECUPERAR_FACTURAS:
			System.out.print("Introduce NIF: ");
			nif = scan.next();
			gestion.recuperarFacturas(nif);
		default:
			break;
		}
		System.out.println("Ok");
		scan.close();
	}
}
