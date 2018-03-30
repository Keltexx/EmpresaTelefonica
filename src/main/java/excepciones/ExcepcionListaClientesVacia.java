package excepciones;

public class ExcepcionListaClientesVacia extends Exception{
	public ExcepcionListaClientesVacia(){
		super("No hay clientes guardados");	
	}	
}
