package excepciones;

public class ExcepcionListaFacturasVacia extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 82225316379029004L;

	public ExcepcionListaFacturasVacia(){
		super("No hay facturas guardadas");	
	}	
}
