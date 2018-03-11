package main;

import java.util.Calendar;
import java.util.Scanner;

import cliente.Cliente;
import cliente.Direccion;
import factura.Llamada;
import factura.Tarifa;

public class Consola {
	public static String pedirNIF() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Introduce NIF: ");
		String nif = scan.next();
		scan.close();
		return nif;
	}
	
	public static Tarifa pedirTarifa() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduce nueva tarifa:");
		Tarifa tarifa = new Tarifa(scanner.nextDouble());
		scanner.close();
		return tarifa;
	}
	
	public static Calendar pedirFecha() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduce fecha: ");
		System.out.print("	-Año: ");
		int año = scan.nextInt();
		System.out.print("	-Mes (numérico): ");
		int mes = scan.nextInt();
		System.out.print("	-Día: ");
		int dia = scan.nextInt();
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		scan.close();
		return fecha;
	}
	
	public static String pedirCod() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Introduce código de factura: ");
		String cod = scan.next();
		scan.close();
		return cod;
	}

	public static Cliente pedirDatosCliente() {
		System.out.println("Introducir datos cliente: ");
		Scanner scan = new Scanner(System.in);
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
		scan.close();
		return cliente;
	}
	
	public static Llamada pedirLlamada() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Introduce el número de teléfono: ");
		int num =scan.nextInt();
		System.out.println("Introduce fecha: ");
		System.out.print("	-Año: ");
		int año = scan.nextInt();
		System.out.print("	-Mes (numérico): ");
		int mes = scan.nextInt();
		System.out.print("	-Día: ");
		int dia = scan.nextInt();
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		System.out.println("Introduce hora: ");
		int hor = scan.nextInt();
		System.out.println("Introduce minuto: ");
		int min = scan.nextInt();
		Calendar hora = Calendar.getInstance();
		hora.set(año, mes, dia, hor, min);
		System.out.println("Introduce duración: ");
		int dur=scan.nextInt();
		
		Llamada llamada = new Llamada(num, fecha, hora, dur);
		scan.close();
		return llamada;
	}
}
