package excepciones;

public class ExcepcionListaClientesVacia extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7379876300153940331L;

	public ExcepcionListaClientesVacia(){
		super("No hay clientes guardados");	
	}	
}
