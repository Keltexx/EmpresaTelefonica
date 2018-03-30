package excepciones;

public class ExcepcionClienteNoEncontrado extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8356864980647585841L;

	public ExcepcionClienteNoEncontrado(){
		super("El cliente no se ha encontrado");	
	}	
}
