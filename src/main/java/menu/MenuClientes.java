package menu;



public enum MenuClientes {
	VOLVER("Regresar al menu principal"),
	ALTA_CLIENTE("Dar de alta un nuevo cliente"),
	BORRAR_CLIENTE("Borrar un cliente"),
	CAMBIAR_TARIFA("Cambiar la tarifa de un cliente"),
	RECUPERAR_CLIENTE_NIF("Recuperar los datos de un cliente a partir de su NIF"),
	RECUPERAR_TODOS("Recuperar el listado de todos los clientes"),
	MOSTRAR_LISTADO_CLIENTES_FECHAS("Mostrar un listado de clientes que fueron dados de alta entre dos fechas");
	
	
	private String descripcion;
	
	private MenuClientes(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	
	public static MenuClientes getOpcion(int posicion) {
		return values()[posicion];
	}
	
	
	public static String getMenu() {
	    StringBuilder sb = new StringBuilder();
	    for(MenuClientes opcion: MenuClientes.values()) {
	        sb.append(opcion.ordinal());
	        sb.append(".- ");
	        sb.append(opcion.getDescripcion());
	        sb.append("\n");
	    }
	    return sb.toString();
	}

	
}


