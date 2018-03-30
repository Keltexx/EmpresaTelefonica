package excepciones;

public class ExcepcionListaFacturasVacia extends Exception{
	public ExcepcionListaFacturasVacia(){
		super("No hay facturas guardadas");	
	}	
}
