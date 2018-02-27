package main;

import java.util.Scanner;

public enum Menu {
	ALTA_CLIENTE("Dar de alta un nuevo cliente"),
	BORRAR_CLIENTE("Borrar un cliente"),
	CAMBIAR_TARIFA("Cambiar la tarifa de un cliente"),
	RECUPERAR_CLIENTE_NIF("Recuperar los datos de un cliente a partir de su NIF"),
	RECUPERAR_TODOS("Recuperar el listado de todos los clientes");
	
	private String descripcion;
	
	private Menu(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	
	public static Menu getOpcion(int posicion) {
		return values()[posicion];
	}
	
	
	public static String getMenu() {
	    StringBuilder sb = new StringBuilder();
	    for(Menu opcion: Menu.values()) {
	        sb.append(opcion.ordinal());
	        sb.append(".- ");
	        sb.append(opcion.getDescripcion());
	        sb.append("\n");
	    }
	    return sb.toString();
	}

	System.out.println(Menu.getMenu());
	Scanner scanner = new Scanner(System.in);
	System.out.print("Elige una opción:");
	byte opcion = scanner.nextByte();
	Menu opcionMenu = Menu.getOpcion(opcion);
	switch(opcionMenu) {
		case ALTA_CLIENTE:
		darDeAltaCliente()
	
	}


}}