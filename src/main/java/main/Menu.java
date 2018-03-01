package main;

import java.util.Scanner;

public enum Menu {
	ALTA_CLIENTE("Dar de alta un nuevo cliente"),
	BORRAR_CLIENTE("Borrar un cliente"),
	CAMBIAR_TARIFA("Cambiar la tarifa de un cliente"),
	RECUPERAR_CLIENTE_NIF("Recuperar los datos de un cliente a partir de su NIF"),
	RECUPERAR_TODOS("Recuperar el listado de todos los clientes"),
	DAR_ALTA_LLAMADA("Dar de alta una llamada"),
	LISTAR_LLAMADAS("Listar todas las llamadas de un cliente"),
	EMITIR_FACTURA("Calcular factura"),
	RECUPERAR_DATOS_FACTURA("Recuperar los datos de una factura"),
	RECUPERAR_FACTURAS("Recuperar todas las facturas");
	
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

	
}


