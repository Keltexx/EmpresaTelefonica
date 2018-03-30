package excepciones;

public class ExcepcionFacturaNoEncontrada extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3989087337567108473L;

	public ExcepcionFacturaNoEncontrada(){
		super("La factura con el código solicitado no existe");
	}
}
