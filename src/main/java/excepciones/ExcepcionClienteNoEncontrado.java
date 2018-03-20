package excepciones;

public class ExcepcionClienteNoEncontrado extends Exception{
	public ExcepcionClienteNoEncontrado(){
		super("El cliente no se ha encontrado");	
	}	
}
