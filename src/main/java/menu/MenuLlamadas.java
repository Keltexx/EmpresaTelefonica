package menu;



public enum MenuLlamadas {
	VOLVER("Regresar al menu principal"),
	DAR_ALTA_LLAMADA("Dar de alta una llamada"),
	LISTAR_LLAMADAS("Listar todas las llamadas de un cliente"),
	MOSTRAR_LISTADO_LLAMADAS_FECHAS("Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas");
	
	private String descripcion;
	
	private MenuLlamadas(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	
	public static MenuLlamadas getOpcion(int posicion) {
		return values()[posicion];
	}
	
	
	public static String getMenu() {
	    StringBuilder sb = new StringBuilder();
	    for(MenuLlamadas opcion: MenuLlamadas.values()) {
	        sb.append(opcion.ordinal());
	        sb.append(".- ");
	        sb.append(opcion.getDescripcion());
	        sb.append("\n");
	    }
	    return sb.toString();
	}

	
}


