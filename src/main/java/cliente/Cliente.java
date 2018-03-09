package cliente;

import java.util.Calendar;
import java.util.Scanner;

import factura.Tarifa;
import interfaces.Fecha;

public class Cliente implements Fecha {
	private String nombre;
	private String NIF;
	private Direccion direccion;
	private String correo;
	private Calendar fechaDeAlta;
	private Tarifa tarifa;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String nombre,String nif,Direccion dir, String correo, Calendar fecha, Tarifa tarifa) {
		this.nombre=nombre;
		this.NIF=nif;
		this.direccion=dir;
		this.correo=correo;
		this.fechaDeAlta=fecha;
		this.tarifa=tarifa;
	}
	
	public Cliente(Cliente otro) {
		this.nombre=otro.nombre;
		this.NIF=otro.NIF;
		this.direccion= otro.direccion;
		this.correo=otro.correo;
		this.fechaDeAlta=otro.fechaDeAlta;
		this.tarifa=otro.tarifa;
	}

	public Calendar getFecha() {
		return fechaDeAlta;
	}
	
	public String getNIF() {
		return this.NIF;
	}
	
	public Tarifa getTarifa() {
		return this.tarifa;
	}
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	
	public static Cliente pedirDatosCliente() {
		System.out.println("Introducir datos cliente: ");
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduce nombre: ");
		String nombre = scan.next();
		System.out.println("Introduce NIF: ");
		String nif = scan.next();
		System.out.println("Introduce dirección: ");
		System.out.println("	-Código Postal: ");
		int codP = scan.nextInt();
		System.out.println("	-Provincia: ");
		String prov = scan.next();
		System.out.println("	-Población: ");
		String pob = scan.next();
		Direccion dir = new Direccion(codP,prov,pob);
		System.out.println("Introduce correo: ");
		String correo = scan.next();
		System.out.println("Introduce fecha de alta: ");
		System.out.println("	-Año: ");
		int año = scan.nextInt();
		System.out.println("	-Mes (numérico): ");
		int mes = scan.nextInt();
		System.out.println("	-Día: ");
		int dia = scan.nextInt();
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes, dia);
		System.out.println("Introduce tarifa: ");
		double importe = scan.nextDouble();
		Tarifa tarifa = new Tarifa(importe);
		Cliente cliente = new Cliente(nombre,nif,dir,correo,fecha,tarifa);
		scan.close();
		return cliente;
	}
	
	
}
