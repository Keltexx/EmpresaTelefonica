package menu;



public enum MenuPrincipal {
	SALIR("Salir"),
	MENU_CLIENTES("Gestion de cliente"),
	MENU_LLAMADAS("Gestion de llamadas"),
	MENU_FACTURAS("Gestion de facturas");
	
	private String descripcion;
	
	private MenuPrincipal(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	
	public static MenuPrincipal getOpcion(int posicion) {
		return values()[posicion];
	}
	
	
	public static String getMenu() {
	    StringBuilder sb = new StringBuilder();
	    for(MenuPrincipal opcion: MenuPrincipal.values()) {
	        sb.append(opcion.ordinal());
	        sb.append(".- ");
	        sb.append(opcion.getDescripcion());
	        sb.append("\n");
	    }
	    return sb.toString();
	}

	
}


