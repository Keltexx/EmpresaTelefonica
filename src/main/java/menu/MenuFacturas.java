package menu;



public enum MenuFacturas {
	VOLVER("Regresar al menu principal"),
	EMITIR_FACTURA("Emitir factura"),
	RECUPERAR_DATOS_FACTURA("Recuperar los datos de una factura"),
	RECUPERAR_FACTURAS("Recuperar todas las facturas"),
	MOSTRAR_LISTADO_FACTURAS_FECHAS("Mostrar un listado de facturas emitidas entre dos fechas");
	
	private String descripcion;
	
	private MenuFacturas(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	
	public static MenuFacturas getOpcion(int posicion) {
		return values()[posicion];
	}
	
	
	public static String getMenu() {
	    StringBuilder sb = new StringBuilder();
	    for(MenuFacturas opcion: MenuFacturas.values()) {
	        sb.append(opcion.ordinal());
	        sb.append(".- ");
	        sb.append(opcion.getDescripcion());
	        sb.append("\n");
	    }
	    return sb.toString();
	}

	
}


