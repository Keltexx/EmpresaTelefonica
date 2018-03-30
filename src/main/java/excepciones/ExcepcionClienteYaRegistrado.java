package excepciones;

public class ExcepcionClienteYaRegistrado extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7891655709476644098L;

	public ExcepcionClienteYaRegistrado(){
		super("El cliente introducido ya está registrado");
	}
}
